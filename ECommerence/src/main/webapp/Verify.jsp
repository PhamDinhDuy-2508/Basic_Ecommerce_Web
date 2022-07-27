<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 6/6/2022
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Bootstrap Elegant Account Login Form with Avatar Icon</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merienda+One">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
        .card {
            width: 350px;
            padding: 10px;
            border-radius: 20px;
            background: #fff;
            border: none;
            height: 350px;
            position: relative;
        }

        .container {
            height: 100vh;
        }

        body {
            background: #eee;
        }

        .mobile-text {
            color: #989696b8;
            font-size: 15px;
        }

        .form-control {
            margin-right: 12px;
        }

        .form-control:focus {
            color: #495057;
            background-color: #fff;
            border-color: #ff8880;
            outline: 0;
            box-shadow: none;
        }
        .cursor {
            cursor: pointer;
        }
    </style>
<div class="d-flex justify-content-center align-items-center container">
    <div class="card py-5 px-3">
        <h5 class="m-0">verification</h5><span class="mobile-text">Enter the code we just send on your Email <b class="text-danger"></b></span>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="verify code" required="required" name="verify_code" id = "verify_code" >
        </div>

        <a type="submit"  onclick="confrim_verify_number()" class="btn btn-primary btn-block btn-md"> Confirm </a>
        <a type="submit" href="confirm"   class="btn btn-primary btn-block btn-md"> Cancel </a>
        <a class="text-center mt-5"><span class="font-weight-bold text-danger cursor">Resend</span></a>
    </div>
    </div>
</head>
<script>
    function confrim_verify_number()  {

        var verify_number =  document.getElementById("verify_code").value ;
        console.log(verify_number)

        $.ajax({
            type : "post" ,
            data : {
                verify_code : verify_number
            }      ,
            dataType:'json' ,
            url : "/ECommerence/verify" ,
            success : function (data)  {
                if(data == true) {
                    location.href="home"
                    console.log("check")
                }
            } ,
        })  ;


    }
</script>
</html>
