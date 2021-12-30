package com.clothingstore.entity;

public class ActionLine {
    protected int id;
    protected int quantity;
    protected Product product;

    public ActionLine(int id, int quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public ActionLine(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    
}
