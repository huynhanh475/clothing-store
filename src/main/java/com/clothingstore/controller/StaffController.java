/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothingstore.controller;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.clothingstore.entity.Staff;
import com.clothingstore.interfaces.Controller;
/**
 *
 * @author Avocado
 */
public class StaffController implements Controller<Staff> {
    @Override
    public List<Staff> findAll(){
        List<Staff> StaffList = new ArrayList<>();
        
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = Controller.getConnection();
            // query
            String sql = "select * from staff";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){ 
                Staff stf;
                java.sql.Date bd = resultSet.getDate("birthday");
                java.sql.Date dt = resultSet.getDate("date_started");
                stf = new Staff(resultSet.getInt("id"),resultSet.getString("full_name"), bd, resultSet.getString("phone"), resultSet.getString("mail"), dt, resultSet.getInt("salary"));
                StaffList.add(stf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return StaffList;
    }
    
    @Override
    public void insert(Staff stf){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            // query
            String sql = "insert into staff(full_name, birthday, phone, mail, date_started, salary) values(?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, stf.getFull_name());
            statement.setDate(2, new java.sql.Date(stf.getBirthday().getTime()));
            statement.setString(3, stf.getPhone());
            statement.setString(4, stf.getMail());
            statement.setDate(5, new java.sql.Date(stf.getDate_started().getTime()));
            statement.setInt(6, stf.getSalary());
       
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public void update(Staff stf){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            
            // query
            String sql = "update staff set full_name=?, birthday=?, phone=?, mail=?, date_started=?, salary=? where id=?";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, stf.getFull_name());
            statement.setDate(2, new java.sql.Date(stf.getBirthday().getTime()));
            statement.setString(3, stf.getPhone());
            statement.setString(4, stf.getMail());
            statement.setDate(5, new java.sql.Date(stf.getDate_started().getTime()));
            statement.setInt(6, stf.getSalary());
            statement.setInt(7, stf.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public void delete(Staff stf){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            
            // query
            String sql = "delete from staff where id=?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, stf.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
    }
    
    @Override
    public List<Staff> findById(int id){
        List<Staff> StaffList = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Controller.getConnection();
            
            // query
            String sql = "select * from staff where id = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()){ 
                Staff stf;
                java.sql.Date bd = resultSet.getDate("birthday");
                java.sql.Date dt = resultSet.getDate("date_started");
                stf = new Staff(resultSet.getInt("id"),resultSet.getString("full_name"), bd, resultSet.getString("phone"), resultSet.getString("mail"), dt, resultSet.getInt("salary"));
                StaffList.add(stf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //end connection
        return StaffList;
    }

    @Override
    public List<Staff> findById(String code) {
        // This doesn't do anything as the staff id is not a string
        return null;
    }
}