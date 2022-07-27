package com.ECommerence.DAO;

import com.ECommerence.entity.Book_Order;
import com.ECommerence.entity.Cart_element;
import com.ECommerence.entity.Order_detail;
import com.ECommerence.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Order_DAO implements  DAO<Book_Order>{
    private  Integer ID = 0  ;
    private Connection connection =  null ;
    private PreparedStatement preparedStatement =  null ;
    private ResultSet resultset =  null ;

    public Order_DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "phamdinhduy2508");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void setID(Integer ID) {
        this.ID  =  ID ;
    }

    public void add_product(Product product) {
        String query = " INSERT INTO `bookstoredb`.`users` (`user_id`, `email`, `password`, `full_name`, `user_name`) VALUES(" ;
        query += "NULL , " ;
    }

    public void Add_Book_Order(Book_Order book_order) {

        String query = String.format("INSERT INTO `bookstoredb`.`customer` (`order_id`, `customer_id`, `order_date`, `shipping_address`, `recipient_name`, `recipient_phone`, `payment_method`, `total`, `status`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')"
                , book_order.getOrder_id(), book_order.getUser_id(), book_order.getOrder_date(), book_order.getShipping_address() , book_order.getReceipt_name() , book_order.getReceipt_phone() , book_order.getPayment_method() ,  book_order.getTotal() ,  book_order.getShipping_details()) ;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Book_Order> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Book_Order> getAll() {
        return null;
    }

    @Override
    public void Update(Book_Order book_order, String[] parameter) {}

    @Override
    public void Delete(Book_Order book_order) {}

    @Override
    public void Add(Book_Order book_order) {
        int ID_Val = 0 ;
        String query = String.format("INSERT INTO `bookstoredb`.`book_order` (`order_id`, `customer_id`, `order_date`, `shipping_address`, `recipient_name`, `recipient_phone`, `payment_method`, `total`, `status`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s') ;   "
                ,0, Integer.valueOf(book_order.getUser_id()), book_order.getOrder_date(), book_order.getShipping_address() , book_order.getReceipt_name() , book_order.getReceipt_phone() , book_order.getPayment_method() ,  book_order.getTotal() ,  book_order.getShipping_details()) ;

        try {
            Statement statement = connection.createStatement();
            int ID =   statement.executeUpdate(query , Statement.RETURN_GENERATED_KEYS);
            statement.execute("SET FOREIGN_KEY_CHECKS=0");


            for(Order_detail x : book_order.getOrder_detail_list()) {

                query = "INSERT INTO bookstoredb.order_detail (`order_id`, `book_id` , `quantity` ,`subtotal`) VALUES ( (select order_id  from book_order where customer_id = '"+book_order.getUser_id()+"'AND order_date = '"+book_order.getOrder_date()+"'), '"+ x.get_Products_().getBook_id()+"', '"
                        +x.getQuantity()+"','"+x.getSubtotal()+"')" ;
                System.out.println(query);
                statement = connection.createStatement();

                statement.executeUpdate(query , Statement.RETURN_GENERATED_KEYS);
            }
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1" );

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    public  List<Order_detail> get_Order_detail(String Order_Id) {
            Get_Oder_detail_List get_oder_detail_list =   new Get_Oder_detail_List(Order_Id) ;
            return  get_oder_detail_list.get_Order_List_from_Database() ;
    }
    public Book_Order get_Book_Order_List(String USer_Id , String order_id) {

        Book_Order book_order =  new Book_Order()  ;

        String query = "  select * from book_order where customer_id ='" + USer_Id + "' AND order_id = '" + order_id +  "'" ;


        try {
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery(query);

            ExecutorService executorService = Executors.newSingleThreadExecutor() ;
            Callable< List<Order_detail> > callable = new Callable< List<Order_detail> >() {
                @Override
                public List<Order_detail> call() throws Exception {

                        Get_Oder_detail_List get_oder_detail_list =  new Get_Oder_detail_List(order_id) ;

                        return  get_oder_detail_list.get_Order_List_from_Database() ;
                }
            } ;
            Future< List<Order_detail> > listFuture  = executorService.submit(callable) ;

            while (resultset.next()) {

                book_order.setOrder_id(Integer.valueOf(resultset.getString("order_id")));


                book_order.setUser_id(resultset.getString("customer_id"));
                book_order.setOrder_date(resultset.getString("order_date"));
                book_order.setShipping_address(resultset.getString("shipping_address"));

                book_order.setReceipt_name(resultset.getString("recipient_name"));
                book_order.setReceipt_phone(resultset.getString("recipient_phone"));
                book_order.setPayment_method(resultset.getString("payment_method"));
                book_order.setTotal(resultset.getString("total"));
                book_order.setShipping_details(resultset.getString("status"));

            }
            executorService.shutdown();
            book_order.setOrder_detail_list(listFuture.get()) ;

            return book_order ;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }
    public Book_Order get_Order_By_CusID_AND_OrID(String user_Id , String order_id) {
        ExecutorService executorService =  Executors.newSingleThreadExecutor()  ;
        String query = "select * from book_order" ;
        try {

        }
        catch (Exception e) {

        }

        return null ;
    }
    public  class  Get_Oder_detail_List{

        private String Order_Id ;
        private ExecutorService executorService = Executors.newSingleThreadExecutor() ;
        private List<Order_detail>  list_order_detail =  new ArrayList<>() ;
        private Detail_DAO detail_dao =  new Detail_DAO() ;

        public Get_Oder_detail_List( String order_Id) {
            Order_Id = order_Id;
        }
        public List<Order_detail> get_Order_List_from_Database() {

                String query =  "select * from order_detail  Where order_id  = '"+ this.Order_Id +"'"  ;

                System.out.println( query) ;
                try{
                    preparedStatement = connection.prepareStatement(query);
                    resultset = preparedStatement.executeQuery(query);

                    while (resultset.next()) {
                        Order_detail order_detail =  new Order_detail() ;

                        order_detail.setOrder_id(this.Order_Id);

                        order_detail.set_Products_(detail_dao.get_Product_By_Id(Integer.valueOf(resultset.getString("book_id")))) ;

                        order_detail.setQuantity(Integer.valueOf(resultset.getString("quantity")));
                        order_detail.setSubtotal(Float.valueOf(resultset.getString("subtotal")));

                        this.list_order_detail.add(order_detail) ;
                    }
                    return  this.list_order_detail;
                }
                catch (Exception e) {

                    System.out.println(e.getMessage());
                    return  null ;
                }

        }
        public void Shutdow() {
            this.executorService.shutdown();
        }

    }
}