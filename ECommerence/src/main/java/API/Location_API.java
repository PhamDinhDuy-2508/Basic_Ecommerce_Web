package API;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.*;

import org.json.simple.parser.JSONParser;


import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Location_API {
    private  String name_province = "" ;
    private HashMap<String ,String> Province_name_AND_CODE =  new HashMap<>() ;
    private List<String> Province_name =  new ArrayList<>()  ;
    public Location_API() {}

    public void get_List_Ditrict(String code) {


    }
    public HashMap<String, String> get_List_Of_Province() {
        return this.Province_name_AND_CODE ;
    }

    public void Get_Location_API(String url_API ) {
        Gson gson = new Gson() ;
        List<String> Location_APi_list = new ArrayList<>() ;
        try {
            URL url = new URL(url_API);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                String API_String =  "" ;
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {

                    String s =  scanner.nextLine() ;

                    API_String += s ;

                }//Close the scanner
                scanner.close();

                JSONArray arr = new JSONArray(API_String);

                for (int i = 0; i < arr.length(); i++) { // Walk through the Array.
                    JSONObject obj = arr.getJSONObject(i);

                    Province_name_AND_CODE.put( String.valueOf(obj.get("name") ), String.valueOf(obj.get("code"))) ;

                    this.Province_name.add(String.valueOf(obj.get("name"))) ;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    }


