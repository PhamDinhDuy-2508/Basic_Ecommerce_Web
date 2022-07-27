package com.ECommerence.Service.Search;

import com.ECommerence.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Search {

    public boolean search_bar_empty  () ;


    public void Search(HttpServletRequest request) ;

    public List<Product> Result() ;

    public Product Binary_Search(List<Product> check , int left , int rght , String x) ;
    public void Paste_Action(String character , HttpServletRequest request) ;

}
