package com.ECommerence.DAO;

import com.ECommerence.entity.Category;
import com.ECommerence.entity.Product;
import com.mysql.cj.jdbc.Blob;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Search_Dao {

    enum  category{
        All ,
        name ,
        ID ,
    }

    private  List<Product> result ;
    private String query = "" ;
    private  String query_2 = "" ;

    private String character = "" ;
    private Connection connection =  null ;
    private PreparedStatement preparedStatement =  null ;
    private ResultSet resultset =  null ;
    private  int present_page = 0 ;


    public Search_Dao(String character) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "phamdinhduy2508");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         result=  new ArrayList<>();
        this.character  =  character ;

    }

    public int getPresent_page() {
        return present_page;
    }

    public void setPresent_page(int present_page) {
        this.present_page = present_page;
    }
    public  List<Product> get_data_frompage() {

        return null ;
    }

    public List<Product> getResult() {
        return result;
    }

    public void setResult(List<Product> result) {
        this.result = result;
    }
    public List<Product> Search_result_max_5(){
        List<Product> list =  new ArrayList<>() ;

        try {

            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery(query);

            while (resultset.next()) {
                Product product = new Product();

                product.setBook_id(resultset.getInt(1));
                product.setTitle(resultset.getString(2));
                product.setAuthor(resultset.getString(3));

                product.setPath(resultset.getString("path_image"));


                product.setPrice(Float.valueOf(resultset.getString("price")));


                list.add(product) ;
                if(list.size() == 5) {
                    break;
                }

            }
            setResult(list);

        }catch (Exception e) {

            System.out.println(e.getMessage());

        }
        System.out.println(list.size());

        return  list ;

    }

    public List<Product> Search_Process()  {
            List<Product> list =  new ArrayList<>() ;
            try {

                preparedStatement = connection.prepareStatement(query);
                resultset = preparedStatement.executeQuery(query);

                while (resultset.next()) {
                    Product product = new Product();

                    product.setBook_id(resultset.getInt(1));
                    product.setTitle(resultset.getString(2));
                    product.setAuthor(resultset.getString(3));
                    product.setPath(resultset.getString("path_image"));
                    product.setPRICE(resultset.getString("price"));

                    product.setCategory_id(Integer.valueOf(resultset.getString("category_id")));



                    product.setPrice(Float.valueOf(resultset.getString("price")));


                    list.add(product) ;

                }
                setResult(list);

            }catch (Exception e) {

                System.out.println(e.getMessage());

            }
        System.out.println(list.size());

        return  list ;


    }

    public void Search(String category) {

        switch (category) {
            case "ID": {
                 query = "SELECT * FROM book WHERE book_id LIKE '"+character+"%'" ;
                break;
            }
            case "name":  {
                 query = "SELECT * FROM book WHERE title LIKE '"+character+"%'" ;
                 break;
            }
            case  "All": {
                 query = "SELECT * FROM book WHERE author LIKE '"+character+"%' or title LIKE'" + character+"%'" ;   ;
                 break;
            }
            case "author" : {
                System.out.println(query);
                query = "SELECT * FROM book WHERE author LIKE '"+character+"%'" ;   ;
                break;
            }
        }

    }
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


}
