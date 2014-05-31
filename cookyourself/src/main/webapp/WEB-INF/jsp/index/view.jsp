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

    <title>Cookyourself</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.css' />"/>

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />"/>
    <script>
        
        function editYield(){
            $("#yiedlDiv").hide();
            $("#yieldForm").show();
        }
        
        function editDuration(){
            $("#durationDiv").hide();
            $("#durationForm").show();
        }
        
        function editPrice(){
           $("#priceDiv").hide();
           $("#priceForm").show();
        }
        
        function myFunction(number){
            $("#comp"+number).remove();
            $("#inputIng"+number).remove();
            alert(number);
        }
        
        function editIngredients(){
            $("#ingredientsDiv").hide();
            $("#ingredientsForm").show();
        }
        
        function editHowTo(){
            $("#howToDiv").hide();
            $("#howToForm").show();
        }
        
        function logout(){
            $.post("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/logout",function(data,status){
            //$.post("http://localhost:8080/cookyourself/logout",function(data,status){
                location.reload();
            }); 
        }
        
        window.fbAsyncInit = function() {
        FB.init({
          appId      : '638862416204874', // App ID
          channelUrl : 'http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself',
          //channelUrl : 'http://localhost:8080/cookyourself',
          status     : true, // check login status
          cookie     : true, // enable cookies to allow the server to access the session
          xfbml      : true  // parse XFBML
        });
        };

      (function(d){
         var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
         if (d.getElementById(id)) {return;}
         js = d.createElement('script'); js.id = id; js.async = true;
         js.src = "//connect.facebook.net/en_US/all.js";
         ref.parentNode.insertBefore(js, ref);
       }(document));
        
        function loginFacebook() {
            FB.login(function(response) {
               alert(JSON.stringify(response));
               if (response.authResponse) {
                        FB.api('/me', function(response) {
                            userJson = {'user.name':response.name, 'user.password':response.id, 'user.email':response.email};
                            $.post("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/doFBLogin",userJson,function(data,status){
                            //$.post("http://localhost:8080/cookyourself/doFBLogin",userJson,function(data,status){
                                location.reload();
                            }); 
                         });
               }
            }, {scope: 'email' });
        }
      
        function statusFacebook() {
            FB.getLoginStatus(function(response) {
               if (response.status === 'connected') {
                        FB.api('/me', function(response) {
                            userJson = {'user.name':response.name, 'user.password':response.id, 'user.email':response.email};
                            $.post("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/doFBLogin",userJson,function(data,status){
                            //$.post("http://localhost:8080/cookyourself/doFBLogin",userJson,function(data,status){
                                location.reload();
                            }); 
                         });               
               } else if (response.status === 'not_authorized') {
                   loginFacebook(); // nao autorizado, solicitar login
               } else {
                   loginFacebook(); // nao autorizado, solicitar login
             }});
        }
        
    </script>    
  </head>

  <body>
        <!---------NAVBAR------->
        
         <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="container-fluid">
                  <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                      <span class="sr-only">Toggle navigation</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                  </div>
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                       <ul class="nav navbar-nav">
                           <li class="active"><a href="<c:url value="/"/>">Home</a></li>
                           <li><a href="<c:url value="/destaques" />">Destaques</a></li>
                       </ul>
                       <c:if test="${session == null}">
                       <ul class="nav navbar-nav navbar-right">
                           <li>
                               <button class="btn btn-danger" onclick="statusFacebook();" style="margin-top: 10px; margin-right: 20px;">Facebook Login</button>
                           </li>
                       </ul>
                       </c:if>
                       <c:if test="${session != null}">
                          <ul class="nav navbar-nav navbar-right">
                           <li>
                               <label style="margin-top: 15px;margin-right: 10px;color: #ffffff;">Olá, ${session.name}</label>
                               <button class="btn btn-danger" onclick="logout();" style="margin-top: 0px; margin-right: 20px;">Logout</button>
                           </li>
                          </ul> 
                       </c:if>
                  </div>
                </div>
          </nav>
        
      
        <div class="container-full" id="indexBg">
               
        <div class="container-fluid">
      
            <div class="row row-offcanvas row-offcanvas-left">
        
                <div class="col-sm-3 col-md-3" id="sidebar" style="overflow-y:scroll;">

                    <img src="${recipe.photoURL}" alt="${recipe.title}" class="img-rounded" style="width: 100%;margin-top: 20px;"><br>
                    <h3 style="text-align: center;">${recipe.title}</h3>
                    <hr>
                    <h4 style="text-align: center;">Atributos</h4>
                    <hr>
                    <br>
                    <p style="text-align: center;"><h4 style="text-align: center;">Rendimento:</h4>
                        <div id="yiedlDiv">
                            <c:if test="${recipe.yield == 0}">
                                <button class="btn btn-danger btn-sm center-block" onclick="editYield()">Quanto rendeu?</button>
                            </c:if>
                            <c:if test="${recipe.yield != 0}">
                                <p style="text-align: center; margin-bottom: 3px;">${recipe.yield}</p>
                                <button class="btn btn-danger btn-xs center-block" onclick="editYield()">Quanto rendeu?</button>
                            </c:if>
                        </div>
                        <div id="yieldForm" style="text-align:center;margin:0 auto;" hidden>
                            <form class="form-inline" role="form" method="post" action="<c:url value="/recipe/update/yield"/>">
                                <input type="text" name="recipe.id" value="${recipe.id}" hidden />
                                <div class="form-group">
                                    <label class="sr-only" for="inputYield">Yield</label>
                                    <input type="number" class="form-control input-sm" name="recipe.yield" id="inptYield" placeholder="Rende" size="2"/>
                                </div>
                                <button type="submit" class="btn btn-default btn-sm">Salvar</button>
                            </form>
                        </div>
                    </p>
                    <br>
                    <p style="text-align: center"><h4 style="text-align: center;">Tempo de Preparo:</h4>
                        <div id="durationDiv">
                            <c:if test="${recipe.duration == null}">
                                <button class="btn btn-danger btn-sm center-block" onclick="editDuration()">Quanto durou?</button>
                            </c:if>
                            <c:if test="${recipe.duration != null}">
                                <p style="text-align: center; margin-bottom: 3px;">${recipe.duration}</p><!--- COLOCAR 30 min, 1 hora, 1:30, 2:00, Mais de 2:00------>
                                <button class="btn btn-danger btn-xs center-block" onclick="editDuration()">Quanto durou?</button>
                            </c:if>
                        </div>
                        <div id="durationForm" style="text-align:center;margin:0 auto;" hidden>
                            <form class="form-inline" role="form" method="post" action="<c:url value="/recipe/update/duration"/>">
                                <input type="text" name="recipe.id" value="${recipe.id}" hidden />
                                <div class="radio">
                                   <label>
                                    <input type="radio" name="recipe.duration" value="00:30">
                                    00:30
                                  </label>
                                </div>
                                <div class="radio">
                                   <label>
                                    <input type="radio" name="recipe.duration" value="01:00">
                                    01:00
                                  </label>
                                </div>
                                <div class="radio">
                                   <label>
                                    <input type="radio" name="recipe.duration" value="01:30">
                                    01:30
                                  </label>
                                </div>
                                <div class="radio">
                                   <label>
                                    <input type="radio" name="recipe.duration" value="02:00">
                                    02:00
                                  </label>
                                </div>
                                <div class="radio">
                                   <label>
                                    <input type="radio" name="recipe.duration" value="Mais que 02:00">
                                    Mais que 02:00
                                  </label>
                                </div>
                                <button type="submit" class="btn btn-default btn-sm">Salvar</button>
                            </form>
                        </div>
                   </p>
                   <br>
                    <p style="text-align: center"><h4 style="text-align: center;">Preço(R$)</h4>
                        <div id="priceDiv">
                            <c:if test="${recipe.price == null}">
                                <button class="btn btn-danger btn-sm center-block" onclick="editPrice();">Quanto custou?</button>
                            </c:if>
                            <c:if test="${recipe.price != null}">
                                <p style="text-align: center; margin-bottom: 3px;">${recipe.price}</p> <!--- PRECO AQUI !! ---->
                                <button class="btn btn-danger btn-xs center-block" onclick="editPrice();">Quanto custou?</button>
                            </c:if>   
                        </div>
                        <div id="priceForm" style="text-align:center;margin:0 auto;" hidden>
                            <form class="form-inline" role="form" method="post" action="<c:url value="/recipe/update/price"/>">
                                <input type="text" name="recipe.id" value="${recipe.id}" hidden />
                                <div class="form-group">
                                    <label class="sr-only" for="inputPrice">Price</label>
                                    <input type="number" class="form-control input-sm" name="recipe.price" id="inputPrice" placeholder="Preço" min="0" max="9999" step="0.01" size="4">
                                </div>
                                <button type="submit" class="btn btn-default btn-sm">Salvar</button>
                            </form>
                        </div>
                    </p>
                    <br>
                    <h4 style="text-align: center;">Autor:</h4><p style="text-align: center">${recipe.author}</p>
                </div><!--/span-->
                <div class="col-sm-3 col-md-8" id="ingredients" style="overflow-y:scroll;">
                    <div id="ingredientsDiv">
                        <div class="row">
                            <div class="col-md-8">
                                <h2>Ingredientes:</h2>
                            </div>
                            <div class="col-md-4 pull-right">
                                <button class="btn btn-danger pull-right" style="margin-top:20px;" onclick="editIngredients();">Editar</button>
                            </div>
                        </div>
                        <hr>
                        ${recipe.ingredients}
                    </div>
                    <div id="ingredientsForm" hidden>
                        <form method="post" action="<c:url value="/recipe/update/ingredients"/>" accept-charset="ISO-8859-1">
                            <div class="row">
                                <div class="col-md-8">
                                    <h2>Ingredientes:</h2>
                                </div>
                                <div class="col-md-4 pull-right">
                                    <button type="submit "class="btn btn-warning pull-right" style="margin-top:20px;">Salvar</button>
                                </div>
                             </div>
                             <hr>
                             <input type="text" name="recipe.id" value="${recipe.id}" hidden />
                             <textarea class="form-control" name="recipe.ingredients" rows="6">${recipe.ingredients}</textarea>         
                        </form>           
                    </div>

                </div><!--/span-->
                
                <div class="col-sm-3 col-md-8 col-md-offset-3" id="howToCook" style="overflow-y:scroll;">
                    <div id="howToDiv">
                        <div class="row">
                            <div class="col-md-8">
                                <h2 style="width:">Modo de preparo:</h2>
                            </div>
                            <div class="col-md-4 pull-right">
                                <button class="btn btn-danger pull-right" style="margin-top:20px;" onclick="editHowTo()">Editar</button><br>
                            </div>
                        </div>
                        <hr>
                        ${recipe.howToCook}
                    </div>
                    <div id="howToForm" hidden>
                        <form method="post" action="<c:url value="/recipe/update/howtocook"/>" accept-charset="ISO-8859-1">
                            <div class="row">
                                <div class="col-md-8">
                                    <h2>Modo de preparo:</h2>
                                </div>
                                <div class="col-md-4 pull-right">
                                    <button type="submit "class="btn btn-warning pull-right" style="margin-top:20px;">Salvar</button>
                                </div>
                             </div>
                             <hr>
                             <input type="text" name="recipe.id" value="${recipe.id}" hidden />
                             <textarea class="form-control" name="recipe.howToCook" rows="6">${recipe.howToCook}</textarea>         
                        </form>           
                    </div>
                </div>
                <div class="col-sm-3 col-md-8" id="comments" style="overflow-y:scroll;">
                    <div class="row">
                        <div class="col-md-8">
                            <h2>Comentários:</h2>
                        </div>
                    </div>
                    <c:if test="${session == null}">
                        <button class="btn btn-danger btn-lg center-block" style="margin-top: 10%" onclick="statusFacebook();">Facebook Login</button>
                    </c:if>
                    <c:if test="${session != null}">
                        <form method="post" class="form-inline" action="<c:url value="/recipe/comment"/>" accept-charset="ISO-8859-1">
                            <table class="table" style="width: 800px;table-layout: fixed; ">
                                <tbody>
                                    <c:forEach items="${comments}" var="comment">
                                       <tr style="width: 100px;">
                                           <td style="color: #e38d13">${comment.user.name}:</td>
                                           <td style="color: #ffffff;">${comment.comment}</td>
                                       </tr>
                                    </c:forEach>
                                </tbody>
                             </table>
                                     <hr>
                                     <input type="text" name="recipeId" value="${recipe.id}" hidden />
                                               <div class="form-group">
                                                    <label class="sr-only" for="commentInput">Comentário</label>
                                                    <input type="text" class="form-control" name="comment.comment" id="commentInput" placeholder="Comentário" style="width: 700px" required>
                                                </div>
                                    <button class="btn btn-danger" type="submit">Enviar</button></td>
                                
                            
                        </form>
                    </c:if>
                </div>
            </div>
         </div>

        </div> <!-- /container full -->

        <div class="container-fluid">

                <div class="row" style="height: 100px; background-color: rgba(39,39,39,100);">
                </div>

                <div class="row" style="background-color: rgba(39,39,39,100); color: #ffffff">
                    <div class="col-lg-12">
                    <br><br>
                      <p class="pull-right"> &nbsp; ©Copyright 2014 Cookyourself</p>
                    <br><br>
                    </div>
                </div>
        </div>


    <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type='text/javascript' src="<c:url value='/js/bootstrap.min.js' />"></script>
    <script>
        $('#howToCook').scrollspy();
    </script>
  </body>
</html>