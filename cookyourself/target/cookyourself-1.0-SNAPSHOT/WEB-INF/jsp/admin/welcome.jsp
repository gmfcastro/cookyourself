<%-- 
    Document   : welcome
    Created on : Apr 28, 2014, 11:11:41 AM
    Author     : guilherme
--%>

<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <title>Bootply.com - Dashboard with Off-canvas Sidebar</title>
        <meta name="generator" content="Bootply" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.css' />"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/adminStyle.css' />"/>
    </head>
    
    <!-- HTML code from Bootply.com editor -->
    
    <body  >
        
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Admin Cookyourself</a>
        </div>
      </div>
</nav>

<div class="container-fluid">
      
      <div class="row row-offcanvas row-offcanvas-left">
        
         <div class="col-sm-3 col-md-2 sidebar-offcanvas" id="sidebar" role="navigation">
           
            <ul class="nav nav-sidebar">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="<c:url value="/admin/recipe" />">Receitas</a></li>
              <li><a href="<c:url value="/admin/crawler" />">Crawler</a></li>
              <li><a href="#">Relatórios</a></li>
            </ul>
          
        </div><!--/span-->
        
        <div class="col-sm-9 col-md-10 main">
          
          <!--toggle sidebar button-->
          <p class="visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
          </p>
          
		  <h1 class="page-header">
            Dashboard
          </h1>

          <div class="row placeholders">
            <a href="<c:url value="/admin/recipe" />"><div class="col-xs-6 col-sm-4 placeholder text-center">
              <img src="//placehold.it/200/6666ff/fff" class="center-block img-responsive img-circle" alt="Generic placeholder thumbnail">
              <h4>Receitas</h4>
            </div></a>
            <a href="<c:url value="/admin/crawler" />" />"><div class="col-xs-6 col-sm-4 placeholder text-center">
              <img src="//placehold.it/200/6666ff/fff" class="center-block img-responsive img-circle" alt="Generic placeholder thumbnail">
              <h4>Crawler</h4>
            </div></a>
            <a href="<c:url value="#" />"><div class="col-xs-6 col-sm-4 placeholder text-center">
              <img src="//placehold.it/200/66ff66/fff" class="center-block img-responsive img-circle" alt="Generic placeholder thumbnail">
              <h4>Relatórios</h4>
            </div></a>
          </div>
          
          <hr>

          <h2 class="sub-header">Receitas</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Nome</th>
                  <th>Rendimento</th>
                  <th>Duração</th>
                  <th>Autor</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${recipes}" var="recipe">
                <tr>
                  <td>${recipe.id}</td>
                  <td>${recipe.title}</td>
                  <td>${recipe.yield}</td>
                  <td>${recipe.duration}</td>
                  <td>${recipe.author}</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>

      </div><!--/row-->
	</div>
</div><!--/.container-->

<footer>
  <p class="pull-right">©2014 Cookyourself</p>
</footer>
        
        
        <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


        <script type='text/javascript' src="<c:url value='/js/bootstrap.min.js' />"></script>





        
        <!-- JavaScript jQuery code from Bootply.com editor -->
        
        <script type='text/javascript'>
        
            $(document).ready(function() {
                $('[data-toggle=offcanvas]').click(function() {
                  $('.row-offcanvas').toggleClass('active');
                });
              });
        
        </script>
        
    </body>
</html>
