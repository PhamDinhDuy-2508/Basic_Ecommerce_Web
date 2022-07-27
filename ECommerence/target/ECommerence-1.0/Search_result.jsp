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

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/fav.png">
    <!-- Author Meta -->
    <meta name="author" content="CodePixar">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>


</head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
    * {
        box-sizing: border-box;
    }

    body {
        font: 16px Arial;
    }

    /*the container must be positioned relative:*/
    .autocomplete {
        position: relative;
        display: inline-block;
    }

    input {
        border: 1px solid transparent;
        background-color: #f1f1f1;
        padding: 10px;
        font-size: 16px;
    }

    input[type=text] {
        background-color: #f1f1f1;
        width: 100%;
    }

    input[type=submit] {
        background-color: DodgerBlue;
        color: #fff;
        cursor: pointer;
    }

    .autocomplete-items {
        position: absolute;
        border: 1px solid #d4d4d4;
        border-bottom: none;
        border-top: none;
        z-index: 99;
        /*position the autocomplete items to be the same width as the container:*/
        top: 100%;
        left: 0;
        right: 0;
    }

    .autocomplete-items div {
        padding: 10px;
        cursor: pointer;
        background-color: #fff;
        border-bottom: 1px solid #d4d4d4;
    }

    /*when hovering an item:*/
    .autocomplete-items div:hover {
        background-color: #e9e9e9;
    }

    /*when navigating through the items using the arrow keys:*/
    .autocomplete-active {
        background-color: DodgerBlue !important;
        color: #ffffff;
    }
</style>
<body>
<!-- Navigation-->
<form action="/Ecommerence/home" method = "GET"> </form>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#!">Book Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="home">Home</a></li>
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
                <li class="nav-item">
                    <a class="nav-link" href="#!" data-bs-toggle="modal" data-bs-target="#Search" id = "myModal2">Search</a>
                    <div class="modal fade" id="Search">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Search</h4>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <form autocomplete="off">

                                        <div class="input-group ui-autocomplete "  >
                                            <input type="search" class="copy-paste form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon"  id = "search_text"  />
                                            <select class="btn-light" style="border-radius: 10px" id = "cart_list_2">

                                                <option selected value="0"> All </option>
                                                <option value="1">Category</option>
                                                <option value="2" >Name</option>
                                                <option value="3" >Author</option>
                                                <option value="4" > Product ID </option>

                                            </select>

                                        </div>
                                    </form>
                                    <script>
                                        $(document).ready(function () {


                                            $('#Search').keyup(function () {
                                                let object =   async  function get_data_Search() {
                                                    const something = await search_Product();
                                                    const  url  =  "http://localhost:8080/ECommerence/rest/Search/result"
                                                    let response_ = await fetch(url, {
                                                        headers: {
                                                            'Accept': 'application/json',
                                                            'Content-Type': 'application/json'
                                                        },
                                                        method: 'GET',
                                                    });
                                                    return response_.json() ;
                                                }
                                                object().then(value=> {
                                                    const list = [];
                                                    const obj = JSON.parse(value);
                                                    console.log(obj)


                                                    var loop_step = 0 ;

                                                    if(obj.length < 5){
                                                        loop_step =  obj.length
                                                    }
                                                    else {
                                                        loop_step =  5  ;
                                                    }

                                                    for (let i = 0; i < loop_step; i++) {
                                                        if( $('#cart_list_2 :selected').text() == "Name") {
                                                            console.log(obj[i].title)
                                                            list.push(obj[i].title.toString())
                                                        }
                                                        else if( $('#cart_list_2 :selected').text() == "Author") {
                                                            var text = obj[i].author ;

                                                            if(text[0]==' ') {
                                                                var res =  text.substring(1)
                                                            }
                                                            else {
                                                                res =  text
                                                            }

                                                            list.push(res)

                                                        }
                                                    }
                                                    console.log(list)
                                                    autocomplete(document.getElementById("search_text")  , list)

                                                })
                                            });


                                        })  ;

                                    </script>

                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="">Oke</button>
                                </div>

                            </div>
                        </div>
                    </div>

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
                    <a class= "btn btn-outline-dark dropdown-toggle " id="Dropdown_"  role="button" data-bs-toggle="dropdown" aria-expanded="false">${user_name}</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <li><a class="bi bi-file-earmark-person dropdown-item" href="profile">Profile</a></li>
                        <li><a class="bi bi-cart-check-fill dropdown-item" href="cart">Cart</a></li>
                        <li><a class="bi bi-box-arrow-left dropdown-item" href="login_post">Log Out</a></li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</nav>
<!-- Header-->

<header class="bg-dark py-5">

    <div class="container px-4 px-lg-5 my-5">


        <div class="text-center text-white">
            <h1 class="display-2 fw-bolder">Book Shop</h1>
            <p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
        </div>
    </div>
</header>
<!-- Section-->

<section class="hero hero-normal">
    <div class="container">
        <div class="row">

            <div class="col-lg-9">
                <div class="hero__search">
                    <div class="hero__search__form">
                        <form action="#">
                            <div class="hero__search__categories">
                                All Categories
                                <span class="arrow_carrot-down"></span>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-5">
                <div class="sidebar">

                    <div class="sidebar__item">
                        <h4>Price</h4>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                            <label class="form-check-label" for="flexRadioDefault1" id="label-1">
                                > $30
                            </label>
                            <script>
                                $('#flexRadioDefault1').on('click', function()
                                {
                                    var String_value = " $30 - $0" ;
                                    var value  = String_value.replace(/\s+/g, ' ').trim()
                                    get_signal_from_price_filter(value)

                                });

                            </script>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
                            <label class="form-check-label" for="flexRadioDefault2" id="label_2">
                                $20 - $30
                            </label>
                            <script>
                                $('#flexRadioDefault2').on('click', function()
                                {
                                    var String_value = $('#label_2').text() ;
                                    var value  = String_value.replace(/\s+/g, ' ').trim()
                                    get_signal_from_price_filter(value)

                                });

                            </script>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3" checke onclick="_POST_SIGNAL_Filter_Price(this.text())">

                            <label class="form-check-label" for="flexRadioDefault3" id="label_3">
                                $20 - $30
                            </label>
                            <script>
                                $('#flexRadioDefault3').on('click', function()
                                {
                                    var String_value = $('#label_3').text() ;
                                    var value  = String_value.replace(/\s+/g, ' ').trim()
                                    get_signal_from_price_filter(value)

                                });

                            </script>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault4" checked>
                            <label class="form-check-label" for="flexRadioDefault4" id="label_4">
                                $10 - $20
                            </label>
                            <script>
                                $('#flexRadioDefault4').on('click', function()
                                {
                                    var String_value = $('#label_4').text() ;
                                    var value  = String_value.replace(/\s+/g, ' ').trim()
                                    get_signal_from_price_filter(value)

                                });

                            </script>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault5" checked>
                            <label class="form-check-label" for="flexRadioDefault2" id="label_5">
                                $0 - $10
                            </label>
                            <script>
                                $('#flexRadioDefault5').on('click', function()
                                {
                                    var String_value = $('#label_5').text() ;
                                    var value  = String_value.replace(/\s+/g, ' ').trim()
                                    get_signal_from_price_filter(value)

                                });

                            </script>
                       </div>
                        <h4>Department</h4>
<%--                        <div class="row">--%>
<%--                            <div class="col-9">--%>
<%--                                <div class="list-group" id="list-tab" role="tablist">--%>
<%--                                    <a class="list-group-item list-group-item-action active" id="list-home-list" data-bs-toggle="list" href="#list-home" role="tab" aria-controls="list-home">Home</a>--%>
<%--                                    <a class="list-group-item list-group-item-action" id="list-profile-list" data-bs-toggle="list" href="#list-profile" role="tab" aria-controls="list-profile">Profile</a>--%>
<%--                                    <a class="list-group-item list-group-item-action" id="list-messages-list" data-bs-toggle="list" href="#list-messages" role="tab" aria-controls="list-messages">Messages</a>--%>
<%--                                    <a class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list" href="#list-settings" role="tab" aria-controls="list-settings">Settings</a>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>

                    </div>

                </div>
            </div>


            <div class="col-lg-9 col-md-7">
                <div class="product__discount"id="title_search">
<%--                    <div class="section-title product__discount__title" >--%>
<%--                        <h2>Search result :  ""</h2>--%>
<%--                    </div>--%>
                </div>
                    <div class="filter__item">
                        <div class="row">
                            <div class="col-lg-4 col-md-5">

                                <div class="filter__sort">
                                    <select place>
                                        <option data-hidden="true">Sort By</option>

                                        <option value="0">A -> Z</option>
                                        <option value="0">Z -> A</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="filter__found" id = "amount_result">
<%--                                    <h6><span>16</span> Products found</h6>--%>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3">
                                <div class="filter__option">
                                    <span class="icon_grid-2x2"></span>
                                    <span class="icon_ul"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                <div class="row" id="search_res">

                    <div class="row gx-3 gx-lg-3 row-cols-4 row-cols-md-4 row-cols-xl-5 justify-content-center"  id = "product_list">
<%--                        <div class="product__item">--%>
<%--                            <div class="product__item__pic set-bg" data-setbg="img/product/product-1.jpg">--%>
<%--                                <ul class="product__item__pic__hover">--%>
<%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
<%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
<%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
<%--                                </ul>--%>
<%--                            </div>--%>
<%--                            <div class="product__item__text">--%>
<%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
<%--                                <h5>$30.00</h5>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                </div>

        </div>

                <div class="product__pagination">
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#"><i class="fa fa-long-arrow-right"></i></a>
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

<script src="js/scripts.js"></script>
<script>
    function Close_Modal() {
        console.log("CLOSE") ;
        $("#buttonclose").on("click", function(){
            console.log("adasd")
            $("myModal2").modal('hide');
            ;
        });

    }
</script>
<%--<script>--%>
<%--    function add_cart_element(book_id) {--%>
<%--        console.log(book_id)--%>

<%--        $.ajax({--%>
<%--            type: "get",--%>
<%--            async: true,--%>
<%--            data :{--%>
<%--                book_id : book_id--%>
<%--            },--%>
<%--            data_type : 'json'  ,--%>
<%--            url:"/ECommerence/cart"--%>
<%--        });--%>
<%--        add_size_list() ;--%>
<%--    }--%>
<%--</script>--%>
<script>
    function add_size_list() {
        console.log("DO Post")
        $.ajax({
            type: "post" ,
            success : function (responese){
                console.log("check") ;
            } ,
            url: "/ECommerence/home"
        }) ;
    }
</script>
<script>
    $(document).ready(function() {

        console.log("test123")
        var ctrlDown = false,
            ctrlKey = 17,
            cmdKey = 91,
            vKey = 86,
            cKey = 67;

        $(document).keydown(function(e) {
            if (e.keyCode == ctrlKey || e.keyCode == cmdKey) {
                console.log("test Ctrl")
                ctrlDown = true;
            }
        }).keyup(function(e) {

            if (e.keyCode == ctrlKey || e.keyCode == cmdKey) ctrlDown = false;
        });

        $(".no-copy-paste").keydown(function(e) {
            if (ctrlDown && (e.keyCode == vKey || e.keyCode == cKey)) return false;
        });

        // Document Ctrl + C/V
        $(document).keydown(function(e) {
            if (ctrlDown && (e.keyCode == cKey)) console.log("Document catch Ctrl+C");
            if (ctrlDown && (e.keyCode == vKey)) console.log("Document catch Ctrl+V");
        });
    });

</script>
<script>

    function  Search_from_character(char ,  Cate) {

    }
</script>
<script>
    function  see_all_search_result() {


    }
</script>

<script>
    async function search_Product() {

        var category_ = $('#cart_list_2 :selected').text()
        var text_ = $("#search_text").val()
        const url = "http://localhost:8080/ECommerence/rest/Search/process";
        const pararams = {
            cate: category_,

            name: text_
        };
        const options = {
            method: "POST",
            body: JSON.stringify(pararams),
            headers: {
                'Content-Type': 'application/json',
            }
        }
        fetch(url, options).then(response => response.json())
            .then(response => {
                console.log("response suscess");
            })
    }
</script>

<script>
    function autocomplete(inp, arr) {
        /*the autocomplete function takes two arguments,
        the text field element and an array of possible autocompleted values:*/
        var currentFocus;
        /*execute a function when someone writes in the text field:*/
        inp.addEventListener("input", function(e) {
            var a, b, i, val = this.value;
            /*close any already open lists of autocompleted values*/
            closeAllLists();
            if (!val) { return false;}
            currentFocus = -1;
            /*create a DIV element that will contain the items (values):*/
            a = document.createElement("DIV");
            a.setAttribute("id", this.id + "autocomplete-list");
            a.setAttribute("class", "autocomplete-items");
            /*append the DIV element as a child of the autocomplete container:*/
            this.parentNode.appendChild(a);
            /*for each item in the array...*/
            for (i = 0; i < arr.length; i++) {
                /*check if the item starts with the same letters as the text field value:*/
                if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                    /*create a DIV element for each matching element:*/
                    b = document.createElement("DIV");
                    /*make the matching letters bold:*/
                    b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                    b.innerHTML += arr[i].substr(val.length);
                    /*insert a input field that will hold the current array item's value:*/
                    b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                    /*execute a function when someone clicks on the item value (DIV element):*/
                    b.addEventListener("click", function(e) {
                        /*insert the value for the autocomplete text field:*/
                        inp.value = this.getElementsByTagName("input")[0].value;
                        /*close the list of autocompleted values,
                        (or any other open lists of autocompleted values:*/
                        closeAllLists();
                    });
                    a.appendChild(b);
                }
            }
        });
        /*execute a function presses a key on the keyboard:*/
        inp.addEventListener("keydown", function(e) {
            var x = document.getElementById(this.id + "autocomplete-list");
            if (x) x = x.getElementsByTagName("div");
            if (e.keyCode == 40) {
                /*If the arrow DOWN key is pressed,
                increase the currentFocus variable:*/
                currentFocus++;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 38) { //up
                /*If the arrow UP key is pressed,
                decrease the currentFocus variable:*/
                currentFocus--;
                /*and and make the current item more visible:*/
                addActive(x);
            } else if (e.keyCode == 13) {
                /*If the ENTER key is pressed, prevent the form from being submitted,*/
                e.preventDefault();
                if (currentFocus > -1) {
                    /*and simulate a click on the "active" item:*/
                    if (x) x[currentFocus].click();
                }
            }
        });
        function addActive(x) {
            /*a function to classify an item as "active":*/
            if (!x) return false;
            /*start by removing the "active" class on all items:*/
            removeActive(x);
            if (currentFocus >= x.length) currentFocus = 0;
            if (currentFocus < 0) currentFocus = (x.length - 1);
            /*add class "autocomplete-active":*/
            x[currentFocus].classList.add("autocomplete-active");
        }
        function removeActive(x) {
            /*a function to remove the "active" class from all autocomplete items:*/
            for (var i = 0; i < x.length; i++) {
                x[i].classList.remove("autocomplete-active");
            }
        }
        function closeAllLists(elmnt) {
            /*close all autocomplete lists in the document,
            except the one passed as an argument:*/
            var x = document.getElementsByClassName("autocomplete-items");
            for (var i = 0; i < x.length; i++) {
                if (elmnt != x[i] && elmnt != inp) {
                    x[i].parentNode.removeChild(x[i]);
                }
            }
        }
        /*execute a function when someone clicks in the document:*/
        document.addEventListener("click", function (e) {
            closeAllLists(e.target);
        });
    }

</script>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>

<script>
    $(document).ready(function () {

        async function  load_data(_url) {

            const url = parent.document.URL;

            let search_res = url.split("_")[2]
            search_res = search_res.replaceAll('%20' , '')
            $("#title_search").append(" <div class= 'section-title product__discount__title' > <h2>Search result :"+search_res+" </h2> </div>")

            let object =   async  function get_data_Search() {
                const something = await search_Product();
                const  url  =  _url
                let response_ = await fetch(url, {
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    method: 'GET',
                });
                return response_.json() ;
            }
            object().then(value=> {
                var list = [];
                list = value.toArray
                console.log(value)
                $('#amount_result').append("<h6><span>"+value.length+"</span> Products found</h6>>")

                for(var i = 0 ; i < value.length ; i++) {

                    $('#product_list').append(
                        "<div class='card w-25'>"+
                            <!-- Product image-->
                           " <img class='card-img-top 'src='"+value[i].path+"' />" +
                            <!-- Product details-->
                            "<div class='card-body pe-1'>" +
                                "<div class='text-center'>" +
                        "<a href='detail?book_id=" +value[i].book_id +"' class='card-link'>"+value[i].title+"</a>"  +
                         "</div>" +
                            "</div>" +
                            <!-- Product actions-->
                        "<div class='text-center'><a >Price :"+value[i].pRICE+"$</a></div>" +

                        "<div class='card-footer p-4 pt-0 border-top-0 bg-transparent'>" +
                                "<div class='text-center'>" +
                                  " <a data-bs-toggle='modal' data-bs-target='#myModal' class='btn btn-outline-dark mt-auto' href=' ' onclick='add_cart_element('${i.book_id}', this)'>Add to Cart</a>" +
                                    "<div class='modal fade' id='myModal'>" +
                                        "<div class='modal-dialog'> "+
                                            "<div class='modal-content''>" +

                                                <!-- Modal Header -->
                                                "<div class='modal-header'>"+
                                                    "<h4 class='modal-title'>Notify</h4>"+
                                                    "<button type='button' class='btn-close' data-bs-dismiss='modal'></button>" +
                                                "</div>"+

                                                <!-- Modal body -->
                                                "<div class='modal-body'><p>THIS PRODUCT WAS ADDED IN YOUR CARD</p></div>" +

                                                <!-- Modal footer -->
                                                "<div class='modal-footer'>" +
                                                    "<button type='button' class='btn btn-danger' data-bs-dismiss='modal'>Oke</button>"+
                                                "</div></div></div></div></div></div></div> </div></div>")
                }
            })


            }
        load_data("http://localhost:8080/ECommerence/rest/Search/result_all")
    })

</script>
<script>
    async function get_signal_from_price_filter(condition_signal) {
        var temp = condition_signal.replaceAll('$', ' ').trim() ;

        console.log(condition_signal)

        let object =  await _POST_SIGNAL_Filter_Price(temp)
    }


</script>
<script>
     async function _POST_SIGNAL_Filter_Price(condition_signal){

         var signal =  condition_signal

         var Start =  signal.split("-")[0]
         var end_ =  signal.split("-")[1]
         var start = Start.replace(/\s+/g, ' ').trim()
         var  end =  end_.replace(/\s+/g, ' ').trim()




         const pararams = {
             Start : start,
             End: end
         };
         console.log(pararams)
         const url = "http://localhost:8080/ECommerence/rest/filter/Filter_price/"+start+"_"+end ;

         const options = {
             method: "POST",
             body: JSON.stringify(pararams),
             headers: {
                 'Content-Type': 'application/json',
             }
         }

         fetch(url, options).then(response => response.json())
             .then(response => {
                 console.log("response suscess");
             })
         load_result_filter()
    }

</script>

<script>
    function  load_result_filter() {
        const boxes = document.querySelectorAll('.card');

        boxes.forEach(box => {
            box.remove();
        });

        let object =   async  function get_data_Search() {
            const something = await search_Product();
            const  url  =  "http://localhost:8080/ECommerence/rest/filter/result_Filter"
            let response_ = await fetch(url, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'GET',
            });
            return response_.json() ;
        }
        object().then(value=> {
            var list = [];
            list = value.toArray

            object().then(value=> {
                var list = [];
                list = value.toArray

                for(var i = 0 ; i < value.length ; i++) {
                    console.log(i)

                    $('#product_list').append(
                        "<div class='card w-25'>"+
                        <!-- Product image-->
                        " <img class='card-img-top 'src='"+value[i].path+"' />" +
                        <!-- Product details-->
                        "<div class='card-body pe-1'>" +
                        "<div class='text-center'>" +
                        "<a href='detail?book_id=" +value[i].book_id +"' class='card-link'>"+value[i].title+"</a>"  +
                        "</div>" +
                        "</div>" +
                        <!-- Product actions-->
                        "<div class='text-center'><a >Price :"+value[i].pRICE+"$</a></div>" +

                        "<div class='card-footer p-4 pt-0 border-top-0 bg-transparent'>" +
                        "<div class='text-center'>" +
                        " <a data-bs-toggle='modal' data-bs-target='#myModal' class='btn btn-outline-dark mt-auto'" +
                        "onclick= add_cart_element ("   +value[i].book_id +   ", this)>Add to Cart</a>" +
                        "<div class='modal fade' id='myModal'>" +
                        "<div class='modal-dialog'> "+
                        "<div class='modal-content''>" +

                        <!-- Modal Header -->
                        "<div class='modal-header'>"+
                        "<h4 class='modal-title'>Notify</h4>"+
                        "<button type='button' class='btn-close' data-bs-dismiss='modal'></button>" +
                        "</div>"+

                        <!-- Modal body -->
                        "<div class='modal-body'><p>THIS PRODUCT WAS ADDED IN YOUR CARD</p></div>" +

                        <!-- Modal footer -->
                        "<div class='modal-footer'>" +
                        "<button type='button' class='btn btn-danger' data-bs-dismiss='modal'>Oke</button>"+
                        "</div></div></div></div></div></div></div> </div></div>")
                }
            })



     })
    }
</script>
<script>
    function add_cart_element(book_id) {
        console.log(book_id)

        $.ajax({
            type: "get",
            async: true,
            data :{
                book_id : book_id
            },
            data_type : 'json'  ,
            url:"/ECommerence/cart"
        });
        add_size_list() ;
    }
</script>
<script>
    async function  load_data(_url) {

        const url = parent.document.URL;

        let search_res = url.split("_")[2]
        search_res = search_res.replaceAll('%20' , '')
        $("#title_search").append(" <div class= 'section-title product__discount__title' > <h2>Search result :"+search_res+" </h2> </div>")

        let object =   async  function get_data_Search() {
            const something = await search_Product();
            const  url  =  _url
            let response_ = await fetch(url, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'GET',
            });
            return response_.json() ;
        }
        object().then(value=> {
            var list = [];
            list = value.toArray
            console.log(value)
            $('#amount_result').append("<h6><span>"+value.length+"</span> Products found</h6>>")

            for(var i = 0 ; i < value.length ; i++) {

                $('#product_list').append(
                    "<div class='card w-25'>"+

                    <!-- Product image-->
                    " <img class='card-img-top 'src='"+value[i].path+"' />" +
                    <!-- Product details-->
                    "<div class='card-body pe-1'>" +
                    "<div class='text-center'>" +
                    "<a href='detail?book_id=" +value[i].book_id +"' class='card-link'>"+value[i].title+"</a>"  +
                    "</div>" +
                    "</div>" +
                    <!-- Product actions-->
                    "<div class='text-center'><a >Price :"+value[i].pRICE+"$</a></div>" +

                    "<div class='card-footer p-4 pt-0 border-top-0 bg-transparent'>" +
                    "<div class='text-center'>" +
                    " <a data-bs-toggle='modal' data-bs-target='#myModal' class='btn btn-outline-dark mt-auto' href=' ' onclick='add_cart_element('${i.book_id}', this)'>Add to Cart</a>" +
                    "<div class='modal fade' id='myModal'>" +
                    "<div class='modal-dialog'> "+
                    "<div class='modal-content''>" +

                    <!-- Modal Header -->
                    "<div class='modal-header'>"+
                    "<h4 class='modal-title'>Notify</h4>"+
                    "<button type='button' class='btn-close' data-bs-dismiss='modal'></button>" +
                    "</div>"+

                    <!-- Modal body -->
                    "<div class='modal-body'><p>THIS PRODUCT WAS ADDED IN YOUR CARD</p></div>" +

                    <!-- Modal footer -->
                    "<div class='modal-footer'>" +
                    "<button type='button' class='btn btn-danger' data-bs-dismiss='modal'>Oke</button>"+
                    "</div></div></div></div></div></div></div> </div></div>")
            }
        })


    }
</script>


</body>
</html>

