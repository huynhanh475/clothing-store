/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.Customer;
import com.clothingstore.interfaces.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class CustomerController implements Controller<Customer>{

    @Override
    public List<Customer> findAll(){
        List<Customer> CustomerList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        
        try {

            connection = Controller.getConnection();

            // query
            String sql = "select * from customer";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){ 
                Customer ctm;
                java.sql.Date bd = resultSet.getDate("birthday");
                ctm = new Customer(resultSet.getInt("expenditure"), resultSet.getString("ranking"), resultSet.getInt("id"), resultSet.getString("full_name"), bd, resultSet.getString("phone"), resultSet.getString("mail"));
                CustomerList.add(ctm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return CustomerList;
    }
    
    @Override
    public void insert(Customer ctm){

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            // query
            String sql = "insert into customer(full_name, birthday, phone, mail, expenditure, ranking) values(?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, ctm.getFull_name());
            statement.setDate(2, new java.sql.Date(ctm.getBirthday().getTime()));
            statement.setString(3, ctm.getPhone());
            statement.setString(4, ctm.getMail());
            statement.setInt(5, ctm.getExpenditure());
            statement.setString(6, ctm.getRanking());
       
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public void update(Customer ctm){

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();

            // query
            String sql = "update customer set full_name=?, birthday=?, phone=?, mail=?, ranking=?, expenditure=? where id=?";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, ctm.getFull_name());
            statement.setDate(2, new java.sql.Date(ctm.getBirthday().getTime()));
            statement.setString(3, ctm.getPhone());
            statement.setString(4, ctm.getMail());
            statement.setString(5, ctm.getRanking());
            statement.setInt(6, ctm.getExpenditure());
            statement.setInt(7, ctm.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public void delete(Customer ctm){

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            // query
            String sql = "delete from customer where id=?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, ctm.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public List<Customer> findById(int id){
        List<Customer> CustomerList = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            // query
            String sql = "select * from customer where id = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()){ 
                Customer ctm;
                java.sql.Date bd = resultSet.getDate("birthday");
                ctm = new Customer(resultSet.getInt("expenditure"), resultSet.getString("ranking"), resultSet.getInt("id"), resultSet.getString("full_name"), bd, resultSet.getString("phone"), resultSet.getString("mail"));
                CustomerList.add(ctm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return CustomerList;
    }

    @Override //Instead of find by code, find by Customer's phone, which is also unique
    public List<Customer> findById(String phone) {
        try {
            List<Customer> customerList = new ArrayList<>();
            
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = Controller.getConnection();
            
            // query
            String sql = "select * from Customer where phone = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,phone);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()){
                Customer ctm;
                java.sql.Date bd = resultSet.getDate("birthday");
                ctm = new Customer(resultSet.getInt("expenditure"), resultSet.getString("ranking"), resultSet.getInt("id"), resultSet.getString("full_name"), bd, resultSet.getString("phone"), resultSet.getString("mail"));
                customerList.add(ctm);
            }
            //end connection
            return customerList;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
