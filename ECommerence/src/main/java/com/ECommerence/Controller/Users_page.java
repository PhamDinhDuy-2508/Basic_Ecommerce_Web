package com.ECommerence.Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import API.Location_API;
import com.ECommerence.DAO.HomePage_Dao_Category;
import com.ECommerence.DAO.Homepage_Dao;
import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Category;
import com.ECommerence.entity.Customer;
import com.ECommerence.entity.Product;
import  com.ECommerence.entity.users ;
import org.apache.commons.lang.ObjectUtils;

@WebServlet(name  =  "Users_page",  urlPatterns = {"/home"})
public class Users_page  extends HttpServlet{
	private static final long serialVersionUID = 2616818815084011052L;private  users User =  new users() ;
	private  HttpSession session  ;
	private com.ECommerence.entity.Cookie cookie_process =  new com.ECommerence.entity.Cookie() ;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session=request.getSession(true);


		try {
			String signal = (String) session.getAttribute("signal_Save") ;
			User = (users)session.getAttribute("user_info") ;
			USer_DAO uSer_dao  =  new USer_DAO() ;

			getServletContext().setAttribute("user_name"  ,  User.getUser_name());
			getServletContext().setAttribute("Signal" ,  signal);
			getServletContext().setAttribute("User_info" , User);



			if(signal.equals("true")) {
					Set_AND_response_Cookie(User.getUser_name() , User.getPasswordString() ,signal , response)  ;
				}
				if(signal.equals("False")) {
					Set_AND_response_Cookie(User.getUser_name() , User.getPasswordString() ,"False" , response)  ;
				}
			load_user(true ,  request , response , User) ;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			load_user(false , request ,response , User) ;
		}
		load_all_data(request , response);
	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cookie cookie[] =  req.getCookies() ;
		String amount = String.valueOf(cookie.length-2) ;
		req.setAttribute("amount" , amount );
		load_all_data(req , resp);
	}
	public void load_all_data(HttpServletRequest request, HttpServletResponse response) throws  ServletException  , IOException {
		Homepage_Dao homepage_Dao =  new Homepage_Dao() ;

		HomePage_Dao_Category homePage_dao_category =  new HomePage_Dao_Category(2) ;

		List<Product> checkList = 	homepage_Dao.getAll() ;
		List<Category> categoryList  =  homePage_dao_category.getAll();
		List<Product> _lats_Product = homepage_Dao.Lastest_Product() ;
		String name_cate =  homePage_dao_category.get_Name_OF_Category("5") ;

		request.setAttribute("List_Product" ,  checkList);
		request.setAttribute("List_Category" ,  categoryList);
		request.setAttribute("List_last" ,  _lats_Product);
		request.setAttribute("name_category"  ,  name_cate );
		request.getRequestDispatcher("Home.jsp").forward(request , response);
	}
	public void load_user(boolean signal , HttpServletRequest request , HttpServletResponse response  , users Users) {

		if(!signal) {
			request.setAttribute("user" , false);
		}
		else {

			request.setAttribute("user" , true);

			request.setAttribute("user_name" ,  Users.getUser_name());

			request.setAttribute("user_id" ,  Users.getUser_id());

			Load_cookie_in_cart(String.valueOf(Users.getUser_id()) , response , request);

		}
	}
	public void Set_AND_response_Cookie(String name , String pass , String sig , HttpServletResponse response) throws  ServletException, IOException {
		if (!sig.equals("true")) {
			Cookie cookie_user_name = new Cookie("user_name", name);
			cookie_user_name.setMaxAge(60*3600);
			response.addCookie(cookie_user_name);

		}
		 else {
			Cookie cookie_pass = new Cookie("pass", pass);
			Cookie cookie_user_name = new Cookie("user_name", name);

			cookie_user_name.setMaxAge(60*3600);
			cookie_pass.setMaxAge(60*3600);

			response.addCookie(cookie_pass);
			response.addCookie(cookie_user_name);

		}
		Cookie cookie_User_id =  new Cookie("user_id" , String.valueOf(User.getUser_id())) ;
		cookie_User_id.setMaxAge(60*3600);
		response.addCookie(cookie_User_id);



	}
	public void load_Header(HttpServletRequest request , HttpServletResponse response) {

	}
	public void Load_data_from_user_Tables_to_custommer_Table() {
	}
	public void Load_cookie_in_cart(String ID , HttpServletResponse response , HttpServletRequest request) {
		session =  request.getSession()  ;


		Cookie cookie = null;
		HashMap<String  ,  Integer >  product_from_Cookies = new HashMap<>();

		Cookie cookie1[] = null;

		cookie1 = request.getCookies();


		Cookie[] finalCookie = cookie1;
		for (Cookie x : finalCookie) {
			if ((x.getName().equals("user_name") == false) && (x.getName().equals("JSESSIONID") == false) && x.getName().equals("pass") == false) {
				String temp_ID =  x.getName().split("_")[0] ;
				if(temp_ID.equals(ID)) {
					product_from_Cookies.put(x.getName().split("_")[1] ,Integer.valueOf(x.getValue()) ) ;
				}
			}
		}
		System.out.println(product_from_Cookies.size());

		request.setAttribute("amount" ,  product_from_Cookies.size());
		session.setAttribute("Product_from_cookies" , product_from_Cookies);
		session.setAttribute("amount" , product_from_Cookies.size());
		session.setMaxInactiveInterval(60*3600);


	}
	public void Amount_of_product(HttpServletRequest  request , HttpServletResponse response) {

		Cookie cookie = null;
		HashMap<String  ,  Integer >  product_from_Cookies = new HashMap<>();
		Cookie cookie1[] = null;

		cookie1 = request.getCookies() ;
	}


}
