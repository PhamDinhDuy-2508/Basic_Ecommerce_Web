package com.ECommerence.Service.Search;


import com.ECommerence.entity.Product;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Path("Search")



public class Search_API {

    private List<Product> result   ;
    private HttpSession session ;

    private int present_page = 0 ;

    private List<Product> full_result  ;

    public Search_API() {


    }

    @GET

    @Path("/result")

    public Response Search_result(@Context HttpServletRequest request) {
        session =  request.getSession(true) ;

        this.result = (List<Product>) session.getAttribute("result") ;

        Gson gson =  new Gson() ;
        String check =  gson.toJson(result) ;

        return Response.status(200).entity(check).build() ;
    }


    @GET

    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)

    @Path("/search_result/{page}")

    public Response Page_data(@Context HttpServletRequest request , @PathParam("page") String page) {

        int max_amount = 8 ;


        session =  request.getSession(true) ;

        this.full_result = (List<Product>) session.getAttribute("full_result_res") ;

        Gson gson =  new Gson() ;
        String check =  gson.toJson(full_result) ;


        int Start_pos = max_amount*(Integer.valueOf(page)-1) ;

        int End_pos = max_amount*Integer.valueOf(page) ;

        if(End_pos >= this.full_result.size()) {
            End_pos =  this.full_result.size() ;
        }

        List<Product> list_page = new ArrayList<>() ;
        for (int i =  Start_pos ; i < End_pos ; i++) {
            System.out.println(this.full_result.get(i).getPrice()) ;
             list_page.add(full_result.get(i)) ;
        }

        return  Response.status(200).entity(list_page).build() ;

    }
    @GET

    
    @Path("/result_all")


    public Response Search_result_all(@Context HttpServletRequest request) {
        
        session =  request.getSession(true) ;

        this.full_result = (List<Product>) session.getAttribute("full_result_res") ;

        Gson gson =  new Gson() ;
        String check =  gson.toJson(full_result) ;
        System.out.println(check);



        return Response.status(200).entity(this.full_result).build() ;
    }


    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)

    @Path("/process")
    public Response doPost(@Context HttpServletResponse response , @Context HttpServletRequest  request , Search_signal search_signal) {


        session =  request.getSession(true) ;

        Gson gson =  new Gson() ;
        System.out.println(search_signal.getName());

//
//       try{
//           this.result = (List<Product>) session.getAttribute("result") ;
//       }
//       catch (Exception e) {
//           System.out.println(e.getMessage());
//
//       }
        this.result =  new ArrayList<>() ;
        System.out.println(search_signal.getCate());


        switch (search_signal.getCate()){
            case "Name" :{
                if(search_signal.getName().length()==1) {
                Search_Name search_name = new Search_Name(search_signal.getName()) ;


                search_name.Search(request);

                this.result =  search_name.Result() ;
                }
                else {

                    Search_Name search_name =  new Search_Name(this.result,search_signal.getName()) ;
//                    search_name.Search(request);
                    search_name.Search_basis(request);
                    this.result =  search_name.Result() ;

                    this.full_result = (List<Product>) session.getAttribute("full_result");
                    session.setAttribute("full_result" , this.full_result);
                    session.setMaxInactiveInterval(600000);
                }

                break;

            }

            case "Author" : {

                Search_Author search_author =  new Search_Author(search_signal.getName()) ;

                search_author.Search_basic();

                this.result =  search_author.Result() ;


                break;
            }

            default:{
                System.out.println("Some thing was wrong");

                break;
            }

        }
        session.setAttribute("result" , this.result);

        session.setMaxInactiveInterval(60*3600);

        return Response.status(200).entity(result).build() ;
    }

    public List<Product> getResult(String url_get_APi) {
        return result;
    }

    public void setResult(List<Product> result) {
        this.result = result;
    }

    @POST
    @Produces("application/json")

    @Consumes(MediaType.APPLICATION_JSON)

    @Path("/full_res")

    public  void full_result(@Context HttpServletResponse response , @Context HttpServletRequest  request , Search_signal search_signal){

        session =  request.getSession(true) ;

        Gson gson =  new Gson() ;


        switch (search_signal.getCate()){
            case "Name" :{



                    Search_Name search_name =  new Search_Name(this.result,search_signal.getName()) ;
//                    search_name.Search(request);
                    search_name.Search_basis(request);
                    this.full_result = (List<Product>) session.getAttribute("full_result");
                    session.setAttribute("full_result_res" , this.full_result);
                    System.out.println(this.full_result.size());
                    session.setMaxInactiveInterval(60*3600);

                break;

            }

            case "Author" : {


            }

            default:{
                System.out.println("Some thing was wrong");

                break;
            }

        }

    }
}
class  Search_signal{
    private String name ;
    private  String cate ;

    public Search_signal(String name, String cate) {
        this.name = name;
        this.cate = cate;
    }

    public Search_signal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

}
