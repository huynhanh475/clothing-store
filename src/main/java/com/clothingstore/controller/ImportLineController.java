/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.controller;

import com.clothingstore.entity.ImportLine;
import com.clothingstore.entity.Product;
import com.clothingstore.entity.Staff;
import com.clothingstore.interfaces.Controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class ImportLineController implements Controller<ImportLine> {

    private ProductController productController = new ProductController();
    private StaffController staffController = new StaffController();
    
    public boolean checkvalid(String code){
        List<Product> product = productController.findById(code);
        if (!product.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean checkvalid(int id){
        List<Staff> stf = staffController.findById(id);
        if (!stf.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean checkvalidimport(int id) throws SQLException{
        List<ImportLine> ipl = findById(id);
        if (!ipl.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public List<ImportLine> findAll() {
        List<ImportLine> importList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;

        try {
            connection = Controller.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM import_line";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ImportLine importline = new ImportLine(
                    rs.getInt("id"),
                    rs.getInt("quantity"),
                    productController.findById(rs.getString("product_code")).get(0),
                    rs.getDate("date_created"),
                    staffController.findById(rs.getInt("staff_id")).get(0)
                );
                importList.add(importline);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return importList;
    }
    
    @Override
    public void insert(ImportLine ipl){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            statement = connection.prepareStatement("INSERT INTO import_line(quantity, product_code, date_created, staff_id) VALUES(?,?,?,?)");
            statement.setInt(1, ipl.getQuantity());
            statement.setString(2, ipl.getProduct().getProdcode());
            statement.setDate(3, ipl.getDate());
            statement.setInt(4, ipl.getPerformer().getId());
            statement.executeUpdate();
            
            ProductController productController = new ProductController();
            Product product = productController.findById(ipl.getProduct().getProdcode()).get(0);
            product.setQuantity(product.getQuantity() + ipl.getQuantity());
            productController.update(product);
        } catch (SQLException ex) {
            Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void delete(ImportLine ipl) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            statement = connection.prepareStatement("DELETE FROM import_line WHERE id=?");
            statement.setInt(1, ipl.getId());
            statement.executeUpdate();
            
            ProductController productController = new ProductController();
            Product product = ipl.getProduct();
            product.setQuantity(product.getQuantity() - ipl.getQuantity());
            productController.update(product);
        } catch (SQLException ex) {
            Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public List<ImportLine> findById(int id) {
        List<ImportLine> importList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = Controller.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM import_line WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ImportLine importline = new ImportLine(
                    rs.getInt("id"),
                    rs.getInt("quantity"),
                    productController.findById(rs.getString("product_code")).get(0),
                    rs.getDate("date_created"),
                    staffController.findById(rs.getInt("staff_id")).get(0)
                );
                importList.add(importline);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportLineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return importList;
    }
    
    @Override
    public void update(ImportLine object) {
        throw new UnsupportedOperationException("Import Line cannot be updated");
    }
    
    @Override
    public List<ImportLine> findById(String code) {
        throw new UnsupportedOperationException("Import Line cannot be found by code");
    }
    
    public List<ImportLine> filterDate(Date datefrom, Date dateto) throws SQLException{
        List<ImportLine> ImportList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        String sql;
        connection = Controller.getConnection();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        String dfrom = df.format(datefrom);
        String dto = df.format(dateto);
        sql = "select * from import_line where date_created>='"+dfrom+"' and date_created<='"+dto+"'";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()){ 
            ImportLine ipl;
            ipl = new ImportLine(resultSet.getInt("id"), resultSet.getInt("quantity"), productController.findById(resultSet.getString("product_code")).get(0), resultSet.getDate("date_created"), staffController.findById(resultSet.getInt("staff_id")).get(0));
            ImportList.add(ipl);
        }
        //end connection
        return ImportList;
    }
        
}