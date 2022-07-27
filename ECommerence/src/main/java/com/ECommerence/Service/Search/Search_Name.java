package com.ECommerence.Service.Search;

import com.ECommerence.DAO.Search_Dao;
import com.ECommerence.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class Search_Name implements Search {

    private  ExecutorService executorService = Executors.newSingleThreadExecutor() ;

    private boolean check_search_bar_empty = false ;

    private List<Product> full_result =  new ArrayList<>() ;

    private  Thread thread_Search ;

    private Stack<HashMap<String , List<Product>>> step_Search = new Stack<>() ;

    private ExecutorService executorService1 =  Executors.newFixedThreadPool(6) ;
    private Future<List<Product>> prduct ;


    private List<Product> result = new ArrayList<>() ;
    private  String character ;

    private HttpSession session  ;

    public Search_Name(List<Product> result, String name) {
        this.result = result;
        this.character = name ;
    }

    public Search_Name() {
    }

    public List<Product> getFull_result(HttpServletRequest request) {
        this.session =  request.getSession(true) ;

        this.full_result= (List<Product>) this.session.getAttribute("full_result");
        System.out.println(this.full_result.size());

        return full_result;
    }

    public void setFull_result(List<Product> full_result) {
        this.full_result = full_result;
    }

    @Override
    public void Search(HttpServletRequest request  ) {

        session =  request.getSession(true) ;


        if(this.result.isEmpty()) {

            Search_Dao search_dao =  new Search_Dao(this.character) ;

            search_dao.Search("name");

            this.result =   search_dao.Search_Process();

            Set_session(request);

        }
        else {
            get_result_search();
            System.out.println("result size : " + result.size());

            Set_session(request);
        }

    }

    @Override
    public List<Product> Result() {
        List<Product>             test = (List<Product>) session.getAttribute("full_result");
        return this.result;
    }

    @Override
    public boolean search_bar_empty() {
        return false;
    }

    public Search_Name(String character) {
        this.character = character;
    }
    public void Set_session(HttpServletRequest request) {
        HashMap<String , List<Product>> check  = new HashMap<>() ;

        check.put(this.character , this.result) ;

        step_Search.push(check) ;
        System.out.println("SIZE " + step_Search.size());

        session.setAttribute("step_search" , step_Search );

    }

    public void get_result_search() {

      this.step_Search  =(Stack<HashMap<String , List<Product>>>)session.getAttribute("step_search") ;
        Set<String> keys = step_Search.peek().keySet();
        String key = keys.toArray()[0].toString() ;


        if(this.character.length() >= key.length()) {
            try {

                this.result = Search_List(step_Search.firstElement().get(keys.toArray()[0]), this.character.charAt(this.character.length() - 1), step_Search.size());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {

            System.out.println("SMALL");
            int loop  = Math.abs( this.character.length() -    key.length()) ;
            int step =0 ;
            while(step != loop){

                step_Search.pop() ;
                step ++ ;

            }
            try {
                keys = step_Search.peek().keySet();
                key = keys.toArray()[0].toString() ;
                this.result =   (step_Search.peek().get(keys.toArray()[0]));
            }
            catch (Exception e) {
                System.out.println(e.getMessage() );
            }
        }

    }

    @Override
    public Product Binary_Search(List<Product> productList , int l, int r , String x) {
        return  null ;
     }
     public List<Product> Search_List(List<Product> present_product, char character , int pos  ) {
        List<Product> result = new ArrayList<>() ;
        for(Product x : present_product) {

            try {
                char r = x.getTitle().charAt(pos) ;


                String title_char = String.valueOf(r) ;
                String charac = String.valueOf(character) ;

                if (title_char.equals(charac)) {
                    result.add(x);
                }
            }
            catch (Exception e) {
                continue;
            }
        }
        return result ;

     }
     @Override
     public void Paste_Action(String character , HttpServletRequest request) {
         Search_Dao search_dao =  new Search_Dao(this.character) ;

         search_dao.Search("name");

         this.result =   search_dao.Search_Process();

         Set_session(request);

     }

     public void Search_basis(HttpServletRequest request)  {
        session =  request.getSession(true);

         Search_Dao search_dao =  new Search_Dao(this.character) ;

         search_dao.Search("name");


         Callable<List<Product>> callable = new Callable<List<Product>>() {
             @Override
             public List<Product> call() throws Exception {
                 return   search_dao.Search_Process() ;
             }
         } ;


         this.result =   search_dao.Search_result_max_5();



         prduct  = executorService1.submit(callable) ;
         try
         {
             executorService1.shutdown();
            this.full_result  = prduct.get();

             session.setAttribute("full_result" ,  full_result);


         }catch (Exception e) {
             System.out.println(e.getMessage());
         }

         session.setMaxInactiveInterval(60*3600);
     }

    public Thread getThread_Search() {
        return thread_Search;
    }

    public void setThread_Search(Thread thread_Search) {
        this.thread_Search = thread_Search;
    }
}
