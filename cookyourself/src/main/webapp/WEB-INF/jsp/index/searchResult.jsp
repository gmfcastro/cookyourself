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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <style>#slider{ margin: 10px; }</style>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />"/>
    <script>
        function myFunction(number){
            $("#comp"+number).remove();
            $("#inputIng"+number).remove();
            alert(number);
        }
        
        function logout(){
            $.post("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/logout",function(data,status){
                location.replace("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself");
            }); 
        }
        
        function like(id){
            var text = $("#like"+id).text();
            if(text === ""){
                var text = $("#des"+id).text();
            }
            var res = text.split(" ");
            var likes = parseInt(res[0]);
            //var url = "http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/like/recipe/"+id;
            var url = "http://localhost:8080/cookyourself/like/recipe/"+id;
            $.post(url,function(data,status){
                likes ++;
                $("#like"+id).attr("onclick","unlike("+id+");");
                $("#like"+id).text(likes+" | Descurtir");
                $("#des"+id).attr("onclick","unlike("+id+");");
                $("#des"+id).text(likes+" | Descurtir");
            });
        }
        
        function unlike(id){
            var text = $("#des"+id).text();
            if(text === ""){
                var text = $("like#"+id).text();
            }
            var res = text.split(" ");
            var likes = parseInt(res[0]);
            //var url = "http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/unlike/recipe/"+id;
            var url = "http://localhost:8080/cookyourself/unlike/recipe/"+id;
            $.post(url,function(data,status){
                likes --;
                $("#des"+id).attr("onclick","like("+id+");");
                $("#des"+id).text(likes+" | Curtir");
                $("#like"+id).attr("onclick","like("+id+");");
                $("#like"+id).text(likes+" | Curtir");
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
                                location.replace("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself");
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
                                location.replace("http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself");
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
            <div class="row">
                <div class="col-sm-3 col-md-3" id="sidebar" style="overflow-y:scroll; margin-left: 30px;height: 85%;position: fixed;text-align: center;">
                    <form role="form" method="post" id="filterForm" action="<c:url value="/search"/>">
                    <c:forEach items="${ingredients}" var="ingredient">
                        <input type="text" name="ingredients" value="${ingredient}" hidden>
                    </c:forEach>
                    <h2>Filtrar por:</h2>
                    <hr>
                    <h3>Rendimento maior que:</h3>
                    <br>
                    <div id="sliderYield" class="center-block" style="width: 80%"></div>
                    <br>
                    <div class="form-group">
                        <input class="form-control center-block" id="filterYield" name="yield" value="${yield}" style="width:50px;color: #000;text-align: center">
                    </div>
                    <h3>Tempo de preparo:</h3>
                    <div class="center-block" style="width: 120px;">
                    <div class="radio">
                    <label>
                        <c:if test="${duration == '00:30'}">
                            <input type="radio" name="duration" value="00:30" checked>
                         00:30
                        </c:if>
                        <c:if test="${duration != '00:30'}">
                        <input type="radio" name="duration" value="00:30">
                         00:30
                        </c:if>
                    </label>
                    </div>
                    <div class="radio">
                    <label>
                        <c:if test="${duration == '01:00'}">
                            <input type="radio" name="duration" value="01:00" checked>
                            01:00
                        </c:if>
                        <c:if test="${duration != '01:00'}">
                            <input type="radio" name="duration" value="01:00">
                            01:00
                        </c:if>
                    </label>
                    </div>
                    <div class="radio">
                    <label>
                        <c:if test="${duration == '01:30'}">
                            <input type="radio" name="duration" value="01:30" checked>
                            01:30
                         </c:if>
                         <c:if test="${duration != '01:30'}">
                            <input type="radio" name="duration" value="01:30">
                            01:30
                         </c:if>
                    </label>
                    </div>
                    <div class="radio">
                    <label>
                        <c:if test="${duration == '02:00'}">
                            <input type="radio" name="duration" value="02:00" checked>
                            02:00
                        </c:if>
                        <c:if test="${duration != '02:00'}">
                            <input type="radio" name="duration" value="02:00">
                            02:00  
                        </c:if>
                    </label>
                    </div>
                    <div class="radio">
                    <label>
                        <c:if test="${duration == 'Mais que 02:00'}">
                            <input type="radio" name="duration" value="Mais que 02:00" checked>
                            Mais que 02:00
                        </c:if>
                        <c:if test="${duration != 'Mais que 02:00'}">
                            <input type="radio" name="duration" value="Mais que 02:00">
                            Mais que 02:00
                        </c:if>
                    </label>
                    </div>
                    </div>
                    <h3>Precos menores que:</h3>
                    <br>
                    <div id="sliderPrice" class="center-block" style="width: 80%"></div><br>
                    <div class="form-group">
                        <input class="form-control center-block" id="filterPrice" name="price" value="${price}" style="width:50px;color: #000;text-align: center">
                    </div><br>
                    <button type="submit" class="btn btn-danger btn-lg center-block">Filtrar</button>
                    </form>
                </div>
            </div>
            
            <div class="container" id="searchResult">
                
                <div class="row"style="margin-left: 25%;">
                    <c:forEach items="${recipes}" var="recipe">
                        <div class="col-md-6 boxRecipe">   
                            <div class="panel panel-warning" style="margin-top: 10px;">
                               <form method="post" id="formViewRecipe${recipe.id}" action="<c:url value="/click/recipe"/>">
                               <input type="text" name="id" value="${recipe.id}" hidden>
                               <c:forEach items="${ingredients}" var="ingredient">
                                   <input type="text" name="ingredients" value="${ingredient}" hidden>
                               </c:forEach>
                                <div class="submitViewForm" id="${recipe.id}" style="cursor: pointer;">
                                 <div class="panel-heading">
                                     <img src="${recipe.photoURL}" alt="${recipe.title}" style="width: 100%;height: 230px;"/>
                                 </div>
                                </div>
                               </form>
                               
                               <div class="panel-body" style="color:#101010; ">
                                 ${recipe.title}
                                 
                                 <c:if test="${session != null}">
                                     <c:set var="curtido" value="${false}"/>
                                     <c:forEach items="${curtis}" var="like">
                                         <c:if test="${like.recipe.id == recipe.id }">
                                            <c:set var="curtido" value="${true}"/>
                                            <button class="btn btn-danger pull-right" id="des${recipe.id}" onclick="unlike(${recipe.id});">${recipe.likes} | Descurtir</button>
                                         </c:if>
                          
                                     </c:forEach> 
                                     <c:if test="${curtido == false}">
                                         <button class="btn btn-danger pull-right" id="like${recipe.id}"onclick="like(${recipe.id});">${recipe.likes} | Curtir</button>
                                     </c:if>
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
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script>
        $(document).ready(function(){
            
            var yield = $("#filterYield").val();
            var price = $("#filterPrice").val();
            
            if(yield !== null){
                
                $("#sliderYield").slider({
                    min:0,
                    max:10,
                    value: yield,
                    change: function(event,ui){
                        var valor = $( "#sliderYield" ).slider( "value" );
                        $("#filterYield").val(valor);
                        $("#filterForm").submit();
                    }
                });
                
            }else{
              $("#sliderYield").slider({
                    min:0,
                    max:10,
                    change: function(event,ui){
                        var valor = $( "#sliderYield" ).slider( "value" );
                        $("#filterYield").val(valor);
                        $("#filterForm").submit();
                    },
                    create: function(event,ui){
                        var valor = $( "#sliderYield" ).slider( "value" );
                        $("#filterYield").val(valor);
                    }
                });  
            }
            
            if(price !== null){
            
                $("#sliderPrice").slider({
                    min:20,
                    max:200,
                    step:10,
                    value:price,
                    change:function(event,ui){
                        var valor = $( "#sliderPrice" ).slider( "value" );
                        $("#filterPrice").val(valor);
                        $("#filterForm").submit();
                    }
                });
            }else{
                $("#sliderPrice").slider({
                    min:20,
                    max:200,
                    step:10,
                    value:200,
                    change:function(event,ui){
                        var valor = $( "#sliderPrice" ).slider( "value" );
                        $("#filterPrice").val(valor);
                        $("#filterForm").submit();
                    },
                    create: function(event,ui){
                        var valor = $( "#sliderPrice" ).slider( "value" );
                        $("#filterPrice").val(valor);
                    }
                });
            }
            
            $(".radio").change(function(){
                $("#filterForm").submit();
            });
            
            $(".submitViewForm").click(function (){
               var id = $(this).attr('id');
               $("#formViewRecipe"+id).submit();
            });
                
        });
    </script>
    <script type='text/javascript' src="<c:url value='/js/bootstrap.min.js' />"></script>
  </body>
</html>