package Test;

public class test_model {
    private  String full_name = "" ;
    private  String user_id = "" ;

    public test_model(String full_name, String user_id) {
        this.full_name = full_name;
        this.user_id = user_id;
    }

    public test_model() {
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
