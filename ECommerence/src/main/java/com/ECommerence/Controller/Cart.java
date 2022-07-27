package com.ECommerence.Controller;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.ECommerence.DAO.Cart_DAO;

import com.ECommerence.DAO.Detail_DAO;

import com.ECommerence.DAO.HomePage_Dao_Category;

import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.*;
import com.ECommerence.entity.Category;

@WebServlet(name = "Cart" ,  urlPatterns = {"/cart"})

public class Cart extends HttpServlet  {
    private  static  HttpSession session ;
    private  users User =  new users() ;
    private  String  name = "" ;
    private Cookie[] cookies =  null ;
    private   String Title = "" ;
//    private Detail_DAO detail_dao =  new Detail_DAO() ;
    private Cart_element cart_element =  new Cart_element() ;
    private   List<Cart_element> cart_elements_by_ID =  new ArrayList<>() ;
    private  Cart_DAO cart_dao =  new Cart_DAO() ;
    private  String ID_cus ;
    private  String book_ID ;
    private  HashMap<Integer , Float> product_price =  new HashMap<>() ;
    private  Cart_element  new_element  ;
    private  float Total_Price = 0 ;
    private  Customer customer =  new Customer() ;
    private   static Map<String , Cookie> map_Cookie  =  new HashMap<>();
    private  USer_DAO uSer_dao =  new USer_DAO() ;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customer =  (Customer)getServletContext().getAttribute("customer_info") ;


        Load_Cookie(req,resp);
        String signal_cart = req.getParameter("signal_cart") ;
        Create_Cookies(resp , req);
        session =  req.getSession();
        session.setAttribute("amount" , map_Cookie.size()-3);
        session.setMaxInactiveInterval(60*3600);

        req.setAttribute("amount" , map_Cookie.size()-3 );

        Load_Cart_page(req , resp , 1);

        users  User = (users) getServletContext().getAttribute("User_info") ;
        Customer	customer = 		uSer_dao.get_customer_By_ID(String.valueOf(User.getUser_id())) ;

        customer.setBook_orders(this.cart_elements_by_ID);

        getServletContext().setAttribute("customer_info" , customer);
        session.setAttribute("customer_info" ,customer );
        session.setMaxInactiveInterval(60*3600);

        req.getRequestDispatcher("Cart.jsp").forward(req , resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.session =  req.getSession();
        map_Cookie = new HashMap<>() ;
        cart_elements_by_ID = new ArrayList<>() ;
        Load_Cookie(req, resp);
        Load_Cart_page(req , resp , 0);

        uSer_dao =  new USer_DAO() ;
        users  User = (users) getServletContext().getAttribute("User_info") ;
        Customer	customer = 		uSer_dao.get_customer_By_ID(String.valueOf(User.getUser_id())) ;

        customer.setBook_orders(this.cart_elements_by_ID);

        getServletContext().setAttribute("customer_info" , customer);
        session.setAttribute("customer_info" ,customer );
        session.setMaxInactiveInterval(60*3600);

        req.getRequestDispatcher("Cart.jsp").forward(req , resp);

    }
    public void get_data(String Book_ID , String Price , String Title  , HttpServletRequest request ) {


        this.cart_element.setBook_ID(Book_ID);
    }
    public void Create_Cookies(HttpServletResponse response , HttpServletRequest request) {
        String Cookie_ID = "" ;
        if(this.book_ID != null) {
             Cookie_ID  =  this.ID_cus +"_"+ this.book_ID ;
        }
        else {
            return  ;
        }
        try {
           if(map_Cookie.isEmpty()) {
               Cookie cookie =  new Cookie(Cookie_ID , String.valueOf(1)) ;
               cookie.setMaxAge(60*3600);

               response.addCookie(cookie);
               this.map_Cookie.put(Cookie_ID ,  cookie) ;
           }
           else {
               int quantity =  check_AND_add(Cookie_ID ,  1 ,  response , request) ;
           }

        }
        catch (Exception e) {
            System.out.println("SOME THing Was Wrong +  " + e.getMessage());
            return  ;
        }
    }
    public void load_data() {
        if(!cart_elements_by_ID.isEmpty()) {
            cart_elements_by_ID.clear();

        }
        for(String cookie: map_Cookie.keySet()) {
            try {
                Cookie x = map_Cookie.get(cookie);
                System.out.println(cookie);

                if (Integer.valueOf(x.getName().charAt(0)) != 0) {

                    String book_id = cookie.split("_")[1];
                    Detail_DAO detail_dao1 = new Detail_DAO();

                    Product product = new Product();

                    product = detail_dao1.get_Product_By_Id(Integer.valueOf(book_id));



                    Cart_element cart_element1 = new Cart_element(product, Integer.valueOf(x.getValue()));
                    cart_element1.setUser_ID(this.ID_cus);
                    cart_element1.setBook_ID(String.valueOf(product.getBook_id()));

                    product_price.put(Integer.valueOf(book_id), cart_element1.getPrice());
                    cart_elements_by_ID.add(cart_element1);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
    public int check_AND_add(String Cokkies_Book_ID , int quantity , HttpServletResponse response , HttpServletRequest request ) throws InterruptedException {

        String book_ID = Cokkies_Book_ID.split("_")[1] ;
        int quantity_ = 0 ;

             try {
                    Cookie cookie =   this.map_Cookie.get(Cokkies_Book_ID);
                    quantity_ =  Integer.valueOf(cookie.getValue());
                    quantity_ =  quantity_+1 ;
                    cookie.setValue(String.valueOf( quantity_));

                    response.addCookie(cookie);
                    cookie.setMaxAge(60*3600);
                    Upload_view(Cokkies_Book_ID);
             }

             catch (Exception exception){

                 Cookie cookie =  new Cookie(Cokkies_Book_ID ,  String.valueOf(1)) ;
                 response.addCookie(cookie);
                 cookie.setMaxAge(60*3600);
                 map_Cookie.put(Cokkies_Book_ID , cookie) ;
                 ADD_LIST(Cokkies_Book_ID);
             }

        return quantity ;
    }

    public void Insert_into_View(HttpServletRequest request) {

        request.setAttribute("List_product_in_cart" ,  cart_elements_by_ID);
        request.setAttribute("List_length" ,  cart_elements_by_ID.size());
        Load_total_Price(request);

    }
    public void add_data_from_database() {

        User = (users)session.getAttribute("user_info") ;
        try {
            cart_dao.add_into_order_detail(  this.cart_element);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void load_data_base_from_user(HttpServletRequest request){
        return ;
     }
     public float Total_price_Cal(String ID , int amount  , HttpServletRequest request) {

        this.Total_Price = (Float)  session.getAttribute("Total_value") ;

        if(Total_Price == 0) {
          for (int x : product_price.keySet()) {
              Total_Price += product_price.get(x) ;
          }
          request.setAttribute("Total_value" ,  this.Total_Price);
          return Total_Price ;
        }
        else {
        }

        return 0 ;

     }
     public void add_Session_data(HttpServletRequest request) {
        session = request.getSession() ;
        session.setMaxInactiveInterval(60*3600);
        request.setAttribute("Total_value" , this.Total_Price);
     }
     public void Load_Cart_page(HttpServletRequest req, HttpServletResponse resp , int i) {


         session = req.getSession(true) ;

         if(cart_elements_by_ID.size() == 0) {

             load_data();

         }
         try{
             customer.setBook_orders(cart_elements_by_ID);


             User = (users)session.getAttribute("user_info") ;
             int amount =  (Integer) session.getAttribute("amount") ;
             req.setAttribute("amount" , amount);

             this.name = User.getUser_name() ;

             this.ID_cus = String.valueOf(User.getUser_id()) ;

             this.book_ID=  req.getParameter( "book_id") ;
             boolean signal = false ;


                if (!name.isEmpty() ) {

                    req.setAttribute("user", true);

                    req.setAttribute("user_name", name);
                    if(i==1){
                            Create_Cookies(resp, req);
                            Insert_into_View(req);
                    }
                    else {
                        load_data();
                        Insert_into_View(req);
                    }
            }
         }
         catch (Exception e) {

             System.out.println("Some thing was wrong " + e.getClass());

             req.setAttribute("user" ,  false);
         }
         Put_cartList_AND_MapCOokie(req);
         return   ;
     }
     public void ADD_LIST(String Cokkies_Book_ID) {

         String Book_ID = Cokkies_Book_ID.split("_")[1] ;
         Detail_DAO detail_dao1 =  new Detail_DAO() ;

         Product product =  new Product() ;
         product =   detail_dao1.get_Product_By_Id(Integer.valueOf(Book_ID)) ;
         this.new_element =  new Cart_element(product , 1) ;
         this.new_element.setUser_ID(this.ID_cus);

         this.cart_elements_by_ID.add(this.new_element) ;
     }
     public void Upload_view(String Cookie_Id ) {
        for(Cart_element x : cart_elements_by_ID) {

            if(x.getBook_ID().equals(Cookie_Id.split("_")[1])) {
                int quantity = x.getAmount() +1 ;
                x.Increase_amount();
                break;
            }
        }
        return;
     }

     public void Load_Cookie(HttpServletRequest request, HttpServletResponse response) {

         Map<String , Cookie> temp_map ;
         map_Cookie.clear();

         Cookie cookie1[] = null;

         cookie1 = request.getCookies();

         Cookie[] finalCookie = cookie1;
         product_price = new HashMap<>();

         map_Cookie =  new HashMap<>() ;

         try{
             for (Cookie x : finalCookie) {
                    this.map_Cookie.put(x.getName(), x);
             }
        }catch (Exception e) {
             return  ;
        }
     }
     public void delete_Cart_element() {

         try {
             String Book_Id = (String) this.session.getAttribute("book_id_delete");

             if (!Book_Id.equals("")) {
                 for (Cart_element x : cart_elements_by_ID) {
                     String cookie_id = this.ID_cus+"_"+this.book_ID ;

                     if(x.getBook_ID().equals(Book_Id)) {

                         cart_elements_by_ID.remove(x) ;

                         this.session.setAttribute("book_id_delete" , "");
                     }
                 }
             }
         }
         catch (Exception e) {
             System.out.println(e.getMessage());
         }

     }
     public void Put_cartList_AND_MapCOokie(HttpServletRequest request) {

         this.session =  request.getSession() ;
         this.session.setAttribute("map_cookie" , map_Cookie) ;
         HashMap<String , List<Cart_element>  > ID_cart_element  =  new HashMap<>() ;
         ID_cart_element.put(this.ID_cus , cart_elements_by_ID  ) ;
         session.setAttribute("ID_cart_element" , ID_cart_element);

         session.setAttribute("cart_list"  , cart_elements_by_ID);

         session.setMaxInactiveInterval(60*3600);

     }
     public void Load_total_Price( HttpServletRequest request) {

        float Price_total = 0 ;
        for(Cart_element x : cart_elements_by_ID) {
            Price_total += x.get_total_price() ;
        }

        request.setAttribute("total_Price" , Price_total);

     }
}