package com.clothingstore.entity;

public class ActionLine {
    protected int id;
    protected int quantity;
    protected Product code;

    public ActionLine(int id, int quantity, Product code) {
        this.id = id;
        this.quantity = quantity;
        this.code = code;
    }

    public ActionLine(int quantity, Product code) {
        this.quantity = quantity;
        this.code = code;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getCode() {
        return code;
    }

    public void setCode(Product code) {
        this.code = code;
    }
    
}
