<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 6/24/2022
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
    <!--  All snippets are MIT license http://bootdey.com/license -->
    <title>Book Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<div class="page-content container">
    <div class="page-header text-blue-d2">
        <h1 class="page-title text-secondary-d1">
            Invoice
            <small class="page-info" id = "invoice_id">
                <i class="fa fa-angle-double-right text-80"></i>
            </small>
        </h1>

        <div class="page-tools">
            <div class="action-buttons">
                <a class="btn bg-white btn-light mx-1px text-95" href="#" data-title="Print">
                    <i class="mr-1 fa fa-print text-primary-m1 text-120 w-2"></i>
                    Print
                </a>
                <a class="btn bg-white btn-light mx-1px text-95" href="#" data-title="PDF">
                    <i class="mr-1 fa fa-file-pdf-o text-danger-m1 text-120 w-2"></i>
                    Export
                </a>
            </div>
        </div>
    </div>

    <div class="container px-0" >
        <div class="row mt-4">
            <div class="col-12 col-lg-12">

                <div class="row">
                    <div class="col-12">
                        <div class="text-center text-150" >
                            <i class="fa fa-book fa-2x text-success-m2 mr-1"></i>
                            <span  h class="text-default-d3">BookShop.com</span>
                        </div>
                    </div>
                </div>

                <!-- .row -->

                <hr class="row brc-default-l1 mx-n1 mb-4" />

                <div class="row">
                    <div class="col-sm-6">
                        <div id="name">
                            <span class="text-sm text-grey-m2 align-middle">To:</span>
                        </div>
                        <div class="text-grey-m2" >
                            <div class="my-1" id="Street_country">
                                Street, City
                            </div>
                            <div class="my-1" id="Sate_Country">
                                State, Country
                            </div>
                            <div class="my-1">
                                <i class="fa fa-phone  text-grey-m2 align-middle" id="phone"></i>
<%--                                <b class="text-600">111-111-111</b></div>--%>
                            </div>
                        </div>
                    </div>
                    <!-- /.col -->

                    <div class="text-95 col-sm-6 align-self-start d-sm-flex justify-content-end">
                        <hr class="d-sm-none" />
                        <div class="text-grey-m2">
                            <div class="mt-1 mb-2 text-secondary-m1 text-600 text-125">
                                Invoice
                            </div>

                            <div class="my-2"><i class="fa fa-circle text-blue-m2 text-xs mr-1" id="order_id_1"></i>
<%--                                <span class="text-600 text-90">ID:</span>${order_id}--%>
                            </div>

                            <div class="my-2"><i class="fa fa-circle text-blue-m2 text-xs mr-1" id = "date_time"></i>
<%--                                <span class="text-600 text-90">Issue Date:</span> Oct 12, 2019--%>
                            </div>

                            <div class="my-2"><i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span class="text-600 text-90">Status:</span> <span class="badge badge-warning badge-pill px-25">Unpaid</span></div>
                        </div>
                    </div>
                    <!-- /.col -->
                </div>

                <div class="mt-5" id="table_product">
                    <div class="row text-600 text-white bgc-default-tp1 py-25">
                        <div class="col-4 col-sm-4">Product name</div>

                        <div class="d-none d-sm-block col-4 col-sm-2">Amount</div>
                        <div class="d-none d-sm-block col-sm-2">Unit Price</div>
                        <div class="col-2">Price</div>
                    </div>



                    </div>

                    <div class="row border-b-2 brc-default-l2"></div>

                    <div class="row mt-3">
                        <div class="col-12 col-sm-7 text-grey-d2 text-95 mt-2 mt-lg-0">
                        </div>

                        <div class="col-12 col-sm-5 text-grey text-90 order-first order-sm-last">

                            <div class="row my-2 align-items-center bgc-primary-l3 p-2">
                                <div class="col-7 text-right">
                                    Total Amount
                                </div>
                                <div class="col-5" id="total">
<%--                                    <span class="text-150 text-success-d3 opacity-2">$2,475</span>--%>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr />

                    <div>
                        <span class="text-secondary-d1 text-105">Thank you for your business</span>
                        <h6 class="mb-0"><a href="home" class="text-body"><i
                                class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>

                        <button type="button" class="btn btn-info btn-bold px-4 float-right mt-3 mt-lg-0" data-bs-toggle="modal" data-bs-target="#exampleModal">Pay</button>
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <meta name="viewport" content="width=device-width, initial-scale=1">
                                    </div>
                                    <div class="modal-body">
                                        <script src="https://www.paypal.com/sdk/js?client-id=test&currency=USD"></script>
                                        <!-- Set up a container element for the button -->
                                        <div id="paypal-button-container"></div>
                                        <script>
                                                var prsent_location = window.location.href;

                                                let temp = prsent_location.split("=");
                                                let user_and_order = temp[1];
                                                let user = user_and_order.split("_")[0];
                                                let order_id = user_and_order.split("_")[1];


                                                const url = "http://localhost:8080/ECommerence/rest/Payment_invoice/103/168323" ;

                                                var object1  ;
                                                var total_price ;
                                                let object =  async function loadNames() {
                                                    const response = await fetch(url);
                                                    const names = await response.json();
                                                    const object1 = JSON.parse(JSON.stringify(names));

                                                    let  total_price = object1.transactions[0].amount.total  ;

                                                    return await total_price  ;

                                                    // logs [{ name: 'Joker'}, { name: 'Batman' }]
                                                }

                                                paypal.Buttons({


                                                    // Order is created on the server and the order id is returned

                                                    createOrder: (data, actions) => {

                                                        object().then(value => console.log(value))

                                                        var c =  $("#total").text() ;
                                                        var array_c = c.split(" ") ;
                                                        var total_price = array_c[array_c.length-1].split("$") ;

                                                        return actions.order.create({

                                                            purchase_units: [{
                                                                amount: {
                                                                    value: total_price[1] // Can also reference a variable or function
                                                                }
                                                            }]
                                                        });
                                                    },

                                                }).render('#paypal-button-container');

                                        </script>
                                        <script>
                                            async  function get_info_Payment() {

                                                const url = "http://localhost:8080/ECommerence/rest/Payment_invoice/103/168323";
                                                const response = await fetch(url);
                                                var data = await response.json();
                                                const object = JSON.parse(JSON.stringify(data));
                                                console.log(object)
                                                return object  ;
                                            }
                                        </script>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
script>


<style type="text/css">
    body{
        margin-top:20px;
        color: #484b51;
    }
    .text-secondary-d1 {
        color: #728299!important;
    }
    .page-header {
        margin: 0 0 1rem;
        padding-bottom: 1rem;
        padding-top: .5rem;
        border-bottom: 1px dotted #e2e2e2;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-pack: justify;
        justify-content: space-between;
        -ms-flex-align: center;
        align-items: center;
    }
    .page-title {
        padding: 0;
        margin: 0;
        font-size: 1.75rem;
        font-weight: 300;
    }
    .brc-default-l1 {
        border-color: #5c636a!important;
    }

    .ml-n1, .mx-n1 {
        margin-left: -.25rem!important;
    }
    .mr-n1, .mx-n1 {
        margin-right: -.25rem!important;
    }
    .mb-4, .my-4 {
        margin-bottom: 1.5rem!important;
    }

    hr {
        margin-top: 1rem;
        margin-bottom: 1rem;
        border: 0;
        border-top: 1px solid rgba(0,0,0,.1);
    }

    .text-grey-m2 {
        color: #888a8d!important;
    }

    .text-success-m2 {
        color: #86bd68!important;
    }

    .font-bolder, .text-600 {
        font-weight: 600!important;
    }

    .text-110 {
        font-size: 110%!important;
    }
    .text-blue {
        color: #5c636a!important;
    }
    .pb-25, .py-25 {
        padding-bottom: .75rem!important;
    }

    .pt-25, .py-25 {
        padding-top: .75rem!important;
    }
    .bgc-default-tp1 {
        background-color: rgba(75, 73, 75, 0.8)!important;
    }
    .bgc-default-l4, .bgc-h-default-l4:hover {
        background-color: #f3f8fa!important;
    }
    .page-header .page-tools {
        -ms-flex-item-align: end;
        align-self: flex-end;
    }

    .btn-light {
        color: #757984;
        background-color: #f5f6f9;
        border-color: #dddfe4;
    }
    .w-2 {
        width: 1rem;
    }

    .text-120 {
        font-size: 120%!important;
    }
    .text-primary-m1 {
        color: #4e555b!important;
    }

    .text-danger-m1 {
        color: #dd4949!important;
    }
    .text-blue-m2 {
        color: #4e555b!important;
    }
    .text-150 {
        font-size: 150%!important;
    }
    .text-60 {
        font-size: 60%!important;
    }
    .text-grey-m1 {
        color: #7b7d81!important;
    }
    .align-bottom {
        vertical-align: bottom!important;
    }

</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

</script>
<script type="text/javascript">
    $(document).ready( function () {
        get_info_Invoice() ;

    })  ;
</script>
<script>
   async  function  get_info_Invoice() {
       var prsent_location = window.location.href;

       const url = "http://localhost:8080/ECommerence/rest/invoice_service/168323_103";
       const response = await fetch(url);
       var data = await response.json();
       const object = JSON.parse(JSON.stringify(data));

       $("#name").append('<span class="text-600 text-110 text-blue align-middle">' + object.receipt_name + '</span>')
       var arrdress = object.shipping_address.split(/-/);
       $("#Street_country").append('<span class="text-600 text-110 text-blue align-middle">' + arrdress[2] + ',' + arrdress[1] + '</span>')
       $("#Sate_Country").append('<span class="text-600 text-110 text-blue align-middle">' + arrdress[0] + ',' + 'VietNam' + '</span>')

       $("#phone").append('<span class="text-600">' + object.receipt_phone + '</span>')
       $("#order_id_1").append('<span class="text-600 text-90">ID:</span>' + object.order_id);
       $("#date_time").append('<span class="text-600 text-90">Issue Date:</span> ' + object.order_date)
       $("#invoice_id").append(' <i class="fa fa-angle-double-right text-80"></i>' +'#' + object.order_id ) ;

       var title;
       var amount;
       var unit_price;
       var Price;

       const object2 = JSON.parse(JSON.stringify(object.order_detail_list));

       for (var i in object2) {

           var div = document.createElement('div') ;

           console.log(i)

           price_unit = object2[i].subtotal / object2[i].quantity ;

           div.innerHTML = ' <div class="row mb-2 mb-sm-0 py-25"><div class="col-4 col-sm-4">'+object2[i]._Products_.title +"-"+object2[i]._Products_.book_id +'</div> <div class="d-none d-sm-block col-2">'+object2[i].quantity+'</div>  <div class="d-none d-sm-block col-2 text-95">'+'$'+ price_unit+'</div> <div class="col-2 text-secondary-d2">'+'$'+object2[i].subtotal+'</div>                         </div> </div>';

          $("#table_product").append(div)

       }
       $("#total").append('<span class="text-150 text-success-d3 opacity-2">'+'$'+ object.total+'</span>')

   }

</script>
<script>
        function Pay_ment() {
            console.log("check")
            $.ajax({
                success : function (data) {
                    console.log(data)
                }
            })
        }
</script>

</body>
</html>
