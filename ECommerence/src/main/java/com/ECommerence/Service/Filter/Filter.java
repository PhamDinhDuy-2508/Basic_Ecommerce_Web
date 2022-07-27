package com.ECommerence.Service.Filter;

import com.ECommerence.entity.Product;

import java.util.List;

public interface Filter  {
    public void filter_process() ;
    public List<Product> filter_result() ;
}
