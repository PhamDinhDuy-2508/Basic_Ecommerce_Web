package com.ECommerence.algorithm;

import com.ECommerence.entity.Cart_element;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Connect_Sort_compoment  {
    private List<Cart_element> list  =  new ArrayList<>() ;

    public  Connect_Sort_compoment(List<Cart_element> list) {
        this.list =  list ;
    }
    private  Sort_Interface sort_interface ;

    public void Compoment(int _signal_sort) {

        switch (_signal_sort) {
            case 1 : {
                sort_interface =  new Sort_By_Name() ;
                break;
            }
            case 2 : {
                sort_interface =  new Sort_By_Price() ;
                break;
            }
            default:{
                return;
            }
        }
        sort_interface.load_data(this.list);
        sort_interface.Sort();


    }
    public List<Cart_element> get_sort_list() {
        return this.list ;
    }
}
