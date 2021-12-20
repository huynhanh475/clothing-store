/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.entity;

import java.sql.Date;

/**
 *
 * @author ACER
 */
public class Customer extends User {
    
    protected int expenditure;
    protected String ranking;
    protected int id;

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
}
