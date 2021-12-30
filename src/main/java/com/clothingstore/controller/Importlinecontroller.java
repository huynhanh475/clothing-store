/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.ImportLine;
import com.clothingstore.entity.Product;
import com.clothingstore.entity.Staff;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class Importlinecontroller {
    
    public static boolean checkvalid(String code){
        List<Product> product = Productcontroller.findbyid(code);
        if (!product.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean checkvalid(int id){
        List<Staff> stf = Staffcontroller.findbyid(id);
        if (!stf.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean checkvalidimport(int id) throws SQLException{
        List<ImportLine> ipl = Importlinecontroller.findAll(id);
        if (!ipl.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public static List<ImportLine> findAll() throws SQLException{
        List<ImportLine> ImportList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
          
        sql = "select * from import_line";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
            
        while (resultSet.next()){ 
            ImportLine ipl;
            ipl = new ImportLine(resultSet.getInt("order_id"), resultSet.getInt("quantity"), Productcontroller.findbyid(resultSet.getString("product_code")).get(0), resultSet.getDate("date_imported"), Staffcontroller.findbyid(resultSet.getInt("staff_id")).get(0));
            ImportList.add(ipl);
        }
        //end connection
        return ImportList;
    }
    
    public static void insert(ImportLine ipl) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
        String sql = "insert into import_line(staff_id, product_code, quantity, date_imported) values(?,?,?,?)";
        statement = connection.prepareCall(sql);
        statement.setInt(1, ipl.getPerformer().getId());
        statement.setString(2, ipl.getCode().getProdcode());
        statement.setInt(3, ipl.getQuantity());
        statement.setDate(4, ipl.getDate());
        statement.execute();      
    }
    
    public static void update_quantity(int quantity, Product prd, String choice) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        String sql;
        
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
        sql = "update Product set quantity=? where prodcode=?";
        statement = connection.prepareCall(sql);
        if (choice=="insert"){
            statement.setString(1, Integer.toString(prd.getQuantity()+quantity));
        }
        if (choice=="delete")
        {
            statement.setString(1, Integer.toString(prd.getQuantity()-quantity));
        }
        statement.setString(2, prd.getProdcode());
        statement.execute();
    }
    
    public static void delete(int id) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
        String sql = "delete from import_line where order_id=?";
        statement = connection.prepareCall(sql);
        statement.setString(1, Integer.toString(id));
        statement.execute();
    }
    
    
    public static List<ImportLine> findAll(int id) throws SQLException{
        List<ImportLine> ImportList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
   
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
          
        sql = "select * from import_line where order_id="+Integer.toString(id);
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
            
        while (resultSet.next()){ 
            ImportLine ipl;
            ipl = new ImportLine(resultSet.getInt("order_id"), resultSet.getInt("quantity"), Productcontroller.findbyid(resultSet.getString("product_code")).get(0), resultSet.getDate("date_imported"), Staffcontroller.findbyid(resultSet.getInt("staff_id")).get(0));
            ImportList.add(ipl);
        }
        //end connection
        return ImportList;
    }
    
    
    public static List<ImportLine> filterdate(Date datefrom, Date dateto) throws SQLException{
        List<ImportLine> ImportList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        String dfrom = df.format(datefrom);
        String dto = df.format(dateto);
        sql = "select * from import_line where date_imported>='"+dfrom+"' and date_imported<='"+dto+"'";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
            
        while (resultSet.next()){ 
            ImportLine ipl;
            ipl = new ImportLine(resultSet.getInt("order_id"), resultSet.getInt("quantity"), Productcontroller.findbyid(resultSet.getString("product_code")).get(0), resultSet.getDate("date_imported"), Staffcontroller.findbyid(resultSet.getInt("staff_id")).get(0));
            ImportList.add(ipl);
        }
        //end connection
        return ImportList;
    }
      
}
