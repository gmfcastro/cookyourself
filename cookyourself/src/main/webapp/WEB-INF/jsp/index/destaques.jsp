<%-- 
    Document   : destaques
    Created on : May 14, 2014, 2:15:00 PM
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
    <script>
        function myFunction(number){
            $("#comp"+number).remove();
            $("#inputIng"+number).remove();
            alert(number);
        }
        
        function logout(){
            $.post("http://localhost:8080/cookyourself/logout",function(data,status){
                location.reload();
            }); 
        }
        
        function like(id){
            var text = $("#"+id).text();
            var res = text.split(" ");
            var likes = parseInt(res[0]);
            var url = "http://localhost:8080/cookyourself/like/recipe/"+id;
            $.post(url,function(data,status){
                likes ++;
                $("#"+id).text(likes+" | Gostei");
            });
        }
        
        window.fbAsyncInit = function() {
        FB.init({
          appId      : '638862416204874', // App ID
          channelUrl : 'http://localhost:8080/cookyourself',
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
                            $.post("http://localhost:8080/cookyourself/doFBLogin",userJson,function(data,status){
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
                            $.post("http://localhost:8080/cookyourself/doFBLogin",userJson,function(data,status){
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
                        <li><a href="<c:url value="/"/>">Home</a></li>
                        <li class="active"><a href="<c:url value="/destaques" />">Destaques</a></li>
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
               
            <div class="container" id="searchResult">
                <div class="row">
                    <c:forEach items="${recipes}" var="recipe">
                        <div class="col-md-4 boxRecipe">   
                            <div class="panel panel-warning" style="margin-top: 20px;">
                               <a href="<c:url value="/view/${recipe.id}"/>"><div class="panel-heading">
                                   <img src="${recipe.photoURL}" alt="${recipe.title}" style="width: 100%;height: 230px;"/>
                               </div></a>
                               <div class="panel-body" style="color:#101010; ">
                                 ${recipe.title}
                                 <c:if test="${session != null}">
                                     <button class="btn btn-danger pull-right" id="${recipe.id}" onclick="like(${recipe.id});">${recipe.likes} | Gostei</button>
                                 </c:if>
                               </div>
                             </div>
                            </div>
                    </c:forEach>
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
  </body>
</html>
