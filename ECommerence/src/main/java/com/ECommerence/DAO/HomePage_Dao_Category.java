package com.ECommerence.DAO;

import com.ECommerence.entity.Category;
import com.ECommerence.entity.Product;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomePage_Dao_Category  implements DAO<Category> {

    private  Connection connection  = null;
    private PreparedStatement preparedStatement = null ;
    private ResultSet resultSet =  null ;
    private  List<Category> categoryList ;
    private  int id ;

    @Override
    public Optional<Category> get(long id) {
        return Optional.empty();
    }
    public HomePage_Dao_Category() {
        super();
        categoryList =  new ArrayList<>() ;
    }
    public HomePage_Dao_Category(int id) {
        super();
        categoryList =  new ArrayList<>() ;
        this.id =  id  ;
    }
    public String get_Name_OF_Category(String id) {
        String name_of_Cate_gory = "" ;
        String query  = "select * from category where category_id =" + id  ;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb","root","phamdinhduy2508") ;
            preparedStatement =  connection.prepareStatement(query) ;
            resultSet = preparedStatement.executeQuery(query) ;
            ResultSetMetaData resultSetMetaData =  resultSet.getMetaData() ;
            while(resultSet.next()) {
                name_of_Cate_gory = resultSet.getString(2) ;
            }
        }catch (Exception e) {
                System.out.println(e.getMessage());
                return "" ;
        }
            return name_of_Cate_gory ;
    }

    @Override
    public List<Category> getAll() {

        List<Category> categories = new ArrayList<>() ;
        String _id  = String.valueOf(this.id) ;
        String query  = "select  * from  category";
        try{
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb","root","phamdinhduy2508") ;
             preparedStatement =  connection.prepareStatement(query) ;
             resultSet = preparedStatement.executeQuery(query) ;
             ResultSetMetaData resultSetMetaData =  resultSet.getMetaData() ;
            while(resultSet.next()) {
                Category category =  new Category() ;
                category.setCategory_id(resultSet.getInt(1));
                category.setName(resultSet.getString(2));

                this.categoryList.add(category) ;
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categoryList;
    }

    @Override
    public void Update(Category category, String[] parameter) {
        return  ;
    }
    @Override
    public void Delete(Category category) {
        return  ;
    }

    @Override
    public void Add(Category category) {
        return  ;
    }
}
