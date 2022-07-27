package com.ECommerence.Service.Filter;

import com.ECommerence.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Filter_Price implements Filter {

    private  List<Product> productList ;
    private String Start ;
    private String End ;
    private List<Product> result =  new ArrayList<>() ;

    public Filter_Price() {}

    public Filter_Price(List<Product> productList, String start, String end) {
        this.productList = productList;
        Start = start;
        End = end;
    }

    @Override
    public void filter_process() {
        Float start = Float.valueOf(this.Start);
        Float end = Float.valueOf(this.End);
        if (end > 0) {
            for (Product x : productList) {

                if (Float.valueOf(x.getPRICE())  >= start && Float.valueOf(x.getPRICE())  <= end) {
                    this.result.add(x);
                } else {
                    continue;
                }
            }
        }
        else {
            for (Product x : productList) {

                if (Float.valueOf(x.getPRICE()) >= start )
                {

                    this.result.add(x);
                } else {
                    continue;
                }
            }

        }

    }

    @Override
    public List<Product> filter_result() {
        return this.result;
    }
}
