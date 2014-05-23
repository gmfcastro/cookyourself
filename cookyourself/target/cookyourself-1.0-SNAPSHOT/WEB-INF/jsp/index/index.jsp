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

    <title>Cookyourself</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.css' />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />"/>
    <script src="http://connect.facebook.net/en_US/all.js"></script>
    <script>
        function myFunction(number){
            $("#comp"+number).remove();
            $("#inputIng"+number).remove();
            alert(number);
        }
        
        function surpreenda(){
            $.post("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/surpreenda",function(data,status){
            });    
        }
        
        function logout(){
            $.post("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/logout",function(data,status){
                location.reload();
            }); 
        }
        
        window.fbAsyncInit = function() {
        FB.init({
          appId      : '638862416204874', // App ID
          channelUrl : 'http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself',
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
                        <li class="active"><a href="#">Home</a></li>
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
        
        <!---------NAVBAR------->
        <hr>
        <nav class="navbar navbar-inverse navbar-fixed-top" id="navIngredients" role="navigation">
                <div class="container-fluid" id="ingNav">
                  <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                      <span class="sr-only">Toggle navigation</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Ingredientes:</a>
                  </div>
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                       <ul class="nav navbar-nav" id="listIngredients">
                       </ul>
                  </div>
                </div>
          </nav>
      
        <div class="container-full" id="indexBg">

              <div class="row">

                <div class="col-lg-12 text-center v-center">
                  <br><br><br><br><br><br>
                  
                  <img src="<c:url value='/images/cyslogo.png'/>" alt="Cookyourself"/>

                  <br><br>

                  <form class="col-lg-12 form-inline" role="form" method="post" action="<c:url value="/search"/>">
                    <div class="input-group" style="width:600px;text-align:center;margin:0 auto;" id="formIngredients">
                    <input class="form-control input-lg" id="inputIgredients" placeholder="Procure por ingredientes" type="text">
                      <span class="input-group-btn"><button class="btn btn-lg btn-danger" id="addIng" type="button">+</button></span>
                    </div>
                    <br><br>
                    <div class="row">
                        <c:if test="${session!=null}">
                        <div class="col-sm-5 col-sm-offset-1">
                            <button class="btn btn-lg btn-danger pull-right" style="margin-right: 50px" type="submit"><span class="glyphicon glyphicon-search"></span> Pesquisar</button>
                        </div>
                  </form>
                        <div class="col-sm-1">
                            <form class="col-lg-12 form-inline" role="form" method="post" action="<c:url value="/surpreenda"/>">
                                <button class="btn btn-lg btn-danger" type="submit"><span class="glyphicon glyphicon-star"></span> Surpreenda-me !</button>
                            </form>
                        </div>
                        </c:if>
                        <c:if test="${session==null}">
                           <button class="btn btn-lg btn-danger" type="submit"><span class="glyphicon glyphicon-search"></span> Pesquisar</button> 
                           </form>
                        </c:if>
                    </div> 
                </div>

              </div> <!-- /row -->

                  <div class="row">

                <div class="col-lg-12 text-center v-center" style="font-size:39pt;">
                </div>

              </div>

                <br><br><br><br><br>

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
    $(document).ready(function (){
        var counter = 0;
        
        $("#addIng").click(function (){
           var ingredient = $("#inputIgredients").val();
           counter++;
           $("#listIngredients").append('<li id="comp'+counter+'"><div class="ingredientComp ingredientComp-list alert-dismissable"><button type="button" class="close pull-right" id="ing'+counter+'" onclick="myFunction('+counter+')" aria-hidden="true">&times;</button>'+ingredient+'</div></li>');
           $("#formIngredients").append('<input type="text" name="ingredients['+counter+']" id="inputIng'+counter+'" value='+ingredient+' hidden>');
           $("#inputIgredients").val("");
        });
     });
    </script>
  </body>
</html>
