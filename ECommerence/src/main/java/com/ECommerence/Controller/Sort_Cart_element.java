package com.ECommerence.Controller;

import com.ECommerence.algorithm.Connect_Sort_compoment;
import com.ECommerence.algorithm.Sort_By_Name;
import com.ECommerence.entity.Cart_element;
import com.ECommerence.entity.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@WebServlet(name = "Sort_Cart_element" , urlPatterns = {"/sort_cart"})
public class Sort_Cart_element  extends HttpServlet {
    private Connect_Sort_compoment connect_sort_compoment;
    private List<Cart_element> list_cart;

    private int Signal;
    private users User = new users();

    private HttpSession session;

    public Sort_Cart_element() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession(false);
        System.out.println("LIST CART SORT  :");
        User = (users) session.getAttribute("user_info");

        this.Signal = Integer.valueOf(req.getParameter("signal_sort"));

        HashMap<String, List<Cart_element>> get_USer_INfo = (HashMap<String, List<Cart_element>>) this.session.getAttribute("ID_cart_element");
        this.list_cart = get_USer_INfo.get(String.valueOf(User.getUser_id()));
//
        Sort();
        Push_data_to_Webview_through_Ajax(resp);
    }

    public void Sort() {

        connect_sort_compoment = new Connect_Sort_compoment(list_cart);
        connect_sort_compoment.Compoment(this.Signal);
        this.list_cart = connect_sort_compoment.get_sort_list();
        return;
    }

    public void Push_data_to_Webview_through_Ajax(HttpServletResponse responses) throws IOException {
        try {
            System.out.println("SUCESS");

            PrintWriter out = responses.getWriter();
            for (Cart_element i : list_cart) {
                out.println("<div class=\"row mb-4 d-flex justify-content-between align-items-center\"  id=check>\n" +
                        "        <div class=\"col-md-2 col-lg-2 col-xl-2\">\n" +
                        "            <img\n" +
                        "                    src = \"data:image/jpg;base64,${i.getBit_image()}\"\n" +
                        "                    class=\"img-fluid rounded-3\"  alt=\"Cotton T-shirt\">\n" +
                        "        </div>\n" +
                        "        <div class=\"col-md-3 col-lg-3 col-xl-3\">\n" +
                        "            <h6 class=\"text-muted\"> Book Id : ${i.getBook_ID()}</h6>\n" +
                        "            <h6 class=\"text-black mb-0\">${i.getTitle()}</h6>\n" +
                        "        </div>\n" +
                        "        <div class=\"col-md-3 col-lg-3 col-xl-2 d-flex\">\n" +
                        "            <button class=\"btn btn-link px-2\"id=\"formdown\"\n" +
                        "                    onclick=\"this.parentNode.querySelector('input[type=number]').stepDown()\">\n" +
                        "                <i class=\"fas fa-minus\"></i>\n" +
                        "            </button>\n" +
                        "\n" +
                        "            <input id=\"form1\" min=\"0\" name=\"quantity\" value=${i.amount}  type=\"number\" onchange=\"update_amount('${i.getBook_ID()}')\"\n" +
                        "                   class=\"form-control form-control-sm\" />\n" +
                        "\n" +
                        "            <button class=\"btn btn-link px-2 \" id=\"formup\"\n" +
                        "                    onclick=\"this.parentNode.querySelector('input[type=number]').stepUp()\">\n" +
                        "                <i class=\"fas fa-plus\"></i>\n" +
                        "            </button>\n" +
                        "\n" +
                        "        </div>\n" +
                        "        <div class=\"col-md-3 col-lg-2 col-xl-2 offset-lg-1\">\n" +
                        "            <h6 class=\"mb-0\">${i.getPrice()}$</h6>\n" +
                        "        </div>\n" +
                        "        <div class= \"col-md-1 col-lg-1 col-xl-1 text-end\"  >\n" +
                        "            <td class=\"linkdelete\"  href=\"#\"  }>\n" +
                        "                 <button  type=\"button\" href=\"#\" class=\"btn btn-outline-danger bi bi-trash\" onclick=\"reload_remove_function('${i.getBook_ID()}' , '${i.getUser_ID()}' , this)\"  />\n" +
                        "            </td>\n" +
                        "\n" +
                        "        </div>\n" +
                        "\n" +
                        "\n" +
                        "    </div>") ;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}