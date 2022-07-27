<<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <%--<script type="text/javascript" src="Scripts/bootstrap.min.js"></script>--%>
  <%--<script type="text/javascript" src="Scripts/jquery-2.1.1.min.js"></script>--%>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <style>
    body {
      color: #999;
      background: #f5f5f5;
      font-family: 'Varela Round', sans-serif;
    }
    .form-control {
      box-shadow: none;
      border-color: #ddd;
    }
    .form-control:focus {
      border-color: #4aba70;
    }
    .login-form {
      width: 350px;
      margin: 0 auto;
      padding: 30px 0;
    }
    .login-form form {
      color: #434343;
      border-radius: 1px;
      margin-bottom: 15px;
      background: #fff;
      border: 1px solid #f3f3f3;
      box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
      padding: 30px;
    }
    .login-form h4 {
      text-align: center;
      font-size: 22px;
      margin-bottom: 20px;
    }
    .login-form .avatar {
      color: #fff;
      margin: 0 auto 30px;
      text-align: center;
      width: 100px;
      height: 100px;
      border-radius: 50%;
      z-index: 9;
      background: #b4b4b4;
      padding: 15px;
      box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
    }
    .login-form .avatar i {
      font-size: 62px;
    }
    .login-form .form-group {
      margin-bottom: 20px;
    }
    .login-form .form-control, .login-form .btn {
      min-height: 40px;
      border-radius: 2px;
      transition: all 0.5s;
    }
    .login-form .close {
      position: absolute;
      top: 15px;
      right: 15px;
    }
    .login-form .btn, .login-form .btn:active {
      background: #4aba70 !important;
      border: none;
      line-height: normal;
    }
    .login-form .btn:hover, .login-form .btn:focus {
      background: #b4b4b4 !important;
    }
    .login-form .checkbox-inline {
      float: left;
    }
    .login-form input[type="checkbox"] {
      position: relative;
      top: 2px;
    }
    .login-form .forgot-link {
      float: right;
    }
    .login-form .small {
      font-size: 13px;
    }
    .login-form a {
      color: #4aba70;
    }

  </style>

</head>
<body>
<div class="login-form">

  <form action="login" method="POST">

    <h4 class= "fa fa-lock" style="font-size:32px" modal-title>Confirm your account and email </h4>

    <div class="form-group">
      <input id = "user_name" type="text" class="form-control" placeholder="Username" required="required" name="user_name" value= "${user}">
    </div>

    <div class="form-group">

      <input id="email" type="email" class="form-control" placeholder="email" required="required" name="email" value="${email}">

    </div>

    <a type="submit" href="verify"   onclick="confirm_email()" class="btn btn-primary btn-block btn-lg"> Confirm </a>

    <a class="  btn-block " href="login" role="button">Cancel</a>

  </form>

</div>
<script>
  function confirm_email() {
    var email =  document.getElementById("email").value ;
    var user_name =  document.getElementById("user_name").value ;
    $.ajax({
      type : "post"  ,
      data  : {
        Email : email ,
        User_name : user_name
      } ,
      dataType : "json" ,
      url : "/ECommerence/confirm" ,

      success : function (data) {

        if(data == true) {
        }

      }

    }) ;

  }
</script>
</body>
</html>