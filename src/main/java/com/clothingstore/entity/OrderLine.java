package com.clothingstore.entity;

public class OrderLine extends ActionLine {
    private Order order;
    private int price;

    public OrderLine(int id, int quantity, Product code, Order order, int price) {
        super(id, quantity, code);
        this.order = order;
        this.price = price;
    }

    public OrderLine(int quantity, Product product, Order order, int price) {
        super(quantity, product);
        this.order = order;
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
