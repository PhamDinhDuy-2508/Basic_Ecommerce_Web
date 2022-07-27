<<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
          pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<%--    <title>Account Settings UI Design</title>--%>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

    <div class="container">
        <!-- Navigation-->
        <form action="/Ecommerence/home" method = "GET"> </form>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Book Shop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="Dropdown"  role="button" data-bs-toggle="dropdown" aria-expanded="false">Category</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:forEach items ="${List_Category}" var = "i">
                                    <li><a class="dropdown-item" href="category?category_id=${i.category_id}">${i.getName()}</a></li>
                                </c:forEach>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">more...</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
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
                            <div class="dropdown">
                                <a class= "btn btn-outline-dark dropdown-toggle " id="Dropdown_"  role="button" data-bs-toggle="dropdown" aria-expanded="false">${user_name}</a>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <li><a class="bi bi-file-earmark-person dropdown-item" href="profile">Profile</a></li>
                                    <li><a class="bi bi-cart-check-fill dropdown-item" href="cart">Cart</a></li>
                                    <li><a class="bi bi-box-arrow-left dropdown-item" href="login_post">Log Out</a></li>
                                </ul>
                            </div>

                        </div>
                    </form>
                    <c:if test="${user == true}">

                    </c:if>
                </div>
            </div>
        </nav>
    </div>
<header class="bg-dark py-5">
    <div class="container px-2 px-lg-3 my-5">

        <div class="text-center text-white">

            <h1 > ACCOUNT SETTING</h1>
        </div>
    </div>
</header>
    <section>
<%--        <h1 class="mb-5">Account Settings</h1>--%>
        <div class="bg-white shadow rounded-lg d-block d-sm-flex">
            <div class="profile-tab-nav border-right">
                <div class="p-4">
<%--                    <div class="img-circle text-center mb-3">--%>
<%--                        <img src="img/user2.jpg" alt="Image" class="shadow">--%>
<%--                    </div>--%>
                    <h4 class="text-center">${account}</h4>
                </div>
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <a class="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab" aria-controls="account" aria-selected="true">
                        <i class="fa fa-home text-center mr-1"></i>
                        Account
                    </a>
                    <a class="nav-link" id="password-tab" data-toggle="pill" href="#password" role="tab" aria-controls="password" aria-selected="false">
                        <i class="fa fa-key text-center mr-1"></i>
                        Password
                    </a>
                </div>
            </div>
            <div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
                    <h3 class="mb-4">Account Settings</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>First Name</label>
                                <input type="text" id="first_name" class="form-control" value="${First_name}">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Last Name</label>
                                <input type="text" id="last_name" class="form-control" value="${Last_name}">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" id="email" class="form-control" value="${email}">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Phone number</label>
                                <input type="text" id="phone_number" class="form-control" value="${phone}">
                            </div>
                        </div>
                        <h1> Address </h1>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label>Home Number</label>
                                <input type="text" id="home_number" class="form-control" value="${Home_number}">
                            </div>


                        </div>


                        <div class="col-md-2">
                            <div class="form-group">
                                <label>Street</label>
                                <input type="text" id="street" class="form-control" value="${Street}">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div>
                                <label>  State  </label>
                                <input type="text" id="province" class="form-control" value="${Province}">

                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>City</label>
                                <input type="text" id="city" class="form-control" value="${city}">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Country</label>
                                <input type="text" id="Country" class="form-control" value="${country}">
                            </div>
                        </div>


                        <div class="col-md-12">
                            <div class="form-group">
                                <label>Bio</label>
                                <textarea class="form-control" rows="4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Labore vero enim error similique quia numquam ullam corporis officia odio repellendus aperiam consequatur laudantium porro voluptatibus, itaque laboriosam veritatis voluptatum distinctio!</textarea>
                            </div>
                        </div>
                    </div>
                    <div>
                        <a class="btn btn-primary"onclick="check_form_email()">Update</a>
                        <a class="btn btn-light" href="home"  onclick="return_home_page()">Cancel</a>
                    </div>
                </div>
                <div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
                    <h3 class="mb-4">Password Settings</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Old password</label>
                                <input type="password" class="form-control" id = "old_pass">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>New password</label>
                                <input type="password" class=" NewPass form-control" id="change_pass">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="Confirm form-group" >
                                <label>Confirm new password</label>
                                <input type="password" class="form-control" id="confirm_pass">
                            </div>
                        </div>

                    </div>
                    <div>
                        <button class="btn btn-primary" onclick="Confirm_pass()">Update</button>
                        <a class="btn btn-light" href="home"  onclick="return_home_page()">Cancel</a>
                    </div>
                <div class="tab-pane fade" id="notification" role="tabpanel" aria-labelledby="notification-tab">
                </div>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function check_form_email() {
        var  new_email =  document.getElementById("email").value ;
        var  signal = false ;
        var new_first_name = document.getElementById("first_name").value ;
        var new_last_name =  document.getElementById("last_name").value ;
        var  new_phone_number =  document.getElementById("phone_number").value ;
        var province =  document.getElementById("province").value ;
        var house_number = document.getElementById("home_number").value ;

        var Street  =  document.getElementById("street").value ;

        var city   = document.getElementById("city").value ;

        var Country =  document.getElementById("Country").value ;


        $.ajax({
            type  : "get" ,
            data:  {
                email : new_email ,
            } ,
            dataType: 'json' ,

            success : function (email) {
                if(email == true) {
                    signal =  true  ;
                    $.ajax({
                        type  : 'post' ,
                        data: {
                            first_name : new_first_name ,
                            last_name : new_last_name ,
                            email_new : new_email ,

                            phone_number : new_phone_number ,
                            Province : province ,
                            Home_number  : house_number ,
                            Street : Street ,

                            city : city ,
                            country : Country ,

                        }  ,

                        dataType : 'json' ,
                        url : "/ECommerence/update_info"
                    })
                    alert("Update Success")

                }
                else {
                    signal =  false ;

                    alert('Email Form is incorrect') ;

                }

            } ,
            url: "/ECommerence/update_info" ,

        }) ;

    }
</script>
<script>

    function update_info () {

    }
</script>
<script>
    function update_signal_password(password ) {
        console.log(password) ;
        var old_pass = document.getElementById("old_pass").value ;
        console.log(old_pass)
        $.ajax({
            type : "Post"  ,
            data : {
                password : password ,
                password_old : old_pass
            },

            dataType : 'json'  ,
            url: "/ECommerence/update_pass_profile" ,
            success : function (data) {
                console.log(data)
                if(data == true
                ) {
                    alert("Update is Successed")

                }
                 else {
                     alert("Password is Incorrect")
                }
            }
        }) ;
    }
    function Confirm_pass() {

        var  confirm_pass  =  document.getElementById("confirm_pass").value ;
        var  change_pass  = document.getElementById("change_pass").value ;

        console.log(confirm_pass ,  change_pass) ;

        if(change_pass == confirm_pass) {
            update_signal_password(change_pass)
        }
        else {
            console.log("incorrect") ;
        }
    }
    function return_home_page()
    {  console.log("return home page ")
        $.ajax({
            type: "get" ,
            url: "/ECommerence/home"
        }) ;
    }

</script>
</body>
</html>