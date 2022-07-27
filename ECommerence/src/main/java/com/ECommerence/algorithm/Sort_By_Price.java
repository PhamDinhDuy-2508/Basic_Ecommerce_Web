package com.ECommerence.algorithm;

import com.ECommerence.entity.Cart_element;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort_By_Price implements  Sort_Interface<Cart_element>{
    public Sort_By_Price(){}
    private List<Cart_element> cart_elementList ;

    @Override
    public void load_data(List<Cart_element> list) {
        this.cart_elementList =  list ;
    }
    @Override
    public List<Cart_element> return_data() {
        return cart_elementList ;
    }

    @Override

    public void Sort() {
        Collections.sort(this.cart_elementList, new Comparator<Cart_element>() {
            @Override
            public int compare(Cart_element o1, Cart_element o2) {
                if(o1.getPrice() < o2.getPrice()) {
                    return 0 ;
                }
                else {
                    return  1 ;
                }
             }
        });

    }
}
