package Checkout_Service;

import Untils.HttpUtils;
import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Cart_element;
import com.ECommerence.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import  com.ECommerence.entity.users ;
import  Test.test_model  ;
import com.google.gson.Gson;

@WebServlet(urlPatterns = {"/api-check-out"})

public class Checkout_Service extends HttpServlet  {
    protected Gson gson =  new Gson() ;
    private Customer customer =  new Customer() ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper =  new ObjectMapper() ;

        req.setCharacterEncoding("UTF-8");
        try {
            customer = (Customer) getServletContext().getAttribute("customer_info") ;
            System.out.println("TOTAL Price :" + customer.getTotal_Price());

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            resp.setContentType("application/json");

            resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8080/ECommerence/api-check-out");
            resp.setHeader("Access-Control-Allow-Methods", "GET");


            String jSon  =  gson.toJson(customer) ;
            PrintWriter printWriter  = resp.getWriter() ;
            printWriter.println(jSon);
        }
        catch (Exception e) {
            System.out.println("MESSAGE" + e.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper =  new ObjectMapper() ;
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter() ;

        try{
            test_model test_model = (Test.test_model) HttpUtils.of(req.getReader()).To_Model(Test.test_model.class) ;

            mapper.writeValue(resp.getOutputStream() ,  test_model);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ;
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper =  new ObjectMapper() ;
        req.setCharacterEncoding("UTF-8");
        users USer = (users) getServletContext().getAttribute("user_info") ;

        try{

            test_model test_model = (Test.test_model) HttpUtils.of(req.getReader()).To_Model(Test.test_model.class) ;
            mapper.writeValue(resp.getOutputStream() ,  test_model);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
