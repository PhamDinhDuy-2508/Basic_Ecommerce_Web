package com.ECommerence.Service;


import com.ECommerence.DAO.Order_DAO;
import com.ECommerence.entity.Book_Order;
import com.ECommerence.entity.Customer;
import com.ECommerence.entity.Order_detail;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.TextFormat;
import com.opensymphony.module.sitemesh.Config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("invoice_service")

public class Invoice_Service {
    private Order_DAO order_dao =  new Order_DAO() ;
    private Customer customer =  new Customer() ;
    private HttpSession session ;
    private Book_Order Prsent_Order ;

    @GET
    @Path("/{order_id}")
    @Produces("application/json")

    public Response do_get(@Context HttpServletRequest request , @Context HttpServletResponse response  ,  @PathParam("order_id") String order_id) {
        session =  request.getSession(true) ;
        customer = (Customer) session.getServletContext().getAttribute("customer_info");
//
        this.Prsent_Order =  order_dao.get_Book_Order_List("168323" , "103") ;

        Gson gson =  new Gson() ;
        String S =  gson.toJson(Prsent_Order) ;

        JsonObject convertedObject = new Gson().fromJson(S, JsonObject.class);

        session.getServletContext().setAttribute("order_detail"  ,  Prsent_Order);

        return Response.status(200).entity(Prsent_Order).build() ;

    }
    @PUT
    public void doPut(){
    return  ;
    }

    @DELETE
    public  void doDelete() {
        return  ;
    }

}
