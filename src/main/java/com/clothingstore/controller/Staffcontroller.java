/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothingstore.controller;

import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.clothingstore.entity.Staff;
/**
 *
 * @author Avocado
 */
public class Staffcontroller extends Usercontroller{
    public static List<Staff> findAll(){
        try {
            List<Staff> StaffList = new ArrayList<>();
            
            Connection connection = null;
            Statement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
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
            //end connection
            return StaffList;
        } catch (SQLException ex) {
            Logger.getLogger(Staffcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void insert(Staff stf){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "insert into Staff(full_name, birthday, phone, mail, date_started, salary) values(?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, stf.getFull_name());
            statement.setDate(2, new java.sql.Date(stf.getBirthday().getTime()));
            statement.setString(3, stf.getPhone());
            statement.setString(4, stf.getMail());
            statement.setDate(5, new java.sql.Date(stf.getDate_started().getTime()));
            statement.setInt(6, stf.getSalary());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Staffcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void update(int id, String fullname, Date birthday, String phone, String mail, Date datestarted, int salary){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "update Staff set full_name=?, birthday=?, phone=?, mail=?, date_started=?, salary=? where id=?";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, fullname);
            statement.setDate(2, birthday);
            statement.setString(3, phone);
            statement.setString(4, mail);
            statement.setDate(5, datestarted);
            statement.setInt(6, salary);
            statement.setInt(7, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Staffcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delete(int id){
        try {
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
            // query
            String sql = "delete from Staff where id=?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Staffcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Staff> findbyid(int id){
        try {
            List<Staff> StaffList = new ArrayList<>();
            
            Connection connection = null;
            PreparedStatement statement = null;
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001");
            
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
            //end connection
            return StaffList;
        } catch (SQLException ex) {
            Logger.getLogger(Staffcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}