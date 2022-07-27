package com.ECommerence.entity;

import org.jboss.weld.util.collections.ArraySet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.*;

public class Customer {
    private  int customer_id = 0 ;
    private  String email = "";
    private  String fullname  = "";
    private  String address  = "" ;
    private  String city  = "" ;
    private  String countty = "";
    private String phone = "" ;
    private String zipcode = "" ;
    private  String password = "" ;
    private Date  register_date ;
    private List<Cart_element> book_orders =  new ArrayList<>() ;
    private Queue<Book_Order> queue_Book_Order = new LinkedList<>() ;
    private  String Account_Name = "" ;
    private  String First_name = "" ;
    private String  Last_name = "" ;
    private  String bit_Arr = "" ;
    private   String Total_Price_test  = "";

    public Customer() {
        super();
        return ;
    }
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getAccount_Name() {
        return Account_Name;
    }

    public void setAccount_Name(String account_Name) {
        Account_Name = account_Name;
    }

    public String getBit_Arr() {
        return bit_Arr;
    }

    public void setBit_Arr(String bit_Arr) {
        this.bit_Arr = bit_Arr;
    }

    public List<Cart_element> getBook_orders() {
//        getBook_orders();

        return book_orders;
    }

    public void setBook_orders(List<Cart_element> book_orders) {
        this.book_orders = book_orders;
        float price_total = 0 ;
        for(Cart_element x : book_orders) {
           price_total+= x.get_total_price();
        }
        setTotal_Price(String.valueOf(price_total));
    }

    public String getTotal_Price() {

        return Total_Price_test;
    }

    public void setTotal_Price(String total_Price) {
        this.Total_Price_test =  total_Price ;
    }

    public Queue<Book_Order> getQueue_Book_Order() {
        return queue_Book_Order;
    }

    public void setQueue_Book_Order(Book_Order book_order) {

        this.queue_Book_Order.add(book_order) ;

    }
}
