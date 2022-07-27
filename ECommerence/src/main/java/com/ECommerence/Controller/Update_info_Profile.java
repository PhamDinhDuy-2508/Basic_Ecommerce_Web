package com.ECommerence.Controller;

import com.ECommerence.DAO.USer_DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.ECommerence.entity.users ;


@WebServlet( name = "Update_info_profile" , urlPatterns = {"/update_info"})

public class Update_info_Profile extends HttpServlet {

    private  String email  ;
    private  String First_name ;
    private  String Last_name ;
    private  String Phone ;
    private String customer_id ;

    private  HttpSession  session ;
    private  users User ;
    private  String Customer_id  ;
    private String address ;
    private HashMap<String , String > address_hash_amap ;

    public Update_info_Profile() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Do Get MEthod");
        try {

            this.email = req.getParameter("email");

            PrintWriter printWriter = resp.getWriter();

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            printWriter.println(check_Email());

        }
        catch (Exception e) {
            System.out.println("Some thing was Wrong");
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session =  req.getSession() ;

        First_name =  req.getParameter("first_name") ;
        Last_name   =  req.getParameter("last_name") ;

        email = req.getParameter("email_new") ;

        Phone =  req.getParameter("phone_number") ;

        String Home_number = req.getParameter("Home_number") ;

        String province = req.getParameter("Province") ;

        String Street  =  req.getParameter("Street") ;
        String City =  req.getParameter("city") ;

        String Country = req.getParameter("country") ;

        this.address  = Home_number+"@"+province+"@"+Street+"@"+City+"@"+Country ;

        this.User = (users)session.getAttribute("user_info") ;


        customer_id =  String.valueOf(User.getUser_id()) ;


        Update_in_database();
    }
    public boolean check_Email() {

        Pattern pattern_email = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern_email.matcher(this.email);

        if (matcher.matches() == true) {
            return true;
        }
        else {
            return false;
        }
    }
    public void Update_in_database() {
        USer_DAO uSer_dao =  new USer_DAO() ;
        uSer_dao.update_customer_info(this.First_name , this.Last_name , this.email , this.Phone, this.customer_id, this.address) ;



    }
}