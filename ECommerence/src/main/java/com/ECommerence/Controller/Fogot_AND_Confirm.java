package com.ECommerence.Controller;

import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Send_Mail;
import com.ECommerence.entity.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

@WebServlet(name = "confirm_and_send_Email" , urlPatterns = {"/confirm"})

public class Fogot_AND_Confirm extends HttpServlet {
    private  String user_account = "" ;
    private String email = ""  ;
    private HttpSession session ;
    private  Send_Mail send_mail  ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Confirm_and_Send.jsp").forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         session =  req.getSession() ;
        user_account =  req.getParameter("User_name")  ;

        String email1   =  req.getParameter("Email") ;

        send_mail =  new Send_Mail(req) ;

        get_Email_from_databases();


        if(this.email.equals(email1)) {


            this.send_mail.setMail_Send(this.email);
            String verify =   String .valueOf(send_mail.create_verify_number(req)) ;

            Thread thread =  new Thread(new Runnable() {
                @Override
                public void run() {
                    send_mail.Send_Email(verify) ;
                }
            }) ;

            thread.start();
            session.setAttribute("verify_number" ,verify) ;
            System.out.println(verify);
            session.setMaxInactiveInterval(60*3600);


            System.out.println("USER ACCOUNT " + user_account);
            session.setAttribute("user_name" , user_account);

            session.setMaxInactiveInterval(60*3600);

            PrintWriter printWriter = resp.getWriter() ;
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            printWriter.print(true) ;

//
        }
        else {

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter printWriter  =  resp.getWriter() ;

            printWriter.println(false);

        }
    }
    public void get_Email_from_databases() {

        USer_DAO uSer_dao =  new USer_DAO() ;

         String user_id = uSer_dao.get_user_id_by_name(user_account) ;


        this.email =  uSer_dao.Get_email_from_user_name(user_id) ;
    }
}