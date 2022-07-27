package com.ECommerence.Service;

import Test.test_model;
import com.ECommerence.Controller.Invoice;
import com.ECommerence.DAO.Order_DAO;
import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Book_Order;
import com.ECommerence.entity.Cart_element;
import com.ECommerence.entity.Customer;
import com.ECommerence.entity.Order_detail;
import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("checkout")
public class CheckOut_service {
    private Gson gson =  new Gson() ;
    private Order_DAO order_dao =  new Order_DAO() ;

    private Customer customer  ;
    private USer_DAO uSer_dao =  new USer_DAO() ;
    HttpServletResponse response ;
    HttpServletRequest request ;

    private  HttpSession session ;
    public static  final ObjectMapper mapper = new ObjectMapper()  ;


    @GET
    @Produces("application/json")
    @Path("/{id}")

    public Response doGet(@Context HttpServletRequest request , @Context HttpServletResponse response ,  @PathParam("id")  String id) {
        session = request.getSession(true);

        customer = (Customer) session.getServletContext().getAttribute("customer_info");
            if(String.valueOf(customer.getCustomer_id()).equals(id)) {
                return Response.status(200).entity(customer).build();
            }
            else {
                return Response.status(200).entity(new Object()).build();
            }
    }
    @GET
    @Produces("application/json")
    @Path("order_id")

    public Response Get_order (@Context HttpServletRequest request , @Context HttpServletResponse response) {

        return Response.status(200).entity("103").build() ;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response Post_data( @Context HttpServletRequest request , @Context HttpServletResponse _response  , Book_Order book_order) throws IOException {

        session =  request.getSession(true) ;
        customer = (Customer) session.getServletContext().getAttribute("customer_info");
        List<Order_detail> list =  new ArrayList<>() ;

        for(Cart_element x : customer.getBook_orders()) {

            Order_detail order_detail =  new Order_detail(x.getProduct() , x.getAmount() , x.get_total_price()) ;

            list.add(order_detail) ;

        }

//        book_order.setOrder_detail_list(list);
//        book_order.setTotal(customer.getTotal_Price());
//
//        book_order.setOrder_date(current_Order_date());
//
//        order_dao.Add(book_order);
        try {
            session.getServletContext().setAttribute("customer_info", customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        this.customer.setQueue_Book_Order(book_order);

        return  Response.status(200).entity(book_order).build() ;
    }
    public String current_Order_date() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(dateTimeFormatter.format(now)) ;
    }


}
