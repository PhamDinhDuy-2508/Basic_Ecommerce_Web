package com.ECommerence.Controller;

import com.ECommerence.DAO.HomePage_Dao_Category;
import com.ECommerence.DAO.Homepage_Dao;
import com.ECommerence.DAO.USer_DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.ECommerence.entity.Category;
import com.ECommerence.entity.Product;
import com.ECommerence.entity.users;

@WebServlet(name = "Login_doPost"  ,  urlPatterns = {"/login_post"})
public class Login_doPost  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =  req.getSession() ;
        String signal =  req.getParameter("user") ;
        req.setAttribute("user" , false);
        session.invalidate();
        req.getRequestDispatcher("home").forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req , resp);
    }
    public users User_process(String  name , String pass) {
        USer_DAO uSer_dao =  new USer_DAO() ;
        users User =  new users() ;
        User =  uSer_dao.get_user(name ,  pass) ;
        if(User.equals(null) == true) {
            return null ;
        }
        else {
            return User ;
        }
    }

}

