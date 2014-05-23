<%-- 
    Document   : welcome
    Created on : Apr 28, 2014, 11:11:41 AM
    Author     : guilherme
--%>

<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <title>Admin | Cookyourself</title>
        <meta name="generator" content="Bootply" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.css' />"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/adminStyle.css' />"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery.steps.css' />"/>
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
              <li class="active"><a href="<c:url value="/admin/recipe" />">Receitas</a></li>
              <li><a href="<c:url value="/admin/recipe" />">Crawler</a></li>
              <li><a href="#">Relatórios</a></li>
            </ul>
          
        </div><!--/span-->
        
        <div class="col-sm-9 col-md-10 main">
          
          <!--toggle sidebar button-->
          <p class="visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
          </p>
          
          <h1 class="page-header">
            Nova Receita
          </h1>
          
          <form role="form" id="form" method="post" action="<c:url value="/admin/recipe/save"/>">
              <input type="text" name="recipe.id" value="${recipe.id}" hidden>
              <input type="text" id="listSize" value="${fn:length(recipe.ingredients)}" hidden>
              <h1>Dados básicos da receita</h1>
              <div>
                <div class="form-group">
                  <label for="inputName">Nome</label>
                  <input type="text" class="form-control" name="recipe.title" value="${recipe.title}" id="inputName" placeholder="Nome da receita">
                </div>
                <div class="form-group">
                  <label for="inputRendimento">Rendimento</label>
                  <input type="text" class="form-control" name="recipe.yield" value="${recipe.yield}" id="inputRendimento" placeholder="Quantas porções rende">
                </div>
                <div class="form-group">
                  <label for="inputDuracao">Duração</label>
                  <input type="text" class="form-control" name="recipe.duration" value="${recipe.duration}" id="inputDuracao" placeholder="Tempo que leva">
                </div>
                <div class="form-group">
                  <label for="inputAutor">Autor</label>
                  <input type="text" class="form-control" name="recipe.author" value="${recipe.author}" id="inputAutor" placeholder="Autor da receita">
                </div>
              </div>
              <h1>Ingredientes</h1>
              <div>
                  <div id="stepIngredients">
                      <div class="form-group">
                        <label for="txtAreaIngs">Ingredientes:</label>
                        <textarea class="form-control" name="recipe.ingredients" id="txtAreaIngs" rows="3">${recipe.ingredients}</textarea>
                      </div>
                  </div>
              </div>
              
              <h1>Modo de preparo</h1>
              <div>
              
                  <div class="form-group">
                    <label for="txtAreaHowTo">Modo de preparo</label>
                    <textarea class="form-control" name="recipe.howToCook" id="txtAreaHowTo" rows="3">${recipe.howToCook}</textarea>
                  </div>
              </div>
              
          </form>

      </div><!--/row-->
</div>
</div><!--/.container-->

<footer>
  <p class="pull-right">©2014 Cookyourself</p>
</footer>
        
        
        <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        
        <script type='text/javascript' src="<c:url value='/js/jquery.steps.js' />"></script>

        <script type='text/javascript' src="<c:url value='/js/bootstrap.min.js' />"></script>
        
        <script>
            var ingCounter=$("#listSize").val();
            $(function ()
            {
                $("#form").steps({
                    onFinished: function(event, currentIndex){
                        var form = $(this);

                        form.submit();
                    }
                });
            });
            
            $(document).ready(function(){
                $("#maisIng").click(function(){
                    ingCounter ++;
                    $("#stepIngredients").append('<div class="row" id="ingredientComp"><div class="col-md-5"><select class="form-control" id="selectIng0" name="recipe.ingredients['+ingCounter+'].ingredient.id"><c:forEach items="${ingredients}" var="ingredient"><option value="${ingredient.id}">${ingredient.name}</option></c:forEach></select></div><div class="col-md-5"><div class="form-group"><input type="text" class="form-control" name="recipe.ingredients['+ingCounter+'].qty" id="inputIng0" placeholder="Quantidade"/></div></div></div>');
                
                });
            });
        </script>

        
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
