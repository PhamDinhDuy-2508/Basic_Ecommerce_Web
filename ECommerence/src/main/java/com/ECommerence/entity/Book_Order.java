package com.ECommerence.entity;

import com.ECommerence.DAO.USer_DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book_Order {
    private int order_id  ;

    private String shipping_address  ;

    private  String Payment_method  ;
    private    String receipt_phone ;
    private String receipt_name ;
    private String Post_code ;
    private  String Shipping_details ;
    private String Order_date  ;

    private List<Order_detail> order_detail_list ;
    private String user_id  ;
    private String total ;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(String order_date) {
        Order_date = order_date;
    }

    public Book_Order() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getPayment_method() {
        return Payment_method;
    }

    public void setPayment_method(String payment_method) {
        Payment_method = payment_method;
    }

    public String getReceipt_phone() {
        return receipt_phone;
    }

    public void setReceipt_phone(String receipt_phone) {
        this.receipt_phone = receipt_phone;
    }

    public String getReceipt_name() {
        return receipt_name;
    }

    public void setReceipt_name(String receipt_name) {
        this.receipt_name = receipt_name;
    }

    public String getPost_code() {
        return Post_code;
    }

    public void setPost_code(String post_code) {
        Post_code = post_code;
    }

    public String getShipping_details() {
        return Shipping_details;
    }

    public void setShipping_details(String shipping_details) {
        Shipping_details = shipping_details;
    }

    public List<Order_detail> getOrder_detail_list() {


        return order_detail_list;
    }

    public void setOrder_detail_list(List<Order_detail> order_detail_list) {
        this.order_detail_list = order_detail_list;
    }
}
