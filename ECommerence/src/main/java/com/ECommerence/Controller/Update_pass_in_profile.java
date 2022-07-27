package com.ECommerence.Controller;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.ECommerence.DAO.USer_DAO ;
import com.ECommerence.entity.users;
import com.google.gson.Gson;

@WebServlet(name = "update_pass_profile" , urlPatterns = {"/update_pass_profile"})
public class Update_pass_in_profile extends HttpServlet {
    private HttpSession session  ;
    private  String user_id  ;
    private  users User =  new users() ;
    private  String new_pass_word  ;
    private Gson gson =  new Gson() ;
    private Cookie cookie  ;

    public Update_pass_in_profile() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        session = request.getSession() ;
        User = (users)session.getAttribute("user_info") ;

        this.new_pass_word = request.getParameter("password") ;
        String password_old  = request.getParameter("password_old") ;
        System.out.println(  password_old +  new_pass_word);

        String json_convert_user = this.gson.toJson(User) ;

        this.user_id = String.valueOf( User.getUser_id() ) ;
        try {

            PrintWriter printWriter = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            if(check_old_pass_is_correct(password_old)) {
                printWriter.print(true);
                update_password_database(request);
                Update_Session_And_Cookie(request,resp);
            }
            else {

                printWriter.print(false);

            }


        }
        catch (Exception e) {
            System.out.println("Some thing was wrong" +
                    "");
            System.out.println( e.getMessage());
        }


    }
    public void  update_password_database(HttpServletRequest request) {

        USer_DAO uSer_dao =  new USer_DAO() ;
        uSer_dao.Update_user_Customer_password(this.user_id , this.new_pass_word);
    }
    public boolean check_old_pass_is_correct(String old_pass) {
        System.out.println(old_pass  +  User.getPasswordString());
        if(old_pass.equals(String.valueOf(User.getPasswordString()))){
            return  true ;
        }
        else {
            return false ;
        }
    }
    public void Update_Session_And_Cookie (HttpServletRequest request , HttpServletResponse response) {

        User.setPasswordString(this.new_pass_word);
        request.getSession().setAttribute("user_info" , User);

        for(Cookie x : request.getCookies()) {
            if(x.getName().equals("pass")) {

                x.setValue(this.new_pass_word);
                x.setMaxAge(60*3600);
                response.addCookie(x);
                break;

            }
        }

    }
}
