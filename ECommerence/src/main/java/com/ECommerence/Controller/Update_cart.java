package com.ECommerence.Controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.ECommerence.entity.users ;

@WebServlet(name = "update_cart" ,  urlPatterns = {"/update_cart"})

public class Update_cart  extends HttpServlet  {
    private  String Book_ID ;
    private  users User_Info =  new users() ;
    private  String Amount ;
    private HttpSession session ;
    private static Cookie cookie ;
    private  Map<String , Cookie> load_cookie =  new HashMap<>();


    public Update_cart() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            this.session = req.getSession(false);

            this.Book_ID = req.getParameter("book_id");
            this.Amount = req.getParameter("cart_amount");
        System.out.println("AMOUNT" + this.Amount);
            this.User_Info = (users) session.getAttribute("user_info");

            Map<String , Cookie> load_cookie = (Map<String, Cookie>) session.getAttribute("map_cookie")  ;

            String Cookie_Name =  this.User_Info.getUser_id()+"_"+this.Book_ID ;
            Cookie cookie = load_cookie.get(Cookie_Name) ;
            System.out.println("check" + Integer.valueOf(cookie.getValue()));
            int i =  Integer.valueOf(Amount)  ;
            cookie.setValue(String.valueOf(i));

            cookie.setMaxAge(60*3600);
            resp.addCookie(cookie);

    }
    public void Update_into_cookie(HttpServletResponse response) {

//             cookie.setValue(String.valueOf(i)) ;
//             cookie.setMaxAge(60*3600);
//             response.addCookie(cookie);

    }
}
