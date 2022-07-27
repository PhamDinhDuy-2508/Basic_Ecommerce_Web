package com.ECommerence.Controller;

import com.ECommerence.DAO.Detail_DAO;
import com.ECommerence.DAO.HomePage_Dao_Category;
import com.ECommerence.entity.Category;
import com.ECommerence.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name =  "detail_page" , urlPatterns = "/detail")
public class Detail_Page extends HttpServlet {
    private static final long serialVersionUID = 2616818815084011052L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =  req.getParameter("book_id") ;
        System.out.println(id);
        Detail_DAO detail_dao =  new Detail_DAO() ;
        HomePage_Dao_Category homePage_dao_category =  new HomePage_Dao_Category() ;

        Product product = detail_dao.get_Product_By_Id(Integer.valueOf(id))  ;

        String cate_id = String .valueOf(product.getCategory_id()) ;
        List<Category> categoryList  =  homePage_dao_category.getAll();
        req.setAttribute("List_Category" ,  categoryList);

        List<Product> relative_Product = detail_dao.Relative_Product(cate_id) ;

        req.setAttribute("Product" , product);
        req.setAttribute("relative_Product" , relative_Product);

        req.getRequestDispatcher("Detail.jsp").forward(req , resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }
}
