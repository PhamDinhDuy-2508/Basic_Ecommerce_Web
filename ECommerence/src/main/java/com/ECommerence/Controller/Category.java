package com.ECommerence.Controller;

import com.ECommerence.DAO.HomePage_Dao_Category;
import com.ECommerence.DAO.Homepage_Dao;
import com.ECommerence.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Category"  , urlPatterns = "/category")

public class Category extends HttpServlet {
    private static final long serialVersionUID = 2616818815084011052L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO Get");
        List<Product> checkList =  new ArrayList<>() ;
        String id =  request.getParameter("category_id") ;
        Homepage_Dao homepage_Dao =  new Homepage_Dao() ;

        HomePage_Dao_Category homePage_dao_category =  new HomePage_Dao_Category(2) ;

        if(id.equals("5") == false)  {
            checkList = 	homepage_Dao.getList_Products_category_ID(id)  ;
        }
        else {
            checkList =     homepage_Dao.getAll() ;
        }

        List<com.ECommerence.entity.Category> categoryList  =  homePage_dao_category.getAll();
        List<Product> _lats_Product = homepage_Dao.Lastest_Product() ;
        String name_cate =  homePage_dao_category.get_Name_OF_Category(id) ;
        request.setAttribute("List_Product" ,  checkList);
        request.setAttribute("List_Category" ,  categoryList);
        request.setAttribute("List_last" ,  _lats_Product);
        request.setAttribute("name_category" ,   name_cate);
        request.getRequestDispatcher("Home.jsp").forward(request , response);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
