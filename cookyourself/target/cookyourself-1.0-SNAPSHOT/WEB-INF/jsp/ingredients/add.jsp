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
        <title>Admin | Cookyourself</title>
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
                <li><a href="<c:url value="/admin/welcome" />">Home</a></li>
              <li><a href="<c:url value="/admin/recipe" />">Receitas</a></li>
              <li class="active"><a href="<c:url value="/admin/ingredients" />">Ingredientes</a></li>
              <li><a href="#">Crawler</a></li>
              <li><a href="#">Relatórios</a></li>
            </ul>
          
        </div><!--/span-->
        
        <div class="col-sm-9 col-md-10 main">
          
          <!--toggle sidebar button-->
          <p class="visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
          </p>
          
          <h1 class="page-header">
            Novo Ingrediente
          </h1>
          
          <form role="form" method="post" action="<c:url value="/admin/ingredients/save"/>">
              
          <div class="form-group">
            <label for="inputName">Nome</label>
            <input type="text" class="form-control" name="ingredient.name" id="inputName" placeholder="Nome do ingrediente">
          </div>
              
          <div class="form-group">
            <label for="txtAreaDesc">Descrição</label>
            <textarea class="form-control" name="ingredient.description" id="txtAreaDesc" rows="3"></textarea>
          </div>
          
          <button type="submit" class="btn btn-success">Salvar</button>
          
          </form>

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
