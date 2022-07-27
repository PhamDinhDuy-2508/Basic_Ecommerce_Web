package com.ECommerence.entity;


public class Cart_element {

    private  String name = "" ;
    private  Book_Order book_order ;

    private  int ID  =  0  ;
    private  String book_ID ;
    private float Price ;
    private String image_bit  ;
    private  String _Title = "" ;
    private  String bit_image ;

    private  Product product ;
    private String User_ID = "" ;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public  Cart_element(Product product , int quantity) {
        this.product =  product ;
//        this.product.setImage("");
        this.product.setDescription("");

        this.amount  =  quantity ;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private  int amount ;
    public Cart_element() {

        this.amount = 1 ;
    }
    public Cart_element(Book_Order book_order) {

        this.amount = 1 ;
        this.book_order =  book_order ;
    }

    public Book_Order getBook_order() {
        return book_order;
    }

    public void setBook_order(Book_Order book_order) {
        this.book_order = book_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBook_ID() {
        this.book_ID = String.valueOf( this.product.getBook_id() );
        return book_ID;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }
    public double get_total_price() {
        return  (double) Math.round(this.Price*amount ) ;

    }

    public float getPrice() {
        this.Price =  this.product.getPrice();
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getImage_bit() {
        return image_bit;
    }

    public void setImage_bit(String image_bit) {
        this.image_bit = image_bit;
    }

    public String getTitle() {

        this._Title =  this.product.getTitle() ;
        this.name = this._Title ;
        return _Title;
    }

    public void setTitle(String title) {
        _Title = title;
        this.name = this._Title ;

    }

    public String getBit_image() {
        this.bit_image =  this.product.getImage();

        return bit_image;
    }

    public void setBit_image(String bit_image) {
        this.bit_image = bit_image;
    }

    public void Increase_amount() {
        this.amount++ ;
    }
    public void Total_Price() {

    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }
    public  void print_test() {
        System.out.println(this.Price + this.amount+this._Title+this.book_ID);
    }
}

