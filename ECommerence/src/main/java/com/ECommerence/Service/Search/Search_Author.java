package com.ECommerence.Service.Search;


import com.ECommerence.DAO.Search_Dao;
import com.ECommerence.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Search_Author implements Search {

    private  String character  ="" ;

    private  List<Product> result =  new ArrayList<>() ;

    @Override
    public boolean search_bar_empty() {
        return false;
    }

    @Override
    public void Search(HttpServletRequest request) {

    }

    @Override
    public List<Product> Result() {
        return this.result;
    }

    public Search_Author(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public Product Binary_Search(List<Product> check , int l , int r , String x) {
        return null;
    }
    @Override
    public void Paste_Action(String character ,HttpServletRequest request) {}
    public void Search_basic() {
        Search_Dao search_dao =  new Search_Dao(this.character) ;

        search_dao.Search("author");

        this.result =   search_dao.Search_result_max_5();

    }


}
