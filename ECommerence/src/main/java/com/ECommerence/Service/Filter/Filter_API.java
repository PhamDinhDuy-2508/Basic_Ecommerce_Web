package com.ECommerence.Service.Filter;

import com.ECommerence.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("filter")
public class Filter_API {

    private List<Product> list_product = new ArrayList<>();
    private HttpSession session;

    private List<Product> result = new ArrayList<>() ;


    @GET
    @Path("/result_Filter")
    public Response Result_Filter(@Context HttpServletRequest request) {
        this.list_product.clear();
        session =  request.getSession(true) ;
        this.list_product = (List<Product>) session.getAttribute("filter_result");
        return Response.status(200).entity(this.list_product).build();
    }

    @POST
    @Path("/Filter_price/{price}")

    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)

    public void Filter_Product_price(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("price") String price) {
        session =  request.getSession(true)  ;

        String[] split_res = price.split("_") ;


           this.list_product =  (List<Product>)session.getAttribute("full_result_res") ;

        Filter_Price filter_price =  new Filter_Price(this.list_product ,  split_res[0] , split_res[1]) ;
       filter_price.filter_process();
       session.setAttribute("filter_result" ,  filter_price.filter_result());
       session.setMaxInactiveInterval(60*3600);


    }


}

