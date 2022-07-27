package com.ECommerence.entity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Cookie {

    Cookie cookie =  null ;
    javax.servlet.http.Cookie[] cookie_array =  null ;
    Map<String  , javax.servlet.http.Cookie> map_cookie  = new HashMap<>()  ;

    public Cookie(HttpServletRequest request) {
        try{
            cookie_array =  request.getCookies() ;
            if(map_cookie.isEmpty()) {
                for(javax.servlet.http.Cookie x :this.cookie_array) {
                    System.out.println( "asdasd" + x.getName());
                    this.map_cookie.put(x.getName() , x) ;
                }
            }
            else {}
        }
        catch (Exception e)  {
            System.out.println("EXCEPTION is catch");
            System.out.println(e.getMessage());
            return  ;
        }
    }
    public Cookie() {}

    public void Update_Cookie(String Cookie_id , int quantity) {// do something
    }
    public void Create_cookie(String Cookie ) {
        // do some thing ;
    }
    public void Delete_Cookie(String Cookie_ID) {
        javax.servlet.http.Cookie cookie1 =  find_cookie(Cookie_ID) ;
        cookie1.setMaxAge(0);
    }

    public Map<String, javax.servlet.http.Cookie> getMap_cookie() {
        return map_cookie;
    }
    public void setMap_cookie(Map<String, javax.servlet.http.Cookie> map_cookie) {
        this.map_cookie =  map_cookie ;
        return  ;
    }

    public  javax.servlet.http.Cookie find_cookie(String Cookie_id) {
        try{
            javax.servlet.http.Cookie cookie1 =  this.map_cookie.get(cookie) ;
            return  cookie1 ;
        }
        catch (Exception e) {
            System.out.println(e.getMessage() ) ;
            return null ;
        }

    }
}
