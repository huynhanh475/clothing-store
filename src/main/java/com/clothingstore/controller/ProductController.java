/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.Product;
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
public class ProductController implements Controller<Product> {
    @Override
    public List<Product> findAll(){
        List<Product> ProductList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
        try {
            connection = Controller.getConnection();
          
            sql = "select * from product order by quantity asc";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){ 
                Product prd;
                prd = new Product(resultSet.getString("prod_code"),resultSet.getString("prod_name"),resultSet.getString("category"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getString("brand"));
                ProductList.add(prd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return ProductList;
    }
    
    public List<Product> findAll(String cate, String order){
        List<Product> ProductList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
        String concat = (cate=="All")? " where quantity=0": " and quantity=0";
        try {
            connection = Controller.getConnection();
            
            switch (cate) {
                case "Women-clothes":
                    sql = "select * from product where category='Women-clothes'";
                    break;
                case "Men-clothes":
                    sql = "select * from product where category='Men-clothes'";
                    break;
                default:
                    sql = "select * from product"; 
                    break;
            }
            
            switch (order) {
                case "Out of stock":
                    sql = sql + concat;
                    break;
                case "Descending":
                    sql = sql + " order by quantity desc";
                    break;
                default:
                    sql = sql + " order by quantity asc"; 
                    break;
            }

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){ 
                Product prd;
                prd = new Product(resultSet.getString("prod_code"),resultSet.getString("prod_name"),resultSet.getString("category"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getString("brand"));
                ProductList.add(prd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return ProductList;
    }
    
    @Override
    public void insert(Product prd){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            
            // query
            String sql = "insert into product(prod_code, prod_name, category, quantity, price, brand) values(?,?,?,0,?,?)";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, prd.getProdcode());
            statement.setString(2, prd.getProdname());
            statement.setString(3, prd.getCategory());
            statement.setInt(4, prd.getPrice());
            statement.setString(5, prd.getBrand());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public void update(Product prd){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            
            // query
            String sql = "update product set prod_name=?, category=?, quantity=?, price=?, brand=? where prod_code=?";
            statement = connection.prepareCall(sql);
            statement.setString(1, prd.getProdname());
            statement.setString(2, prd.getCategory());
            statement.setInt(3, prd.getQuantity());
            statement.setInt(4, prd.getPrice());
            statement.setString(5, prd.getBrand());
            statement.setString(6, prd.getProdcode());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public void delete(Product prod){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            
            // query
            String sql = "delete from product where prod_code=?";
            statement = connection.prepareCall(sql);
            statement.setString(1, prod.getProdcode());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public List<Product> findById(String code){
        List<Product> ProductList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
        try {
            connection = Controller.getConnection();

            sql = "select * from product where prod_code = '"+ code +"'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){ 
                Product prd;
                prd = new Product(resultSet.getString("prod_code"),resultSet.getString("prod_name"),resultSet.getString("category"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getString("brand"));
                ProductList.add(prd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return ProductList;
    }

    @Override
    public List<Product> findById(int id) {
        // This doesn't do anything as the int id is not used in this class
        return null;
    }
}
