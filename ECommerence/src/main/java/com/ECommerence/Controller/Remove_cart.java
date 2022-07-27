package com.ECommerence.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet (name = "Remove_cart"  , urlPatterns = {"/remove"})

public class Remove_cart extends HttpServlet {
    public  String Cart_remove ;
    public String Cart_ID ;
    public String ID_user ;
    public HttpSession session ;
    private  Cookie[] cookies =  null ;
    private  static  Cookie cookie ;
    private  static Map<String , Cookie> list_Cookies  =  new HashMap<>() ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.Cart_ID =  req.getParameter("book_id") ;
        this.ID_user =  req.getParameter("user_id") ;

        Remove_cart remove_cart =  new Remove_cart(req , resp) ;
        delete_cookies(resp);
        Put_Cookie_to_Session(req);

        req.getRequestDispatcher("Cart.jsp").forward(req , resp); ;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Remove_cart remove_cart =  new Remove_cart() ;
    }
    public Remove_cart() {

    }
    public Remove_cart(HttpServletRequest request , HttpServletResponse response) {

         cookies =  request.getCookies() ;

         session = request.getSession() ;

        try{

            this.list_Cookies =  (Map<String , Cookie>)this.session.getAttribute("List_cookies") ;

            System.out.println(this.list_Cookies.size());
        }
        catch (Exception e) {
            com.ECommerence.entity.Cookie cookie =  new com.ECommerence.entity.Cookie(request) ;
            this.setList_Cookies(cookie.getMap_cookie());
            Put_Cookie_to_Session(request);
        }

    }
    public String getCart_remove() {
        return Cart_remove;
    }

    public void setCart_remove(String cart_remove) {
        Cart_remove = cart_remove;
    }
    public String getID_user() {
        return ID_user;
    }

    public Map<String, Cookie> getList_Cookies() {
        return list_Cookies;
    }

    public void setList_Cookies(Map<String, Cookie> list_Cookies) {
        this.list_Cookies = list_Cookies;

    }

    public void setID_user(String ID_user) {
        this.ID_user = ID_user;
    }
    public void delete_cookies(HttpServletResponse response) {

        String Cookie_ID =  this.ID_user+"_"+this.Cart_ID ;

        try {
            cookie =  list_Cookies.get(Cookie_ID) ;

            cookie.setValue("10");
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            return;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return  ;
        }
    }
    public void Put_Cookie_to_Session(HttpServletRequest request) {

        this.session =  request.getSession() ;
        this.session.setAttribute("book_id_delete" ,  this.Cart_ID);
        this.session.setAttribute("List_cookies" ,  this.list_Cookies);
        this.session.setMaxInactiveInterval(60*3600);


    }
}
