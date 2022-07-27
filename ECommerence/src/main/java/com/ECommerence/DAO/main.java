package com.ECommerence.DAO;

import java.util.HashMap;
import java.util.List;

import API.Location_API;
import com.ECommerence.entity.Book_Order;
import com.ECommerence.entity.Category;
import com.ECommerence.entity.Product;
import com.google.gson.Gson;

public class main {

	public static void main(String[] args) {
//		Homepage_Dao homepage_Dao =  new Homepage_Dao() ;
//		HomePage_Dao_Category category = new  HomePage_Dao_Category() ;
//		List<Category> check = category.getAll() ;
//		Location_API location_api =  new Location_API() ;
//		HashMap<String , String> hashMap =  new HashMap<>() ;
//
//		location_api.Get_Location_API("https://provinces.open-api.vn/api/" );
//
//		System.out.println(check.size());
		Order_DAO order_dao =  new Order_DAO() ;
		Book_Order book_order =  order_dao.get_Book_Order_List("168323 " , "2022-06-25 21:47:03") ;
		Gson gson = new Gson() ;

		String check  =  gson.toJson(book_order) ;

		System.out.println(book_order.getOrder_detail_list().size());
		System.out.println(check);

	}

}
