/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.Customer;
import java.sql.Connection;
import java.sql.Date;
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
public class Customercontroller extends Usercontroller{
    public static List<Customer> findAll(){
        try {
            List<Customer> CustomerList = new ArrayList<>();
            Connection connection = null;
            Statement statement = null;
            
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
            return CustomerList;
        } catch (SQLException ex) {
            Logger.getLogger(Customercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void insert(Customer ctm){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
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
            Logger.getLogger(Customercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void update(int id, String fullname, Date birthday, String phone, String mail){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "update Customer set full_name=?, birthday=?, phone=?, mail=? where id=?";
            statement = connection.prepareCall(sql);
            statement.setString(1, fullname);
            statement.setDate(2, birthday);
            statement.setString(3,phone);
            statement.setString(4, mail);
            statement.setInt(5, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Customercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void delete(int id){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "delete from Customer where id=?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute(); 
        } catch (SQLException ex) {
            Logger.getLogger(Customercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Customer> findbyproperty(int id){
        try {
            List<Customer> CustomerList = new ArrayList<>();
            
            Connection connection = null;
            PreparedStatement statement = null;
            
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
            //end connection
            return CustomerList;
        } catch (SQLException ex) {
            Logger.getLogger(Customercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<Customer> findbyproperty(String phone){
        try {
            List<Customer> CustomerList = new ArrayList<>();
            
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "select * from Customer where phone = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,phone);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()){
                Customer ctm;
                java.sql.Date bd = resultSet.getDate("birthday");
                ctm = new Customer(resultSet.getInt("expenditure"), resultSet.getString("ranking"), resultSet.getInt("id"), resultSet.getString("full_name"), bd, resultSet.getString("phone"), resultSet.getString("mail"));
                CustomerList.add(ctm);
            }
            //end connection
            return CustomerList;
        } catch (SQLException ex) {
            Logger.getLogger(Customercontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
