/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.ImportLine;
import com.clothingstore.entity.Product;
import com.clothingstore.entity.Staff;
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
public class Importlinecontroller {
    
    public static boolean checkvalid(String code){
        List<Product> product = ProductModify.findbyid(code);
        if (!product.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean checkvalid(int id){
        List<Staff> stf = StaffModify.findbyid(id);
        if (!stf.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public static List<ImportLine> findAll(){
        List<ImportLine> ImportList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
          
            sql = "select * from import_line";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){ 
                ImportLine ipl;
                ipl = new ImportLine(resultSet.getInt("order_id"), resultSet.getInt("quantity"), ProductModify.findbyid(resultSet.getString("product_code")).get(0), resultSet.getDate("date_imported"), StaffModify.findbyid(resultSet.getInt("staff_id")).get(0));
                ImportList.add(ipl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Importlinecontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Importlinecontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Importlinecontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return ImportList;
    }
    
    public static void insert(ImportLine ipl){
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
            String sql = "insert into import_line(staff_id, product_code, quantity, date_imported) values(?,?,?,?)";
            statement = connection.prepareCall(sql);
            statement.setInt(1, ipl.getPerformer().getId());
            statement.setString(2, ipl.getCode().getProdcode());
            statement.setInt(3, ipl.getQuantity());
            statement.setDate(4, ipl.getDate());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    public static void update_quantity_plus(int quantity, Product prd){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql;
        try {
            try {
                // retrieve the list of staff
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            sql = "update Product set quantity=? where prodcode=?";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, Integer.toString(prd.getQuantity()+quantity));
            statement.setString(2, prd.getProdcode());
            statement.execute();
             
        } catch (SQLException ex) {
            Logger.getLogger(Importlinecontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Importlinecontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Importlinecontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
    }
    
}
