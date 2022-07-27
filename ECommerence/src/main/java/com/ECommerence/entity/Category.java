package com.ECommerence.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class Category {
	 private int category_id ; 
	 private String id ;
	 private  String name ;
	public Category(int category_id, String id) {
		super();
		this.category_id = category_id;
		this.id = id;
	}
	public Category() {
		super();
	}

	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

}
