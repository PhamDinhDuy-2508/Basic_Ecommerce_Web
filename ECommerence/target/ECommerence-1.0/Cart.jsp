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

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">


</head>
<body>
<!-- Navigation-->
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
</nav>
<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-2 px-lg-3 my-5">

        <div class="text-center text-white">

            <h1 class= "bi bi-cart4 display-2 fw-bolder">Your Cart</h1>
        </div>
    </div>
</header>
<!-- Section-->

<section class="h-100 h-custom" style="background-color: #f9f9f9;">
    <div class="container">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                                    <div class="d-flex justify-content-between align-items-center mb-5" id="sort_cart">
                                        <h1>
                                            <select class="form-select  form-select-sm" id = "cart_list_2">
                                                <option selected> Sort By</option>
                                                <option value="2"> Amount  : High -> LOW</option>
                                                <option value="1" >Price : High  ->  low</option>
                                                <option value="3" > Total Price : High  ->  low</option>

                                            </select>

                                        </h1>
                                        <h1 class=" fw-bold mb-0 text-black ">Shopping Cart</h1>
                                        <h6 class="mb-0 text-muted">${List_length} ITEMS</h6>
                                    </div>
                                    <section class="cart_area">
                                        <div class="container">
                                            <div class=" col-xl">
                                                <div class="table-responsive">
                                                    <div class="col">
                                                    <table class="table table-hover table-xxl" id="table">
                                                        <thead class="table-dark">
                                                        <tr>
                                                            <th scope="col" id="name">Product </th>
                                                            <th scope="col" id="price" >Price</th>
                                                            <th scope="col" id="quantity">Quantity</th>
                                                            <th scope="col" id=Total>Total</th>
                                                            <th scope="col">     </th>
                                                        </tr>
                                                        </thead>
                                                        <c:forEach items = "${List_product_in_cart}" var="i" >
                                                        <tbody >
                                                        <tr  id ="cart_${i.getBook_ID()}" class="cart_table">
                                                            <td id="title">
                                                                <div class="row ">
                                                                    <div class="col-md ">
                                                                    <img
                                                                            src = "data:image/jpg;base64,${i.getBit_image()}"
                                                                            class="img-fluid rounded-3"  alt="" height="70" width="70">
                                                                            <h8 class="text-black mb-1">${i.getTitle()}</h8>
                                                                     </div>
                                                                </div>
                                                            </td>
                                                            <td id="price_of_cart">
                                                                <div class="col-md ">
                                                                    <div class="Price_cart row"  >
                                                                        <h5 class="text-black mb-1 form-control">${i.getPrice()}$</h5>

                                                                    </div>
                                                                </div>

                                                            </td>
                                                            <td id="Amount">
                                                                <div class="Amount_increase row-cols-sm-3">
                                                                <button class="formdown btn btn-link "id="formdown" onclick="this.parentNode.querySelector ('input[type=number]').stepDown() "/>
                                                                    <i class="fas fa-minus"></i>
                                                                </button>
                                                                <input class="input_amount" id="form1"  max="15" min="0" name="quantity" value=${i.amount}  type="number" onchange="update_amount('${i.getBook_ID()}' , this.value)"
                                                                       width="25" height="15">
                                                                <button class="formup btn btn-link" id="formup" onclick="this.parentNode.querySelector('input[type=number]').stepUp()" />
                                                                    <i class="fas fa-plus"></i>
                                                                </div>
                                                            </td>
                                                            <td class="total_price">

                                                                <label id="Total_Price">${i.get_total_price()}$</label>
                                                            </td>
                                                            <td>
                                                                   <button id="delete"  type="button" href="#" class="btn btn-outline-danger bi bi-trash" onclick="reload_remove_function('${i.getBook_ID()}' , '${i.getUser_ID()}' , this)"  />

                                                            </td>

                                                        </tr>
                                                        </c:forEach>

                                                    </table>
                                                    </div>
                                                        <div class="row">
                                                            <div class="col-lg-6">
                                                                <div class="shoping__continue">
                                                                    <div class="shoping__discount">
                                                                        <h5>Discount Codes</h5>
                                                                        <form action="#">
                                                                            <input type="text" placeholder="Enter your coupon code">
                                                                            <button type="submit" class="site-btn">APPLY COUPON</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="ChecK_out_area col-lg-6">
                                                                <div  class="shoping__checkout text-black bg-opacity-75 form-control   ">
                                                                    <h5>Cart Total</h5>
                                                                    <ul id = "total_price_cart">
                                                                        <li  >Total price  <span class="all_total_price text-white form-control bg-dark" style="width: fit-content" >$${total_Price}</span></li>
                                                                    </ul>
                                                                    <form method="post" action="checkout">                                           <div class="pt-5">
                                                                        <h6 class="mb-0"><button  href="checkout"  type="button"  onclick="send_checkout()" class="text-body accordion-button"><i
                                                                                class="fas fa-long-arrow-alt-left me-2"></i>PROCEED CHECKOUT</button></h6>
                                                                    </div>
                                                                    </form>
                                                                </div>

                                                            </div>


                                                        </div>


                                                </div>

                                                </div>

                                        </div>



                                        <div class="pt-5 ">

                                            <h6 class="mb-0"><a href="home" class="text-body"><i
                                                    class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                                        </div>
                                    </section>

                        </div>--%>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<%--<script type="text/javascript" src="Scripts/bootstrap.min.js"></script>--%>
<%--<script type="text/javascript" src="Scripts/jquery-2.1.1.min.js"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function send_checkout() {
        $.ajax({
            url:  "/ECommerence/checkout" ,
            type : "post" ,
            success : function (data) {
                console.log(data)
                if(data == "false") {
                    console.log("check")
                    location.href = "checkout"
                }
            }
        });
    }


</script>
<script>
    function reload_remove_function(book_id, user_id  ,  element) {

        console.log("gi");
        console.log(book_id) ;
        $('#table').ready(function (){
            console.log(this)
            // Delete row on delete button click
            $('#delete').on("click", function(){

                console.log("delete")
                var  id =  "#cart_"+book_id ;
                $(id).remove()

            });
        })  ;
        console.log(book_id)
        $.ajax({
            url:  "/ECommerence/remove" ,
            type: "get",
            data:{
                book_id : book_id ,
                user_id  : user_id
            } ,
            dataType: 'json',
            success : function (response) {

                ele.remove()
            } ,
            error : function () {
                console.log("error")
                element.remove()
                var  id =  "#cart_"+book_id ;
                $(id).remove()
            }
        });

    }
</script>

<script type="text/javascript">
    $(window).bind('unload', function(){
        console.log("reload")
        $.ajax({
            type: "post",
            url: "/ECommerence/cart"
        });
    });
</script>
<script>
    function add_size_list(amount) {

        $.ajax({
            type: "post" ,
            async:  true ,
            data: {
                size_of_cart : amount
            } ,
            dataType : 'json' ,
            url: "/ECommerence/home"
        }) ;

    }
</script>
<script>
    function update_amount( book_id  , amount) {
        console.log(book_id) ;


        $.ajax({
                type :"post" ,
                async: true ,
                data : {
                    book_id: book_id ,
                    cart_amount :amount
                }  ,
                dataType : 'json' ,
                url :  "/ECommerence/update_cart"
            }) ;
    }
</script>
<script>
    function Sort_signal(signal) {
        sortTable(signal)
    }
</script>
<script>
    $('#cart_list_2').on('change' , function (){
        Sort_signal($(this).val());
    }) ;
</script>
<SCRIPT>
    function sortTable(signal) {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("table");
        switching = true;

        while (switching) {
            switching = false;
            rows = table.rows;

            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;

                x = rows[i].getElementsByTagName("td")[Number(signal)];
                y = rows[i + 1].getElementsByTagName("td")[Number(signal)];

                var character_1 =  x.innerHTML.toLowerCase() ;
                var character_2 =  y.innerHTML.toLowerCase() ;

                character_1.replace('$' , '') ;
                character_2.replace('$' , '') ;


                    if (character_2 >character_1) {
                            shouldSwitch = true;
                            break;
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }
</SCRIPT>
<SCRIPT>
    function Update_Total_amount(old_price , new_price) {
        var total_price =  $(".ChecK_out_area").find(".all_total_price").text()

        old_price =  old_price.replace('$' ,  '') ;
        total_price =  total_price.replace('$' ,'') ;


        var result  =  Number(total_price)-Number(old_price)+ Number(new_price );
        $(".ChecK_out_area").find(".all_total_price").text(Math.round(Number(result)) + "$") ;

    }
</SCRIPT>

<script>
    $(".input_amount").change(function (){
        $row = $(this).closest("tr");    // Find the row
        $text = $row.find(".Price_cart").text();
        $text =  $text.replace('$' , '') ;
        old_price = $row.find(".total_price").text() ;
        var amount = $row.find('.input_amount').val()

        update    =  Number( amount )  * Number( $text )  ;

        console.log( $row.find(".total_price").text(Math.round(update) + "$") );

        Update_Total_amount(old_price ,  update) ;
    }) ;
</script>
</body>
</html>
