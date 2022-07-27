package com.ECommerence.entity;

import java.sql.Date;

import com.mysql.cj.jdbc.Blob;


public class Product {  
	private String signalString  = "product" ; 
	
	private int book_id ;  
	private String title ;
	private String author ;  
	private String description ; 
	private String isbn ; 
	private String image ; 
	private float price;
	private String Path ;
	private  String PRICE ;
	private int category_id ;
	private  int Quatity ;
	public Product(int book_id, String title, String author, String description, String isbn, String image, float price,
			Date publish_date, Date last_update_time, int category_id) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;

		this.category_id = category_id;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String PRICE) {
		this.PRICE = PRICE;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Product() {
		super();
	}
	public int getBook_id() {

		return  book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price ;
	}

	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


}
