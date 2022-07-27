package com.ECommerence.Service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.ECommerence.entity.Book_Order;
import com.ECommerence.entity.Customer;
import com.ECommerence.entity.Order_detail;
import com.google.gson.Gson;
import com.google.protobuf.TextFormat;
import com.opensymphony.module.sitemesh.Config;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;



@Path("/Payment_invoice")
public class Payment_Service {
    private static final String CLIENT_id = "AY3LmPCFwCTIo1L9AzXg09r2ZOwCJxiAIgNI3AQFz8o2fPWUFN0qOaTKrik4c6qc77r-EF5DOHUQmgFm";
    private static final String CLIENT_SECRET = "EIv2KIUq2WuZ61rtglentAGA-eOsVLuieVJkan9AasSVyyQvq1ttI1BrpRMWDmJ9afrK3n7UBPRaHjnO";
    private static final String MODE = "sandbox";
    private HttpSession session;
    private  JSONParser parser = new JSONParser();
    private Gson gson =  new Gson() ;


    public Payment_Service() {
    }

    public String authorizePayment(@Context HttpServletRequest request, @Context HttpServletResponse response) throws PayPalRESTException {
        session = request.getSession(true);

        Gson gson = new Gson();

        Book_Order order_detail = (Book_Order) session.getServletContext().getAttribute("order_detail");

        String _change_to_String = gson.toJson(order_detail);
        Payer payer = getPayerInformation();
//
        RedirectUrls redirectUrls = getRedirectUrls();

        List<Transaction> listTransaction = getTransactionInformation(request, response);
////

        Payment createPayment = null;

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_id, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);
    }

    protected Payer getPayerInfomation() {

        Customer customer = (Customer) session.getServletContext().getAttribute("customer_info");

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(customer.getFirst_name())
                .setLastName(customer.getLast_name()).setEmail(customer.getEmail());

        payer.setPayerInfo(payerInfo);
        return payer;
    }

    protected RedirectUrls getRedirectUrls() {
        String url = (String) session.getServletContext().getAttribute("invoice_url");
        RedirectUrls redirectUrls = new RedirectUrls();

        redirectUrls.setReturnUrl(url);
        redirectUrls.setCancelUrl("");

        return redirectUrls;
    }

    protected List<Transaction> getTransactionInformation(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return null ;
    }
    public String getApprovalLink(Payment approved_Payment) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(approved_Payment));
        List<Links> links = approved_Payment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }
        return approvalLink;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
                .setLastName("Peterson")
                .setEmail("william.peterson@company.com") ;

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    public Payment crunchifyCapturePayPalAPI(Book_Order book_order) {


        String check = book_order.getTotal() ;
        System.out.println(check);

        Payer crunchifyPayer = new Payer();
        crunchifyPayer.setPaymentMethod("paypal");

        // Redirect URLs
        RedirectUrls crunchifyRedirectUrls = new RedirectUrls();
        crunchifyRedirectUrls.setCancelUrl("http://localhost:3000/crunchifyCancel");
        crunchifyRedirectUrls.setReturnUrl("http://localhost:3000/crunchifyReturn");

        // Set Payment Details Object
        Details crunchifyDetails = new Details();
        crunchifyDetails.setShipping("2.22");
        crunchifyDetails.setSubtotal("3.33");
        crunchifyDetails.setTax("1.11");

        // Set Payment amount
        Amount crunchifyAmount = new Amount();
        crunchifyAmount.setCurrency("USD");
        crunchifyAmount.setTotal(check);
        crunchifyAmount.setDetails(crunchifyDetails);

        // Set Transaction information
        Transaction crunchifyTransaction = new Transaction();
        crunchifyTransaction.setAmount(crunchifyAmount);
        crunchifyTransaction.setDescription("Crunchify Tutorial: How to Invoke PayPal REST API using Java Client?");
        List<Transaction> crunchifyTransactions = new ArrayList<Transaction>();
        crunchifyTransactions.add(crunchifyTransaction);

        // Add Payment details
        Payment crunchifyPayment = new Payment();

        // Set Payment intent to authorize
        crunchifyPayment.setIntent("authorize");
        crunchifyPayment.setPayer(crunchifyPayer);
        crunchifyPayment.setTransactions(crunchifyTransactions);

        crunchifyPayment.setRedirectUrls(crunchifyRedirectUrls);

        // Pass the clientID, secret and mode. The easiest, and most widely used option.
        APIContext crunchifyapiContext = new APIContext(CLIENT_id,CLIENT_SECRET, MODE);

        try {

            Payment myPayment = crunchifyPayment.create(crunchifyapiContext);
            return myPayment ;
//            Gson gson =  new Gson() ;
//            String check = gson.toJson(myPayment.getLinks()) ;
//            System.out.println(check);
//
//            System.out.println("createdPayment Obejct Details ==> " + myPayment.toString());
//
//
//            // Identifier of the payment resource created
//            crunchifyPayment.setId(myPayment.getId());
//
//            PaymentExecution crunchifyPaymentExecution = new PaymentExecution();


        } catch (PayPalRESTException e) {
            System.err.println(e.getMessage());
            return null ;
        }
    }
    @GET
    @Produces("application/json")

    public Response doGet()
    {
        return Response.status(200).entity("Doget").build() ;

    }
    @GET
    @Path("/{order_id}/{user_id}")
    @Produces("application/json")
    public Response DoPost_data(@Context HttpServletRequest request , @Context HttpServletResponse response ,
                                @PathParam("order_id") String order_id , @PathParam("user_id") String user_id ) {

        try {
            session = request.getSession(true);

            Customer customer = (Customer) session.getServletContext().getAttribute("customer_info");
            Book_Order book_order = (Book_Order) session.getServletContext().getAttribute("order_detail");

            if (String.valueOf(customer.getCustomer_id()).equals(user_id) && String.valueOf(book_order.getOrder_id()).equals(order_id)) {

                Payment payment = crunchifyCapturePayPalAPI(book_order);
                JSONObject json = (JSONObject) parser.parse( gson.toJson(payment));

                return Response.status(200).entity(json).build();
            } else {
                return Response.status(200).entity("Some thing WAS WRONG").build();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(200).entity("Some thing WAS WRONG").build();
        }


    }

}
