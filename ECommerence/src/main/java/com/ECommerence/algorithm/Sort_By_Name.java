package com.ECommerence.algorithm;

import com.ECommerence.Controller.Cart;
import com.ECommerence.entity.Cart_element;

import java.util.*;

public class Sort_By_Name  implements Sort_Interface<Cart_element> {
    private  List<Cart_element> lis_cart_element =  new ArrayList<>();

    public Sort_By_Name() {}

    @Override

    public void load_data( List<Cart_element>  cart_list ) {
        this.lis_cart_element =  cart_list ;
    }

    @Override
    public List<Cart_element> return_data() {
        return this.lis_cart_element;
    }

    @Override
    public void Sort() {
        Collections.sort(this.lis_cart_element, new Comparator<Cart_element>() {
            @Override
            public int compare(Cart_element o1, Cart_element o2) {
                if(o1.getTitle().compareTo(o2.getTitle()) == 0) {
                    return 1;
                }
                else {
                    return 0 ;
                }
            }
        });
    }
}
