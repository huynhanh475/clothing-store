/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clothingstore.entity;

/**
 *
 * @author ACER
 */
public class Product {
    protected String prodcode;
    protected String prodname;
    protected String category;
    protected int quantity;
    protected int price;
    protected String brand;

    public Product(String prodcode, String prodname, String category, int quantity, int price, String brand) {
        this.prodcode = prodcode;
        this.prodname = prodname;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
    }

    public Product(String prodcode, String prodname, String category, int price, String brand) {
        this.prodcode = prodcode;
        this.prodname = prodname;
        this.category = category;
        this.price = price;
        this.brand = brand;
    }

    public Product(String prodname, String category, int price, String brand) {
        this.prodname = prodname;
        this.category = category;
        this.price = price;
        this.brand = brand;
    }
    
    

    public String getProdcode() {
        return prodcode;
    }

    public void setProdcode(String prodcode) {
        this.prodcode = prodcode;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
