package com.clothingstore.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Controller<T> {
    static final String ENDPOINT = "jdbc:mysql://test-java-project.ckb6742uhstk.ap-southeast-1.rds.amazonaws.com:3306/clothingstore";
    //static final String ENDPOINT = "jdbc:mysql://localhost:3306/shop_test"; //For Bo's local testing
    static final String USERNAME = "admin";
    //static final String USERNAME = "root"; //For Bo's local testing
    static final String PASSWORD = "AvocadoChan";
    //static final String PASSWORD = "Bochan06022001"; //For Bo's local testing

    static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(ENDPOINT, USERNAME, PASSWORD);
            return connection;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void insert(T object);

    public void update(T object);

    public void delete(T object);

    public List<T> findById(int id);

    public List<T> findById(String code);

    public List<T> findAll();
}
