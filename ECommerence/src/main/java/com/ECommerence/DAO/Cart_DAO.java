package com.ECommerence.DAO;

import com.ECommerence.Controller.Cart;
import com.ECommerence.entity.Cart_element;
import com.ECommerence.entity.Product;
import com.ECommerence.entity.users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart_DAO  implements  DAO<Product>{
    private Connection connection =  null ;
    private PreparedStatement preparedStatement =  null ;
    private ResultSet resultset  = null ;

    private String Bit_array  ;
    private int Quantity ;
    private  int ID ;
    private List<Cart_element> list_Cart_Item = new ArrayList<>() ;

    public String getBit_array() {
        return Bit_array;
    }

    public void setBit_array(String bit_array) {
        Bit_array = bit_array;
    }
    private  Statement statement  ;
    private  String order_id ;
    public Cart_DAO() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver") ;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb","root","phamdinhduy2508") ;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
    public void Load_cart_element_by_user_Id(String ID) {
        String query = String.format("INSERT INTO `bookstoredb`.`book_order` (`order_id`, `customer_id`, `order_date`, `shipping_address`, `recipient_name`, `recipient_phone`, `payment_method`, `total`, `status`) VALUES ('NULL','%s', '2038-01-19 03:14:07', 'None', 'None', 'None', 'None', '0', 'None')" , ID);
        try{
            Statement statement =  connection.createStatement() ;
            statement.executeUpdate(query) ;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return  ;
        }
    }
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String get_image(String ID) {

        try {
            String query = "select * from book where book_id = " + ID  ;
            preparedStatement =  connection.prepareStatement(query) ;
            resultset =  preparedStatement.executeQuery(query) ;
            ResultSetMetaData resultSetMetaData =  resultset.getMetaData() ;

            while(resultset.next()) {
                this.Bit_array = resultset.getString("image") ;
            }
            return this.Bit_array ;
        }
        catch (Exception e) {

            return "" ;

        }
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public List<Cart_element> get_Cart_From_ID(String ID) {

        String query = "select from book where book_id = " + ID ;

        List<Cart_element> cart_elements =  new ArrayList<>() ;

        Cart_element cart_element =  new Cart_element() ;

        try {

            preparedStatement =  connection.prepareStatement(query) ;

            resultset =  preparedStatement.executeQuery(query) ;

            ResultSetMetaData resultSetMetaData =  resultset.getMetaData() ;

            while(resultset.next()) {
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null  ;
    }
    public boolean Load_Cart_Element_into_Cart(String ID)    {
//        String query  =  "" ;
        String query = String.format( "INSERT INTO `bookstoredb`.`book_order` (`order_id`, `customer_id`, `order_date`, `shipping_address`, `recipient_name`, `recipient_phone`, `payment_method`, `total`, `status`) VALUES (NULL, '%s', '2038-01-19 03:14:07', 'None', 'None', 'None', 'None', '0', 'None')" , ID  )  ;
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query) ;
            get_lastest_key_user_id();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        query =  "SELECT DISTINCT book_order.customer_id ,  book_order.order_id  from book_order Inner join customer on book_order.customer_id =  customer.customer_id where  book_order.customer_id = " + ID ;
        List<Cart_element> list_cart = new ArrayList<>() ;

        try {
            preparedStatement =  connection.prepareStatement(query) ;
            resultset =  preparedStatement.executeQuery(query) ;
            ResultSetMetaData resultSetMetaData =  resultset.getMetaData() ;

            while(resultset.next()) {
                  System.out.println(resultset.getInt(2));
                  Cart_element cart_element =  new Cart_element() ;
//                  cart_element.setOrder_ID(Integer.valueOf(resultset.getString("order_id")));
                  list_cart.add(cart_element) ;
            }
            list_Cart_Item =  list_cart ;
        }
        catch (Exception e) {
//            System.out.println(e.getMessage());
        }
        if(list_cart.size() == 0 ) {
            return false ;
        }
        else {
            return  true ;
        }
    }
    public void add_into_order_detail(Cart_element cart_element)  throws Exception{
        String query = "" ;
        query  = " Select COUNT(*) from order_detail" ;
        preparedStatement =  connection.prepareStatement(query) ;
        resultset =  preparedStatement.executeQuery(query) ;
        ResultSetMetaData resultSetMetaData =  resultset.getMetaData() ;
        resultset.next()   ;

        int Count = Integer.valueOf(resultset.getInt(1)) ;

        if(Count == 0 ) {

            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query) ;
        }
        else {
            try {
                if (this.list_Cart_Item.size() != 0) {

                    for (int i = 0; i < list_Cart_Item.size(); i++) {

                        if (cart_element.getBook_ID().equals(list_Cart_Item.get(i).getBook_ID())) {
                            int Quantity = list_Cart_Item.get(i).getAmount() + 1;
                            float Price =  list_Cart_Item.get(i).getPrice() + list_Cart_Item.get(i).getPrice()/list_Cart_Item.get(i).getAmount() ;
                            query = "UPDATE order_detail " +
                                    " Set quantity  =   " + Quantity + ", subtotal = " +  Price + "Where book_id = " + cart_element.getBook_ID() ;

                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);

                            return;
                        }
                    }
                    query = String.format("INSERT INTO `bookstoredb`.`order_detail` (`order_id`, `book_id`, `quantity`, `subtotal`) VALUES ('%s', '%s', 1 , %s )",this.order_id , cart_element.getBook_ID(), cart_element.getPrice());

                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                } else {
                    query = "SET foreign_key_checks = 0 " ;
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    query = String.format("INSERT INTO `bookstoredb`.`order_detail` (`order_id`, `book_id`, `quantity`, `subtotal`) VALUES ('%s', '%s', 1 , %s )",this.order_id,  cart_element.getBook_ID(), cart_element.getPrice());
                    statement.executeUpdate(query);
                }

            } catch (Exception e) {

                System.out.println(e.getMessage());
                return;
            }
        }
        return ;
    }
    public void get_lastest_key_user_id() {
        String query = "select order_id from book_order order by order_id desc limit 1" ;
        try {
            preparedStatement =  connection.prepareStatement(query) ;
            resultset =  preparedStatement.executeQuery(query) ;
            ResultSetMetaData resultSetMetaData =  resultset.getMetaData() ;

            if(resultset.next()) {
                this.order_id = String.valueOf( resultset.getInt(1) );
            }
        }
        catch (Exception e) {
            System.out.println("None" + e.getMessage()) ;
        }

    }
    @Override
    public Optional<Product> get(long id) {
        return Optional.empty();
    }
    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void Update(Product product, String[] parameter) {
    }

    @Override
    public void Delete(Product product) {
    }
    @Override
    public void Add(Product product) {
    }

    public List<Cart_element> getList_Cart_Item() {

        return this.list_Cart_Item;

    }
    public void setList_Cart_Item(List<Cart_element> list_Cart_Item) {
        this.list_Cart_Item = list_Cart_Item;
    }
}
