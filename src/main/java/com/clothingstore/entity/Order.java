package com.clothingstore.entity;

import java.sql.Date;

public class Order {
    private int id;
    private Staff performer;
    private Customer customer;
    private Date date;
    private int totalMoney;

    public Order(int id, Staff performer, Customer customer, Date date, int totalMoney) {
        this.id = id;
        this.performer = performer;
        this.customer = customer;
        this.date = date;
        this.totalMoney = totalMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getPerformer() {
        return performer;
    }

    public void setPerformer(Staff performer) {
        this.performer = performer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

}
