package com.ECommerence.entity;

import javax.ws.rs.Produces;

public class Order_detail {

    private String  order_id ;
    private Product _Products_  ;
    private int quantity ;
    private double Subtotal ;

    public Order_detail() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Order_detail(Product _products, int quantity, double subtotal) {

        this._Products_ = _products;
        this.quantity = quantity;
        Subtotal = subtotal;
    }

    public Product get_Products_() {
        return _Products_;
    }

    public void set_Products_(Product _Products_) {
        this._Products_ = _Products_;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }
}
