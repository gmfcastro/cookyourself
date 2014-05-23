<%-- 
    Document   : index
    Created on : Apr 24, 2014, 3:38:39 PM
    Author     : guilherme
--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Admin | Cookyourself</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.css' />"/>

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
            .modal-footer {   border-top: 0px; }
    </style>
  </head>

  <body>

    <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h1 class="text-center">Admin Cookyourself</h1>
          </div>
          <div class="modal-body">
              <form class="form col-md-12 center-block" method="POST" action="<c:url value='/dologin' />">
                <div class="form-group">
                  <input type="email" class="form-control input-lg" placeholder="Email" name='email' />
                </div>
                <div class="form-group">
                  <input type="password" class="form-control input-lg" placeholder="Password" name='password' />
                </div>
                <div class="form-group">
                  <button class="btn btn-primary btn-lg btn-block">Entrar</button>
                </div>
              </form>
          </div>
          <div class="modal-footer">
              <div class="col-md-12">
                      </div>	
          </div>
      </div>
      </div>
    </div>


    <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type='text/javascript' src="<c:url value='/js/bootstrap.min.js' />"></script>
  </body>
</html>
