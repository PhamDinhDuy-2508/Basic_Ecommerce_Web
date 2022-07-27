<%@ page import="com.ECommerence.entity.Session" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />


    <title>Shop Homepage - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link href="css/jquery-countryselector.min.css" rel="stylesheet" />
    <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="//unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="//unpkg.com/bootstrap-select@1.12.4/dist/css/bootstrap-select.min.css" type="text/css" />
    <link rel="stylesheet" href="//unpkg.com/bootstrap-select-country@4.0.0/dist/css/bootstrap-select-country.min.css" type="text/css" />

    <script src="//unpkg.com/jquery@3.4.1/dist/jquery.min.js"></script>
    <script src="//unpkg.com/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="//unpkg.com/bootstrap-select@1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script src="//unpkg.com/bootstrap-select-country@4.0.0/dist/js/bootstrap-select-country.min.js"></script>
</head>
<div class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#!">Book Shop</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="home">About</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="Dropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Category</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <%--                        <c:forEach items ="${List_Category}" var = "i">--%>
                        <%--                            <li><a class="dropdown-item" href="category?category_id=${i.category_id}">${i.getName()}</a></li>--%>
                        <%--                        </c:forEach>--%>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">more...</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown"  role="button"  aria-expanded="false">Shop</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">All Products</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                        <li><a class="dropdown-item" href="login">New Arrivals</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex px-5 " action="cart"  method="post">
                <div class="container px-2 px-lg-2 my-2" >
                    <button class="btn btn-outline-dark" type="submit">
                        <i class="bi-cart-fill me-2" ></i>
                        Cart
                        <span class="badge bg-dark text-white ms-2 rounded-pill">${amount}</span>
                    </button>
                    <c:if test="${user == false}">
                        <div class="btn">
                            <a class="btn btn-outline-dark bi bi-person-circle " href="login" role="button">Sign In</a>
                        </div>
                    </c:if>

                </div>
            </form>
            <c:if test="${user == true}">
                <div class="dropdown">
                    <a class= "btn btn-outline-dark dropdown-toggle " id="Dropdown_"  data-bs-target="#ddropdown_"  role="button" data-bs-toggle="dropdown" aria-expanded="false">${user_name}</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink" id="ddropdown_">
                        <li><a class="bi bi-file-earmark-person dropdown-item" href="profile">Profile</a></li>
                        <li><a class="bi bi-cart-check-fill dropdown-item" href="cart">Cart</a></li>
                        <li><a class="bi bi-box-arrow-left dropdown-item" href="login_post">Log Out</a></li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>

</head>
<header class="bg-dark py-5">
    <div class="container px-2 px-lg-3 my-5">

        <div class="text-center text-white">

            <h1 class= "bi bi-cart4 display-2 fw-bolder">Check Out</h1>
        </div>
    </div>
</header>

<!-- Navigation-->

<section class="checkout_area section_gap">
    <div class="container">
        <div class="billing_details">
            <div class="row">
                <div class="col-lg-8">
                    <h3>Billing Details</h3>
                    <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="first" name="name" placeholder="first name">
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="last" name="name" placeholder="last name">

                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="number" name="number" placeholder="phone number">
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="col-md-4">
                            <select class="form-select"   data-flag="true"  >
                                <option selected value="1">VietNam</option>
                            </select>
                            </div>
                            <div class="col-md-4">
                            <select class= "form-select "   data-flag="true" id="province"  >
                              <script type="text/javascript" >
                                  $( document ).ready(function() {

                                      $.ajax({
                                          url: "https://provinces.open-api.vn/api/p/",
                                          type: "Get",
                                          dataType: "json",
                                          data: JSON.stringify({}),
                                          success: function (data) {

                                              var  code_province  ;
                                              var province

                                              const object = JSON.parse(JSON.stringify(data), function (key, value) {

                                                  if (key == "name") {
                                                      province = value ;
                                                  }
                                                  if(key == "code") {
                                                      code_province =  value
                                                      var newOption = $('<option value="'+code_province+'">'+province+'</option>');
                                                      $('#province').append(newOption);
                                                  }
                                              });
                                          }
                                      });
                                  });


                              </script>
                            </select>
                            </div>

                            <div class="col-md-4">
                            <select class="form-select " data-flag="true" id="districts"  >
                            <script type="text/javascript" >
                                $( document ).ready(function() {

                                    $("#province").change(function () {

                                        var selectedVal = $("#province option:selected").val();
                                        var code_province = selectedVal

                                        $("#districts").children().remove()

                                        var _url = "https://provinces.open-api.vn/api/p/" + code_province + "?depth=2";

                                        var arr_districts =   load_districts_data(_url) ;

                                    });
                                });

                            </script>
                            </select>
                            </div>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="add1" name="add1" placeholder="House number - Street">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="zip" name="zip" placeholder="Postcode/ZIP">
                        </div>

                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <h3>Shipping Details</h3>
                                <input type="checkbox" id="f-option3" name="selector">
                                <label for="f-option3">Ship to a different address?</label>
                            </div>
                            <textarea class="form-control" name="message" id="message" rows="1" placeholder="Order Notes"></textarea>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4">
                    <div class="order_box">
                        <h2>Your Order</h2>
                        <ul class="list" id = "your_Order">

                            <li><a onclick="redirec_detail('1234')">Product <span>Total</span></a></li>
                            <li><a>Product <span> </span></a></li>

                        </ul>

                        <ul class="list list_2"  id=your_cost>
                        </ul>

                        <div class="payment_item">
                            <div class="radion_btn">
                                <input type="radio" id="f-option5" name="selector">
                                <label for="f-option5">Check payments</label>
                                <div class="check"></div>
                            </div>
                            <p>

                                Please send a check to Store Name, Store Street, Store Town, Store State / County,
                                Store Postcode.

                            </p>
                        </div>
                        <div class="payment_item active">

                            <div class="radion_btn" id="radio_paypal">
                                <input type="radio" id="f-option6" name="selector">
                                <label for="f-option6">Paypal</label>
                                <img src="img/product/card.jpg" alt="">
                                <div class="check"></div>
                            </div>

                            <p>
                                Pay via PayPal; you can pay with your credit card if you don?t have a PayPal
                                account.
                            </p>
                        </div>
                        <div class="creat_account">
                            <input type="checkbox" id="f-option4" name="selector">
                            <label for="f-option4">I?ve read and accept the </label>
                            <a href="#">terms & conditions*</a>
                        </div>

                        <a class="primary-btn" onclick="Post_Proceced_Checkout()">Proceed to Paypal</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src = "https://code.jquery.com/jquery-3.3.1.min.js">
</script>

</script>
<script>

        async function load_data() {
            const url = "http://localhost:8080/ECommerence/rest/checkout/"+getCookie("user_id") ;

            const response = await fetch(url);
            var data = await response.json();

            const object = JSON.parse(JSON.stringify(data)) ;

            document.getElementById("first").value = object.first_name;
            document.getElementById("last").value =  object.last_name ;
            document.getElementById("number").value =  object.phone ;
            document.getElementById("email").value =  object.email ;

            var name_of_book ;
            var Price ;
            var  amount  ;


            const object2 = JSON.parse(JSON.stringify(object.book_orders), function (key, value) {

                if(key ==  "name") {

                    name_of_book = value

                    $("#your_Order").append('<li><a href="#">'+ name_of_book+' <span class="middle">x'+amount+'</span> <span class="last">$'+Price+'</span></a></li>')

                }
                else  if(key == "_total_price") {
                    Price =  value ;
                }
                else if(key == "amount") {
                    amount =  value ;
                }
            }) ;
            $("#your_cost").append('<li><a href="#">Total <span>$'+object.total_Price+'</span></a></li>')

        }
</script>
<%--<script> load_districts_data()</script>--%>
<script>
    async  function load_districts_data(url){

        const response = await fetch(url);
        var data = await response.json();
        var object = JSON.parse(JSON.stringify(data)) ;
        var district_name  ;

        const object1 = JSON.parse(JSON.stringify(object.districts), function (key, value) {

            if (key == "name") {
                district_name = value;
            }
            if (key == "codename") {
                var newOption = $('<option value=" '+value+'">'+district_name+'</option>');
                $('#districts').append(newOption);
            }
        }) ;

    }  ;
</script>

<script>
     async  function redirect_detail() {
         var  test = "check"
         const  url = "http://localhost:8080/ECommerence/rest/checkout/"+getCookie("user_id") ;
         const  pararams  = {
             book_id  : check
         } ;
         const  options = {
             method : "POST" ,
             body : JSON.stringify(pararams)
         }
         fetch(url , options) .then(response => response.json())
             .then(response=>{
                 console.log("response suscess") ;
             })
     }
</script>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready( function () {
        var cookie_name =  document.cookie ;
        load_data() ;

    }) ;
</script>
<script>
    function change_character_in_email(character) {

        var check = character.replace(/@/g , '/') ;
            return check ;
    }
</script>
<script>
    async function Post_Proceced_Checkout() {
        var test = "check"
        var pay_method;
        var _receipt_phone = $("number").textContent
        var message = document.getElementById("message").value.toString()
        var check_term_AND_condition = document.getElementById("f-option4").checked;
        const url = "http://localhost:8080/ECommerence/rest/checkout/create"


        var current = new Date();
        console.log(current.getMinutes() + "/" + current.getHours() + "/" + current.getDate())

        if (document.getElementById("f-option6").checked) {
            pay_method = "paypal";
        }
        if (pay_method == "paypal" && check_term_AND_condition == true) {


            fetch(url, {
                method: "Post",
                body: JSON.stringify({
                    user_id: getCookie("user_id"),

                    shipping_address: get_Shipping_Address(),

                    receipt_phone: document.getElementById("number").value,

                    receipt_name: document.getElementById("first").value + document.getElementById("last").value,

                    Post_code: document.getElementById("zip").value,

                    shipping_details: document.getElementById("message").value.toString(),

                    payment_method: "paypal",
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => response.json()).then(json => console.log(json))
            const something = await get_Order_ID();

            var check =  get_Order_ID();
            get_Order_ID().then(something => {
                    console.log(something)
                     const url_2 = "http://localhost:8080/ECommerence/invoice?id=" + getCookie("user_id")+"_"+something ;
                    console.log(url_2) ;
                    window.location.href = url_2 ;
                });
        } else {

            alert("Please complete form")
        }
    }
    function get_Shipping_Address() {
        var Province  = "";
        var district = "" ;
        var HouseNumber_Street  = "";

        Province  =  $('#province option:selected').text()

        district  =  $("#districts option:selected").text();

        HouseNumber_Street = document.getElementById("add1").value ;


        var Shippping_address = Province +"-" + district +"-" + HouseNumber_Street ;


        return Shippping_address ;

    }
</script>
<script>
    $(document).ready(function() {
        var x = getCookie("user_id") ;
    });
    function getCookie(cookieName) {
        let cookie = {};
        document.cookie.split(';').forEach(function(el) {
            let [key,value] = el.split('=');
            cookie[key.trim()] = value;
        })
        return cookie[cookieName];
    }
</script>
<script>
    async function get_Order_ID() {
        const  url  =  "http://localhost:8080/ECommerence/rest/checkout/order_id"
        let response_ = await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'GET',
        });
        return response_.json() ;
    }
</script>
</nav>
</html>