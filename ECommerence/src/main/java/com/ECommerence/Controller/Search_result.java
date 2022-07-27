package com.ECommerence.Controller;

import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Product;
import com.ECommerence.entity.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@WebServlet("/search_result/*")

public class Search_result extends HttpServlet {

    private  HttpSession session ;

    private List<Product> result = new ArrayList<>() ;
    ;private  users User =  new users() ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session =  req.getSession(true) ;
        String url =  "http://localhost:8080/ECommerence/search_result?"+ req.getQueryString() ;

        try {
            String signal = (String) session.getAttribute("signal_Save") ;
            User = (users)session.getAttribute("user_info") ;
            USer_DAO uSer_dao  =  new USer_DAO() ;

            getServletContext().setAttribute("user_name"  ,  User.getUser_name());
            getServletContext().setAttribute("Signal" ,  signal);
            getServletContext().setAttribute("User_info" , User);



            if(signal.equals("true")) {
                Set_AND_response_Cookie(User.getUser_name() , User.getPasswordString() ,signal , resp)  ;
            }
            if(signal.equals("False")) {
                Set_AND_response_Cookie(User.getUser_name() , User.getPasswordString() ,"False" , resp)  ;
            }
            load_user(true ,  req , resp , User) ;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            load_user(false , req ,resp , User) ;
        }
        req.getRequestDispatcher("Search_result.jsp") .forward(req , resp);

    }
    public void Set_AND_response_Cookie(String name , String pass , String sig , HttpServletResponse response) throws  ServletException, IOException {
        if (!sig.equals("true")) {
            Cookie cookie_user_name = new Cookie("user_name", name);
            cookie_user_name.setMaxAge(60*3600);
            response.addCookie(cookie_user_name);

        }
        else {
            Cookie cookie_pass = new Cookie("pass", pass);
            Cookie cookie_user_name = new Cookie("user_name", name);

            cookie_user_name.setMaxAge(60*3600);
            cookie_pass.setMaxAge(60*3600);

            response.addCookie(cookie_pass);
            response.addCookie(cookie_user_name);

        }
        Cookie cookie_User_id =  new Cookie("user_id" , String.valueOf(User.getUser_id())) ;
        cookie_User_id.setMaxAge(60*3600);
        response.addCookie(cookie_User_id);

    }
    public void load_user(boolean signal , HttpServletRequest request , HttpServletResponse response  , users Users) {

        if(!signal) {
            request.setAttribute("user" , false);
        }
        else {

            request.setAttribute("user" , true);

            request.setAttribute("user_name" ,  Users.getUser_name());

            request.setAttribute("user_id" ,  Users.getUser_id());

            Load_cookie_in_cart(String.valueOf(Users.getUser_id()) , response , request);

        }
    }
    public void Load_cookie_in_cart(String ID , HttpServletResponse response , HttpServletRequest request) {
        session =  request.getSession()  ;


        Cookie cookie = null;
        HashMap<String  ,  Integer > product_from_Cookies = new HashMap<>();

        Cookie cookie1[] = null;

        cookie1 = request.getCookies();


        Cookie[] finalCookie = cookie1;
        for (Cookie x : finalCookie) {
            if ((x.getName().equals("user_name") == false) && (x.getName().equals("JSESSIONID") == false) && x.getName().equals("pass") == false) {
                String temp_ID =  x.getName().split("_")[0] ;
                if(temp_ID.equals(ID)) {
                    product_from_Cookies.put(x.getName().split("_")[1] ,Integer.valueOf(x.getValue()) ) ;
                }
            }
        }
        System.out.println(product_from_Cookies.size());

        request.setAttribute("amount" ,  product_from_Cookies.size());
        session.setAttribute("Product_from_cookies" , product_from_Cookies);
        session.setAttribute("amount" , product_from_Cookies.size());
        session.setMaxInactiveInterval(60*3600);


    }
    public void Amount_of_product(HttpServletRequest  request , HttpServletResponse response) {

        Cookie cookie = null;
        HashMap<String  ,  Integer >  product_from_Cookies = new HashMap<>();
        Cookie cookie1[] = null;

        cookie1 = request.getCookies() ;
    }

}
