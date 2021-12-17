/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class CustomerModify extends UserModifier{
    public static List<Customer> findAll(){
        List<Customer> CustomerList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
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
            Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void insert(Customer ctm){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "insert into Customer(full_name, birthday, phone, mail, expenditure, ranking) values(?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, ctm.getFull_name());
            statement.setDate(2, new java.sql.Date(ctm.getBirthday().getTime()));
            statement.setString(3, ctm.getPhone());
            statement.setString(4, ctm.getMail());
            statement.setInt(5, ctm.getExpenditure());
            statement.setString(6, ctm.getRanking());
       
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    public static void update(Customer ctm){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "update Customer set full_name=?, birthday=?, phone=?, mail=? where id=?";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, ctm.getFull_name());
            statement.setDate(2, new java.sql.Date(ctm.getBirthday().getTime()));
            statement.setString(3, ctm.getPhone());
            statement.setString(4, ctm.getMail());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    
    public static void delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "delete from Customer where id=?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    public static List<Customer> findbyid(int id){
        List<Customer> CustomerList = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "select * from Customer where id = ?";
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
            Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    public static String ranking(int amount){
        int copper_rank = 1000000;
        int silver_rank = 2000000;
        int gold_rank = 3000000;
        
        if (amount >0 && amount<copper_rank){
            return "copper";
        }
        else if (amount>=copper_rank && amount<silver_rank){
            return "silver";
        }
        else{
            return "gold_rank";
        } 
    }
    
    public static void update_rank (int id, int total_amount){
        
        
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement takeout_statement = null;
        
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            
            String sql_takeout = "select * from Customer where id =?";
            takeout_statement = connection.prepareCall(sql_takeout);
            takeout_statement.setInt(1,id);
            ResultSet resultSet = takeout_statement.executeQuery();
            
            int current_amount = resultSet.getInt("expenditure");
            int new_amount = current_amount + total_amount;
            String ranking = ranking(new_amount);
            
            String sql = "update Customer set expenditure = ?, ranking=? where id=?";
            statement = connection.prepareCall(sql);
            
            statement.setInt(1, new_amount);
            statement.setString(2, ranking);
            statement.setInt(3,id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    
    
}
