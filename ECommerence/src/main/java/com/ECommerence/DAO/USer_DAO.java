package com.ECommerence.DAO;

import com.ECommerence.Controller.Users_page;
import com.ECommerence.algorithm.Sort_By_Name;
import com.ECommerence.entity.Customer;
import com.ECommerence.entity.Product;
import  com.ECommerence.entity.users ;
import com.mysql.cj.jdbc.Blob;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class USer_DAO  implements  DAO<users> {
    private String id = "";
    private String padss = "";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultset = null;
    private int verify_number = 0;

    public USer_DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "phamdinhduy2508");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public users get_user(String user_name, String pass) {
        users Users = new users();
        if (check_data(user_name, pass)) {
            System.out.println(user_name);
            try {
                String query = "select * from users where  user_name  = " + "'" + user_name + "'";
                preparedStatement = connection.prepareStatement(query);
                resultset = preparedStatement.executeQuery(query);
                ResultSetMetaData resultSetMetaData = resultset.getMetaData();

                while (resultset.next()) {

                    Users.setUser_name(resultset.getString("user_name"));
                    Users.setUser_id(resultset.getInt("user_id"));
                    Users.setEmail(resultset.getString("email"));
                    Users.setFull_name(resultset.getString("full_name"));
                    Users.setPasswordString(resultset.getString("password"));
                }
            } catch (Exception e) {
                return null;
            }
        }
        return Users;
    }

    public USer_DAO(String id) {
        this.id = id;
    }

    @Override
    public Optional<users> get(long id) {
        return Optional.empty();
    }

    public void Create_user(String user_name, String email, String Password) {
        String query = " INSERT INTO `bookstoredb`.`users` (`user_id`, `email`, `password`, `full_name`, `user_name`) VALUES(";
        query += "NULL , ";
        query += "'" + email + "' , ";
        query += "'" + Password + "' , ";
        query += "'" + "" + "' , ";
        query += "'" + user_name + "' )";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        users USer = getUSer_ByName(user_name);
        Create_customer(USer);
    }

    public boolean check_User_name_exist(String username) {
        String query = "select * from users where user_name = " + "'" + username + "'";

        String name = "";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();
            while (resultset.next()) {
                name = resultset.getString("user_name");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (name.equals("")) {
            return true;
        }
        return false;
    }

    public boolean check_data(String user_name, String id) {

        String query = "select *  from users where ( full_name = " + "'" + user_name + "'" + " AND password = " + "'" + id + "'" + ")";
        String full_Name_test = "";
        String password = "";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery(query);
            while (resultset.next()) {
                full_Name_test = resultset.getString("user_name");
                password = resultset.getString("password");

            }
            if (full_Name_test.equals("") && password.equals("")) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public String message_error(String user_name, String pass) {
        try {
            users User = new users();
            String text = "";
            String query = "select * from users where  user_name  = " + "'" + user_name + "'";
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();
            while (resultset.next()) {
                text = resultset.getString("user_name");
            }
            if (text.equals("")) {
                return "Username is invalid ";
            } else {
                return "Password is wrong";
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }

    @Override
    public List<users> getAll() {
        return null;
    }

    @Override
    public void Update(users users, String[] parameter) {
    }

    @Override
    public void Delete(users users) {

    }

    @Override
    public void Add(users users) {

    }

    public void getUSer_ByID(String User_Id) {

    }

    public users getUSer_ByName(String name) {
        users Users = new users();
        try {
            String query = "select * from users where  user_name  = " +"'" +  name + "'";
            System.out.println(query);
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();

            while (resultset.next()) {

                Users.setUser_name(resultset.getString("user_name"));
                Users.setUser_id(resultset.getInt("user_id"));
                Users.setEmail(resultset.getString("email"));
                Users.setFull_name(resultset.getString("full_name"));
                Users.setPasswordString(resultset.getString("password"));
            }
        } catch (Exception e) {
            return null;
        }

        return Users;

    }

    public void Load_data_from_user_Tables_to_custommer_Table() {
        String query = "select * from users";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();
            while (resultset.next()) {
                users User = new users();
                User.setUser_id(Integer.valueOf(resultset.getString("user_id")));
                User.setFull_name(resultset.getString("full_name"));
                User.setPasswordString(resultset.getString("password"));
                User.setEmail(resultset.getString("email"));
                User.setUser_name(resultset.getString("user_name"));
                Create_customer(User);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void Create_customer(users users) {

        String query = String.format("INSERT INTO `bookstoredb`.`customer` (`customer_id`, `email`, `fullname`, `address`, `city`, `country`, `phone`, `zipcode`, `password`, `register_date`) VALUES ('%s', '%s', '%s', 'None', 'None', 'None', 'None', 'None', '%s', '2038-01-19 03:14:07')"
                , users.getUser_id(), users.getPasswordString(), users.getFull_name(), users.getPasswordString());

        System.out.println(query);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getVerify_number(int user_ID) {

        String query = "select * from users where user_id = " + user_ID;
        int verify_number = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();
            while (resultset.next()) {
                verify_number = Integer.valueOf(resultset.getString("verify_number"));
            }
            return verify_number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public void setVerify_number(int verify_number, int user_ID) {
        long max = 100000000;
        long min = 1000;
        long random_int = (long) Math.floor(Math.random() * (max - min + 1) + min);

        String query = "update users set verify_number  = " + random_int + "where user_id = " + user_ID;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.verify_number = verify_number;
    }

    public String Gei_user_Email(int user_id) {
        String query = "select * from users where user_id = " + user_id;
        String Email = "";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();
            while (resultset.next()) {
                Email = String.valueOf(resultset.getString("email"));
            }
            return Email;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Email;
        }
    }

    public Customer get_customer_By_ID(String customer_id) {
        String query = "select * from customer where customer_id  = " + customer_id;
        Customer customer = new Customer();
        System.out.println(query);

        final  String  user_account   ;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();

            ExecutorService executorService = Executors.newSingleThreadExecutor() ;
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return   get_user_name_by_id(customer_id) ;
                 }
            } ;
            Future<String> account  = executorService.submit(callable) ;

            while (resultset.next()) {

                customer.setCustomer_id(Integer.valueOf(resultset.getString("customer_id")));
//                customer.setFullname(resultset.getString("fullname"));
                customer.setPassword(resultset.getString("password"));
                customer.setEmail(resultset.getString("email"));
                customer.setFirst_name(resultset.getString("First_name"));
                customer.setLast_name(resultset.getString("Last_name"));
                customer.setAddress(resultset.getString("address"));
                customer.setBit_Arr(resultset.getString("Image"));

                customer.setPhone(resultset.getString("phone"));

//                com.mysql.cj.jdbc.Blob blob = (Blob) resultset.getBlob("Image") ;
//
//                Image_process(blob, customer) ;

            }
            executorService.shutdown();

            user_account =  account.get() ;


            customer.setAccount_Name(user_account);

            return  customer ;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    public String get_user_name_by_id(String id) {
        String id_return = "";
        String query = " select * from users where user_id = " + "'" +  id +"'";
        System.out.println(query);

        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();

            while (resultset.next()) {
                id_return = resultset.getString("user_name");
            }

            return id_return;

        } catch (Exception e) {
            e.getMessage();
            return id_return;
        }
    }
    public void Image_process(Blob blob , Customer product) throws SQLException  , IOException {

        InputStream inputStream =  blob.getBinaryStream() ;

        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream() ;

        byte[]byte_arr  = new byte[4096] ;
        int bytes_read = -1 ;

        while ((bytes_read = inputStream.read(byte_arr)) != -1) {

            byteArrayOutputStream.write(byte_arr, 0, bytes_read);
        }
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        inputStream.close();
        byteArrayOutputStream.close();
        product.setBit_Arr(base64Image);
    }
    public void Update_user_Customer_password(String ID , String password) {
        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {

                String  query = "update customer set password  = " + password + "where customer_id = " + ID ;
                try {
                    Statement statement = connection.createStatement() ;
                    statement.executeUpdate(query) ;
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        })  ;
        thread.start();
        String query = "update users set password  = " + password + "where user_id = " + ID ;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            thread.join();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void update_customer_info(String first_name , String last_name  , String new_email ,  String new_Phone_number , String customer_id  , String address) {

        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                String query =  "update users  set email=  " + "'" +new_email +"'" + " where user_id = " +  "'" + customer_id  + "'" ;
                System.out.println(query);
                try{

                    PreparedStatement preparedStatement1 = connection.prepareStatement(query);

                    preparedStatement1.executeUpdate()   ;

                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }) ;
        thread.start();
       String query =  String.format( " update customer set First_name= '%s'  ,  Last_name='%s'  , email='%s' ,  phone='%s'  , address = '%s' where customer_id =  "  +"'"+ customer_id +"'" ,
               first_name ,  last_name , new_email , new_Phone_number , address) ;
        System.out.println(query);

//        String query = " update customer set First_name=?  ,  Last_name=?  , email=? ,  phone=?  where customer_id =  " +  customer_id ;
        try {
            System.out.println(query);

            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            thread.join();
        }
        catch ( Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public String get_user_id_by_name(String name) {
        String query = " select * from users where  user_name  = " + "'" + name +"'" ;
        String id_result  = "" ;
        try {
            System.out.println(query);
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();

            while (resultset.next()) {
                id_result  = resultset.getString("user_id");
            }
            return id_result;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null ;
        }

    }


    public String Get_email_from_user_name( String user_id ) {

        String query =  "select * from customer where  customer_id = " + "'" +  user_id  +"'" ;

        String email_result  = "" ;

        try {

            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultset.getMetaData();

            while (resultset.next()) {
                email_result  = resultset.getString("email");
            }
            return email_result;
        }
        catch (Exception e) {

            System.out.println(e.getMessage());
            return null ;
        }
    }


}
