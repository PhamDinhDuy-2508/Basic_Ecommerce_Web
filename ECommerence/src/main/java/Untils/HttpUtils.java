package Untils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class HttpUtils  {
    private  String Json = "" ;
    private  String API_Str = "" ;

    public HttpUtils(String json) {
        Json = json;
    }
    public  HttpUtils() {}
    public  <T> Object To_Model(Class<T> tClass) throws IOException {
        return new ObjectMapper().readValue(Json ,  tClass) ;
    }
    public  static HttpUtils of (BufferedReader reader) {

        StringBuilder json =  new StringBuilder()  ;

        Gson gson =  new Gson() ;
        try{
            String line = "" ;
            while ((line =  reader.readLine()) != null) {
                json.append(line) ;
            }
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(json.toString());
        return new HttpUtils(json.toString()) ;
    }

    public String getJson() {
        return Json;
    }

    public void setJson(String json) {
        Json = json;
    }

    public String getAPI_Str() {
        return API_Str;
    }

    public void setAPI_Str(String API_Str) {
        this.API_Str = API_Str;
    }
}
