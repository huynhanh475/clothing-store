/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.Product;
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
public class Productcontroller {
    
    public static List<Product> findAll(){
        try {
            List<Product> ProductList = new ArrayList<>();
            
            Connection connection = null;
            Statement statement = null;
            String sql;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            sql = "select * from Product order by quantity asc";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                Product prd;
                prd = new Product(resultSet.getString("prodcode"),resultSet.getString("prodname"),resultSet.getString("category"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getString("brand"));
                ProductList.add(prd);
            }
            //end connection
            return ProductList;
        } catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static List<Product> findAll(String cate, String order){
        try {
            List<Product> ProductList = new ArrayList<>();
            
            Connection connection = null;
            Statement statement = null;
            String sql;
            String concat = (cate=="All")? " where quantity=0": " and quantity=0";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            switch (cate) {
                case "Women-clothes":
                    sql = "select * from Product where category='Women-clothes'";
                    break;
                case "Men-clothes":
                    sql = "select * from Product where category='Men-clothes'";
                    break;
                default:
                    sql = "select * from Product";
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
                prd = new Product(resultSet.getString("prodcode"),resultSet.getString("prodname"),resultSet.getString("category"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getString("brand"));
                ProductList.add(prd);
            }
            
            //end connection
            return ProductList;
        } catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void insert(Product prd){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "insert into Product(prodcode, prodname, category, quantity, price, brand) values(?,?,?,0,?,?)";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, prd.getProdcode());
            statement.setString(2, prd.getProdname());
            statement.setString(3, prd.getCategory());
            statement.setInt(4, prd.getPrice());
            statement.setString(5, prd.getBrand());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void update(Product prd, String prodcode){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "update Product set prodname=?, category=?, price=?, brand=? where prodcode=?";
            statement = connection.prepareCall(sql);
            statement.setString(1, prd.getProdname());
            statement.setString(2, prd.getCategory());
            statement.setInt(3, prd.getPrice());
            statement.setString(4, prd.getBrand());
            statement.setString(5, prodcode);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delete(String prodcode){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "delete from Product where prodcode=?";
            statement = connection.prepareCall(sql);
            statement.setString(1, prodcode);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Product> findbyid(String id){
        try {
            List<Product> ProductList = new ArrayList<>();
            
            Connection connection = null;
            Statement statement = null;
            String sql;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            sql = "select * from Product where prodcode = '"+id+"'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                Product prd;
                prd = new Product(resultSet.getString("prodcode"),resultSet.getString("prodname"),resultSet.getString("category"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getString("brand"));
                ProductList.add(prd);
            }
            //end connection
            return ProductList;
        } catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
