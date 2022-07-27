package com.ECommerence.Service;

import com.ECommerence.entity.Book_Order;
import com.ECommerence.entity.Customer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import  Test.test_model  ;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONArray;


@Path("api_detail")
public class Detail_service {

    @GET
    @Produces("application/json")
    public Response GetAll() throws JSONException {
        JSONArray jsonArray =  new JSONArray() ;
        test_model Test =  new test_model("123" ,  "345") ;
        Gson gson =  new Gson() ;
        String jSon  =  gson.toJson(Test) ;

        return Response.status(200).entity(Test).build();
    }
}