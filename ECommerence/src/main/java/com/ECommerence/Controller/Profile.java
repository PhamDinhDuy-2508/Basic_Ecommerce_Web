package com.ECommerence.Controller;

import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Customer;
import com.ECommerence.entity.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "Profile" , urlPatterns = {"/profile"})

public class Profile extends HttpServlet {

    private  String User_name  ;
    private  String Password ;
    private  String user_name ;

    private String  Email ;
    private users  User   =  new users()  ;
    private  String name ;
    private String ID_cus ;
    private String book_ID ;
    private HttpSession session ;
    private  Customer prsent_Customer ;
    private  String address_arr[]  =  null ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session =  req.getSession()  ;
        System.out.println("GET");
        Load_Header(req);
        load_data_to_profile(req);
        req.getRequestDispatcher("Profile.jsp").forward(req ,  resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POSt");
        super.doPost(req, resp);
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public void Load_Header(HttpServletRequest request) {
        User = (users) session.getAttribute("user_info");
        int amount = (Integer) session.getAttribute("amount");
        request.setAttribute("amount", amount);

        try {

            this.name = User.getUser_name();

            this.ID_cus = String.valueOf(User.getUser_id());

            boolean signal = true;


            System.out.println("Name" + name);
            if (!name.isEmpty()) {
                request.setAttribute("user", true);

                request.setAttribute("user_name", name);
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    public  void  load_data_to_profile(HttpServletRequest request) {

        USer_DAO uSer_dao =  new USer_DAO() ;

        users get_user = uSer_dao.getUSer_ByName(this.name) ;


        this.prsent_Customer =  uSer_dao.get_customer_By_ID(this.ID_cus) ;


        this.address_arr = this.prsent_Customer.getAddress().split("@") ;

        try {
            request.setAttribute("account" ,  this.prsent_Customer.getAccount_Name());
            request.setAttribute("email" ,  this.prsent_Customer.getEmail());

            request.setAttribute("First_name" , this.prsent_Customer.getFirst_name());

            request.setAttribute("Last_name" , this.prsent_Customer.getLast_name());

            request.setAttribute("phone" ,  this.prsent_Customer.getPhone());

            request.setAttribute("Home_number" ,this.address_arr[0]);

            request.setAttribute("Province" , this.address_arr[1]);
            request.setAttribute("Street" , this.address_arr[2]);
            request.setAttribute("city" , this.address_arr[3]);

            request.setAttribute("country" , this.address_arr[4]);

//            System.out.println(this.prsent_Customer.getPhone());

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
