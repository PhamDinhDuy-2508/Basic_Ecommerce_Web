package com.ECommerence.DAO;
import com.ECommerence.entity.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.mysql.cj.jdbc.Blob;

public  class Homepage_Dao implements DAO<Product>  {
	private Connection connection =  null ; 
	private PreparedStatement preparedStatement =   null ; 
	private   ResultSet resultset =  null ;   
	private List<Product> list_Products ;
	private List<Product> list_product_last ;
	public Homepage_Dao() {
		super(); 
		list_Products =  new ArrayList<Product>()  ;
	} 
	
	public void Image_process(Blob  blob , Product product) throws SQLException  ,  IOException{ 
		
		InputStream inputStream =  blob.getBinaryStream() ; 
		
		ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream() ;  
		
		byte[]byte_arr  = new byte[4096] ;   
		int bytes_read = -1 ;  
		
		while ((bytes_read = inputStream.read(byte_arr)) != -1) { 
			
			byteArrayOutputStream.write(byte_arr, 0, bytes_read);  			
        } 
	     byte[] imageBytes = byteArrayOutputStream.toByteArray();
         String base64Image = Base64.getEncoder().encodeToString(imageBytes);
         
         inputStream.close(); 
         byteArrayOutputStream.close(); 
         product.setImage(base64Image);
	}
	@Override
	public Optional<Product> get(long id) {
		return null;
	}

	public List<Product> getList_Products_category_ID( String id) {

		List<Product> products =  new ArrayList<>()  ;
		String query = "select * from book where category_id = " + id;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver") ;
			connection	=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb","root","phamdinhduy2508");
			preparedStatement = connection.prepareStatement(query) ;
			resultset =  preparedStatement.executeQuery(query) ;
			ResultSetMetaData resultSetMetaData = resultset.getMetaData() ;

			while(resultset.next()) {
				Product product =  new Product() ;

				product.setBook_id(resultset.getInt(1)) ;
				product.setTitle(resultset.getString(2)) ;
				product.setAuthor(resultset.getString(3)) ;
				product.setDescription(resultset.getString(4)) ;

				Blob blob = (Blob) resultset.getBlob(5) ;
				Image_process(blob, product) ;

				product.setPrice(resultset.getFloat(6)) ;

				product.setCategory_id(resultset.getInt(9)) ;

				products.add(product) ;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return products;

	}
	@Override
	public List<Product> getAll() { 
		
		List<Product> product_List = new ArrayList<Product>() ; 
		 
        String query = "select * from book";  
       try {
			Class.forName("com.mysql.cj.jdbc.Driver") ;
        	connection	=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb","root","phamdinhduy2508"); 
        	preparedStatement = connection.prepareStatement(query) ;
        	resultset =  preparedStatement.executeQuery(query) ;     
        	ResultSetMetaData resultSetMetaData = resultset.getMetaData() ;



        	while(resultset.next()) { 
        		Product product =  new Product() ; 

        		product.setBook_id(resultset.getInt(1)) ; 
        		product.setTitle(resultset.getString(2)) ;  
        		product.setAuthor(resultset.getString(3)) ;   
        		product.setDescription(resultset.getString(4)) ;   
        		
        		Blob blob = (Blob) resultset.getBlob(5) ;        		
        		Image_process(blob, product) ;
        		
        		product.setPrice(resultset.getFloat(6)) ; 

        		product.setCategory_id(resultset.getInt(9)) ; 
        		
            	product_List.add(product) ;         		        		   
        	}
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		}

		return product_List;
	}
	@Override
	public void Update(Product t, String[] parameter) {
	}
	@Override
	public void Delete(Product t) {
		// TODO Auto-generated method stub
	}
	@Override
	public void Add(Product t) {
		// TODO Auto-generated method stub
	}
	public  List<Product> Lastest_Product() {

		List<Product> last_product  = new ArrayList<>() ;
		String query =  "select * From book where last_update_time =  (Select max(last_update_time) from book) ;" ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "phamdinhduy2508");
			preparedStatement = connection.prepareStatement(query);
			resultset = preparedStatement.executeQuery(query);

			while(resultset.next()) {

				Product product =  new Product() ;
				product.setBook_id(resultset.getInt(1)) ;
				product.setTitle(resultset.getString(2)) ;
				product.setAuthor(resultset.getString(3)) ;
				product.setDescription(resultset.getString(4)) ;

				Blob blob = (Blob) resultset.getBlob(5) ;
				Image_process(blob, product) ;

				product.setPrice(resultset.getFloat(6)) ;

				product.setCategory_id(resultset.getInt(9)) ;

				last_product.add(product) ;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null ;
		}
		return last_product ;

	}
			

}
