package com.ECommerence.Controller;

import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Session;
import com.ECommerence.entity.users;
import org.apache.commons.lang.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "verify" , urlPatterns = {"/verify"})

public class Verify extends HttpServlet  {
    private  String  verify = "" ;
    private HttpSession session ;
    private users User =  new users() ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        session =  req.getSession(true) ;

        this.verify =  (String) session.getAttribute("verify_number") ;

        req.getRequestDispatcher("Verify.jsp") .forward(req , resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session =  req.getSession(true) ;


        this.verify =  (String)session.getAttribute("verify_number") ;
        System.out.println("Verify Number"  + this.verify);

        String verify_code =  req.getParameter("verify_code") ;
        USer_DAO uSer_dao =  new USer_DAO() ;
        String name_of_user = (String)session.getAttribute("user_name") ;
        System.out.println("user_name " + name_of_user);

        users User =  new users() ;

        User = uSer_dao.getUSer_ByName(name_of_user) ;

        if(User != null) {
            System.out.println( "User_name" + User.getUser_name());
        }
        else {
            System.out.println("USER is empty");

        }

        if(verify_code.equals(this.verify)) {

            try{
                session.setAttribute("user_info" ,  User);
                session.setAttribute("signal_Save" ,  "true");

                session.setMaxInactiveInterval(60*3600);

                PrintWriter printWriter = resp.getWriter() ;
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                printWriter.print(true);

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

      }
        else {
            System.out.println("false");
        }

    }

}
