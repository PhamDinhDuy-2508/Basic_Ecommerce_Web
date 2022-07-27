package com.ECommerence.entity;

import java.util.List;

public class Order_ID
{
    private  String Order_ID ;
    private List<String> Book_ID ;
    private  int Quantity = 0 ;
    private  int subtotal = 0 ;

    public Order_ID() {
    }

    public String getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(String order_ID) {
        Order_ID = order_ID;
    }

    public List<String> getBook_ID() {
        return Book_ID;
    }

    public void setBook_ID(List<String> book_ID) {
        Book_ID = book_ID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }


}
