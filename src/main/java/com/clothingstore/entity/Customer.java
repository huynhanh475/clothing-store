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
    public Boolean updateRank(){
        int copper_rank = 1000000;
        int silver_rank = 2000000;
        int gold_rank = 3000000;
        String newRank = "";
        
        if (this.expenditure >= gold_rank){
            newRank = "GOLD";
        }
        else if (this.expenditure >= silver_rank){
            newRank = "SILVER";
        }
        else if (this.expenditure >= copper_rank){
            newRank = "BRONZE";
        }
        //Check if the rank is changed
        if (!newRank.equals(this.ranking)){
            this.ranking = newRank;
            return true;
        }
        return false;
    }
}
