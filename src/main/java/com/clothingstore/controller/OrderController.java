package com.clothingstore.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.clothingstore.entity.Customer;
import com.clothingstore.entity.Order;
import com.clothingstore.entity.OrderLine;
import com.clothingstore.interfaces.Controller;

public class OrderController implements Controller<Order> {

    @Override
    public void insert(Order order) {
        Connection connection = null;
        try {
            connection = Controller.getConnection();
            String sql = "INSERT INTO orders (performer_id, customer_id, date, total_money) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getPerformer().getId());
            statement.setInt(2, order.getCustomer().getId());
            statement.setDate(3, order.getDate());
            statement.setInt(4, order.getTotalMoney());
            
            int affectedRows = statement.executeUpdate();
            int orderId = 0;
            if (affectedRows == 0) {
                throw new SQLException("Creating Order failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating Order failed, no ID obtained.");
                }
            }

            OrderLineController orderLineController = new OrderLineController();
            for (OrderLine orderLine : order.getOrderLines()) {
                orderLine.setId(orderId);
                orderLineController.insert(orderLine);
            }
            // Update Customer's expenditure & rank
            Customer customer = order.getCustomer();
            customer.setExpenditure(customer.getExpenditure() + order.getTotalMoney());
            customer.updateRank();
            new CustomerController().update(customer);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void delete(Order order) {
        Connection connection = null;
        try {
            connection = Controller.getConnection();
            String sql = "DELETE FROM orders WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.executeUpdate();
            // Delete order_lines
            for (OrderLine orderLine : order.getOrderLines()) {
                sql = "DELETE FROM order_line WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, orderLine.getId());
                statement.executeUpdate();
            }
            // Update customer's expenditure
            Customer customer = order.getCustomer();
            customer.setExpenditure(customer.getExpenditure() - order.getTotalMoney());
            customer.updateRank();
            new CustomerController().update(customer);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException("Order should not be edited after creating as It relates to Product's quantity and Customer's expenditure changes.");

    }

    @Override
    public List<Order> findById(int id) {
        Connection connection = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            connection = Controller.getConnection();
            String sql = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order = new Order(
                    resultSet.getInt("id"),
                    new StaffController().findById(resultSet.getInt("staff_id")).get(0),
                    new CustomerController().findById(resultSet.getInt("customer_id")).get(0),
                    resultSet.getDate("date_created"),
                    resultSet.getInt("total_money")
                );

                order.setOrderLines(new OrderLineController().findByOrder(order));
                orders.add(order);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orders;
    }

    @Override
    public List<Order> findById(String code) {
        throw new UnsupportedOperationException("This method is not supported for Order.");
    }

    @Override
    public List<Order> findAll() {
        Connection connection = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            connection = Controller.getConnection();
            String sql = "SELECT * FROM orders";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(
                    resultSet.getInt("id"),
                    new StaffController().findById(resultSet.getInt("staff_id")).get(0),
                    new CustomerController().findById(resultSet.getInt("customer_id")).get(0),
                    resultSet.getDate("date_created"),
                    resultSet.getInt("total_money")
                );

                order.setOrderLines(new OrderLineController().findByOrder(order));
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orders;
    }

}
