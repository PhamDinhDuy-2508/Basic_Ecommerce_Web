package Filter;

import com.ECommerence.DAO.USer_DAO;
import com.ECommerence.entity.Cart_element;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.ECommerence.entity.users ;

@WebFilter(value = "/checkout" , servletNames = "Checkout")
public class Filter_CheckOut implements Filter {
    private Double Total_price ;

    private List<Cart_element> cart_element_ID =  new ArrayList<>() ;
    private HttpSession session ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        return ;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse  response1 =  (HttpServletResponse) response  ;
            String url_path = req.getServletPath() ;

            session = req.getSession();

            this.cart_element_ID = (List<Cart_element>) session.getAttribute("cart_list");

                if(!this.cart_element_ID.isEmpty()) {

                    request.getRequestDispatcher("checkout").forward(request , response);

                }
                else {

                    chain.doFilter(req , response1);

                }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void destroy() {

    }
}
