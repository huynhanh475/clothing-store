/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class Customer extends User {
    
    private int expenditure;
    private String ranking;
    private int id;

    public Customer(int expenditure, String ranking, int id, String full_name, Date birthday, String phone, String mail) {
        super(full_name, birthday, phone, mail);
        this.expenditure = expenditure;
        this.ranking = ranking;
        this.id = id;
    }

    public Customer(int expenditure, String ranking, String full_name, Date birthday, String phone, String mail) {
        super(full_name, birthday, phone, mail);
        this.expenditure = expenditure;
        this.ranking = ranking;
    }
    

    public int getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(int expenditure) {
        this.expenditure = expenditure;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public static void update_rank (int id, int total_amount) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement takeout_statement = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_test", "root", "Bochan06022001"); 
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
    }
}
