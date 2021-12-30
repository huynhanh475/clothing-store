package com.clothingstore.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.clothingstore.entity.Order;
import com.clothingstore.entity.OrderLine;
import com.clothingstore.entity.Product;
import com.clothingstore.interfaces.Controller;

public class OrderLineController implements Controller<OrderLine> {
    @Override
    public void insert(OrderLine object) {
        Connection connection = null;
        try {
            connection = Controller.getConnection();
            String sql = "INSERT INTO order_line (order_id, product_code, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, object.getId());
            statement.setString(2, object.getProduct().getProdcode());
            statement.setInt(3, object.getQuantity());
            statement.setInt(4, object.getPrice());
            statement.executeUpdate();

            //Substract product's quantity
            ProductController productController = new ProductController();
            Product product = productController.findById(object.getProduct().getProdcode()).get(0);
            product.setQuantity(product.getQuantity() - object.getQuantity());
            productController.update(product);

        } catch (SQLException ex) {
            Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(OrderLine object) {
        throw new UnsupportedOperationException("OrderLine should not be edited after creating as It relates to Product's quantity and Order'price changes.");
    }

    @Override
    public void delete(OrderLine object) {
        Connection connection = null;
        try {
            connection = Controller.getConnection();
            String sql = "DELETE FROM order_line WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, object.getId());
            statement.executeUpdate();
            
            //Add product's quantity
            ProductController productController = new ProductController();
            Product product = productController.findById(object.getProduct().getProdcode()).get(0);
            product.setQuantity(product.getQuantity() + object.getQuantity());
            productController.update(product);

        } catch (SQLException ex) {
            Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<OrderLine> findById(int id) {
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        Connection connection = null;

        try {
            //Prepare connection, statement and result set
            connection = Controller.getConnection();
            String sql = "SELECT * FROM order_line WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            //Iterate through result set and add to list
            while (result.next()) {
                OrderLine orderLine = new OrderLine(result.getInt("id"),
                                                result.getInt("quantity"),
                                                new ProductController().findById(result.getString("product_code")).get(0),
                                                new OrderController().findById(result.getInt("order_id")).get(0),
                                                result.getInt("price"));
                orderLines.add(orderLine);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orderLines;
    }

    @Override
    public List<OrderLine> findById(String code) {
        throw new UnsupportedOperationException("This can't be executed as the OrderLine's id is not a string."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderLine> findAll() {
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        Connection connection = null;

        try {
            //Prepare connection, statement and result set
            connection = Controller.getConnection();
            String sql = "SELECT * FROM order_line";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            //Iterate through result set and add to list
            while (result.next()) {
                OrderLine orderLine = new OrderLine(result.getInt("id"),
                                                result.getInt("quantity"),
                                                new ProductController().findById(result.getString("product_code")).get(0),
                                                new OrderController().findById(result.getInt("order_id")).get(0),
                                                result.getInt("price"));
                orderLines.add(orderLine);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orderLines;
    }

    public List<OrderLine> findByOrder(Order order) {
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        Connection connection = null;

        try {
            //Prepare connection, statement and result set
            connection = Controller.getConnection();
            String sql = "SELECT * FROM order_line WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            ResultSet result = statement.executeQuery();
            //Iterate through result set and add to list
            while (result.next()) {
                OrderLine orderLine = new OrderLine(result.getInt("id"),
                                                result.getInt("quantity"),
                                                new ProductController().findById(result.getString("product_code")).get(0),
                                                new OrderController().findById(result.getInt("order_id")).get(0),
                                                result.getInt("price"));
                orderLines.add(orderLine);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return orderLines;
    }
}