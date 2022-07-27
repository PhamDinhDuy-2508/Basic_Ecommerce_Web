package com.ECommerence.DAO;

import com.ECommerence.entity.Product;
import com.mysql.cj.jdbc.Blob;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class Detail_DAO  {
    private Connection connection =  null ;
    private PreparedStatement  preparedStatement =  null ;
    private ResultSet resultset =  null ;

    public void Image_process(Blob  blob , Product product) throws SQLException  , IOException {

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

    public Detail_DAO() {

    }
    public Product get_Product_By_Id(int ID)  {


        String query = "select * from book where book_id = " + ID ;
        Product product =  new Product() ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            connection	= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb","root","phamdinhduy2508");
            preparedStatement = connection.prepareStatement(query) ;
            resultset =  preparedStatement.executeQuery(query) ;
            ResultSetMetaData resultSetMetaData = resultset.getMetaData() ;
            while (resultset.next()) {
                product.setBook_id(resultset.getInt(1)) ;
                product.setTitle(resultset.getString(2)) ;
                product.setAuthor(resultset.getString(3)) ;
                product.setDescription(resultset.getString(4)) ;

                com.mysql.cj.jdbc.Blob blob = (Blob) resultset.getBlob(5) ;
                Image_process(blob, product) ;

                product.setPrice(resultset.getFloat(6)) ;

                product.setCategory_id(resultset.getInt(9)) ;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null ;
        }
        return product ;
    }
    public List<Product> Relative_Product(String id) {

        String query = "select * from  book where category_id = " + id ;

        List<Product> products =  new ArrayList<>() ;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();

            while (resultset.next()) {

                Product product =  new Product() ;
                product.setBook_id(resultset.getInt(1)) ;
                product.setTitle(resultset.getString(2)) ;
                product.setAuthor(resultset.getString(3)) ;
                product.setDescription(resultset.getString(4)) ;

                com.mysql.cj.jdbc.Blob blob = (Blob) resultset.getBlob(5) ;
                Image_process(blob, product) ;

                product.setPrice(resultset.getFloat(6)) ;

                product.setCategory_id(resultset.getInt(9)) ;
                products.add(product) ;
            }

        } catch (Exception exception) {

            System.out.println(exception.getMessage());
        }

        return  products ;
    }


}
