package com.ECommerence.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;

import com.ECommerence.DAO.USer_DAO;
import  com.ECommerence.entity.users ;
@WebServlet(name = "Register" ,urlPatterns = {"/register"})
public class Register  extends HttpServlet {
    private static final long serialVersionUID = 2616818815084011052L;
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Register_form.jsp").forward(req , resp);
    }
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USer_DAO uSer_dao =  new USer_DAO()  ;

        confirm_pass(req , resp);

        if(uSer_dao.check_User_name_exist(req.getParameter("username")) == true) {

            req.setAttribute("signal" ,  true);
            add_new_user( req.getParameter("username")  ,  req.getParameter("email")  ,  req.getParameter("password") );
            HttpSession httpSession = req.getSession() ;
            users User =  uSer_dao.getUSer_ByName(req.getParameter("username")) ;
            httpSession.setAttribute("user_info" ,  User);
            resp.sendRedirect("home");

        }
        else {
            ArrayList<String> arrayList = new ArrayList() ;
            arrayList.add(req.getParameter("username")) ;
            arrayList.add(req.getParameter("email")) ;
            arrayList.add(req.getParameter("password")) ;
            req.setAttribute("signal" ,  false);
            req.setAttribute("message" ,  "username is exist");
            load_data(arrayList ,  req , resp);
        }
    }
    public void add_new_user(String name , String pass , String email) {
        USer_DAO uSer_dao = new USer_DAO() ;
        uSer_dao.Create_user(name ,  pass , email);
    }
    public void confirm_pass( HttpServletRequest request ,  HttpServletResponse response ) throws  ServletException , IOException {
        ArrayList<String> arrayList = new ArrayList() ;
        arrayList.add(request.getParameter("username")) ;
        arrayList.add(request.getParameter("email")) ;
        arrayList.add(request.getParameter("password")) ;

        if(!request.getParameter("password").equals(request.getParameter("confirm_password"))) {
            request.setAttribute("signal" ,  false);
            request.setAttribute("message" ,  "confirm is incorrect");
            load_data(arrayList ,  request , response);
        }
    }
    public void load_data(ArrayList arrayList , HttpServletRequest request , HttpServletResponse response)   throws ServletException, IOException  { {

            request.setAttribute("username" ,  arrayList.get(0) );
            request.setAttribute("email"  ,  arrayList.get(1));
            request.setAttribute("pass" , arrayList.get(2));

            request.getRequestDispatcher("Register_form.jsp").forward(request , response);
        }
    }
}

