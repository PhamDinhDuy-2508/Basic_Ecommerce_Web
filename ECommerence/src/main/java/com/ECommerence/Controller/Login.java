package com.ECommerence.Controller;

import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Session;
import com.ECommerence.entity.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "Login" , urlPatterns = {"/login"})
public class Login  extends HttpServlet {
    private static final long serialVersionUID = 2616818815084011052L;
    private HttpSession session  ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Load_cookies(req);
        req.getRequestDispatcher("LogIn.jsp").forward(req , resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_name = req.getParameter("user_name") ;
        String pass =  req.getParameter("pass") ;
        users users =  User_process(user_name ,  pass) ;
        HttpSession httpSession  =  req.getSession() ;
        session =  req.getSession() ;
        httpSession.setMaxInactiveInterval(60*3600);

        Thread thread =  new Thread(()->{
            Load_cookies(req);
        }) ;
        thread.start();


        if(User_process(user_name ,  pass).getUser_name().equals("")) {
            req.setAttribute("name" ,  0);
            req.setAttribute("user" ,  user_name)  ;
            req.setAttribute("pass" , pass);
            req.setAttribute( "message" , messgae_error(user_name , pass));
            req.getRequestDispatcher("LogIn.jsp").forward(req , resp);
        }
        else {
            String sig =  "False" ;
            if(req.getParameter("remember_account") != null) {
                httpSession.setAttribute("signal_Save" , req.getParameter("remember_account") );
            }
            else {
                httpSession.setAttribute("signal_Save" , sig);
            }
            httpSession.setAttribute("user_info" ,  users);
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("home");
        }

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
    public String messgae_error(String user , String pass) {
        USer_DAO uSer_dao =  new USer_DAO() ;
        return uSer_dao.message_error(user , pass) ;
    }
    public void Load_cookies( HttpServletRequest request )  {
        Cookie cookie = null ;
        Cookie cookies[] =  null ;
        String password = ""   ;
        cookies =  request.getCookies() ; ;
        String name =  "" ;
        if(cookies.length == 1) {

            request.setAttribute("user" ,  "");
            request.setAttribute("pass" , "");
        }
        else  {
          for(Cookie x : cookies)  {
              if(x.getName().equals("pass")) {
                password =  x.getValue()     ;
              }
              if(x.getName().equals("user_name")) {
                  name = x.getValue()    ;
              }
          }
        }

        request.setAttribute("user" , name);
        request.setAttribute("pass" , password);
    }
    public void Load_cookie_and_Load_Session() {

        com.ECommerence.entity.Cookie cookie_create =  new com.ECommerence.entity.Cookie() ;
        Map<String  , Cookie> map_of_cookie  =  cookie_create.getMap_cookie()  ;

        this.session.setAttribute("List_cookies" ,  map_of_cookie  ) ;

        session.setMaxInactiveInterval(60*3600);

    }

}
