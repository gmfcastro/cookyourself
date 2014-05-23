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
              <li class="active"><a href="#">Ingredientes</a></li>
              <li><a href="#">Crawler</a></li>
              <li><a href="#">Relatórios</a></li>
            </ul>
          
        </div><!--/span-->
        
        <div class="col-sm-9 col-md-10 main">
          
          <!--toggle sidebar button-->
          <p class="visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
          </p>
          <c:if test="${success != null}">
            <div class="alert alert-success alert-dismissable">
              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
              <strong>Salvo!</strong> ${success}
            </div>
          </c:if>
          <c:if test="${error != null}">
            <div class="alert alert-danger alert-dismissable">
              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
              <strong>Erro!</strong> ${error}
            </div>
          </c:if>
          <c:if test="${warning != null}">
            <div class="alert alert-warning alert-dismissable">
              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
              <strong>Alerta!</strong> ${warning}
            </div>
          </c:if>
          
          <h1 class="page-header">
            Ingredientes
            <a href="<c:url value="/admin/ingredients/add"/>" class="btn btn-primary pull-right">Novo Ingrediente</a>
          </h1>
          
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Nome</th>
                  <th>Descrição</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${ingredients}" var="ingredient">
                <tr>
                  <td>${ingredient.id}</td>
                  <td>${ingredient.name}</td>
                  <td>${ingredient.description}</td>
                  <td><a href="<c:url value="/admin/ingredients/edit/${ingredient.id}"/>" class="btn btn-warning">Editar</a></td>
                  <td><a href="<c:url value="/admin/ingredients/delete/${ingredient.id}"/>" class="btn btn-danger">Remover</a></td>
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
