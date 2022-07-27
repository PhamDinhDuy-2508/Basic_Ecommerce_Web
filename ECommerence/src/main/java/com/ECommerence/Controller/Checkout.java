package com.ECommerence.Controller;

import com.ECommerence.entity.Cart_element;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ECommerence.entity.Customer;
import com.ECommerence.entity.users ;
import org.apache.commons.lang.ObjectUtils;

@WebServlet(name = "Checkout" ,  urlPatterns = {"/checkout"})


public class Checkout extends HttpServlet {

    private List<Cart_element> cart_list =  new ArrayList<>() ;
    private  String user_name = "" ;
    private HttpSession session  ;
    private HashMap<String , String> province_List =  new HashMap<>() ;
    private users User   ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        user_name = (String) getServletContext().getAttribute("user_name");
        User =  (users) getServletContext().getAttribute("User_info") ;
        Thread thread = (Thread) getServletContext().getAttribute("thread_load_API");

        req.setAttribute("user", true);
        req.setAttribute("user_name", user_name);

        try {
            thread.join();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Customer customer =  new Customer() ;
        customer =  (Customer)getServletContext().getAttribute("customer_info")  ;

        req.getRequestDispatcher("Checkout.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session =  req.getSession() ;

        PrintWriter printWriter =  resp.getWriter() ;
        printWriter.print(false);

    }
    public void load_User_info() {
        return  ;
    }

}

