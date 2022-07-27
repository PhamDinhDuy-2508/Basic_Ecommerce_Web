package com.ECommerence.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "Invoice" , urlPatterns = {"/invoice/*"})
public class Invoice extends HttpServlet {
    @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "http://localhost:8080/ECommerence/invoice?" + req.getQueryString() ;
        getServletContext().setAttribute("invoice_url" , url );

        req.getRequestDispatcher("Check_invoice.jsp").forward(req , resp); ;


    }

}
