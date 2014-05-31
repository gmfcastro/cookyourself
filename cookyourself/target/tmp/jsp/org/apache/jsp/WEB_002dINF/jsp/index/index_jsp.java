package org.apache.jsp.WEB_002dINF.jsp.index;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_url_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_url_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_url_value_nobody.release();
    _jspx_tagPool_c_if_test.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <meta name=\"author\" content=\"\">\n");
      out.write("    <link rel=\"shortcut icon\" href=\"../../assets/ico/favicon.ico\">\n");
      out.write("\n");
      out.write("    <title>Cookyourself</title>\n");
      out.write("\n");
      out.write("    <!-- Bootstrap core CSS -->\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      if (_jspx_meth_c_url_0(_jspx_page_context))
        return;
      out.write("\"/>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      if (_jspx_meth_c_url_1(_jspx_page_context))
        return;
      out.write("\"/>\n");
      out.write("    <script src=\"http://connect.facebook.net/en_US/all.js\"></script>\n");
      out.write("    <script>\n");
      out.write("        function myFunction(number){\n");
      out.write("            $(\"#comp\"+number).remove();\n");
      out.write("            $(\"#inputIng\"+number).remove();\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        function surpreenda(){\n");
      out.write("            $.post(\"http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/surpreenda\",function(data,status){\n");
      out.write("            });    \n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        function logout(){\n");
      out.write("            $.post(\"http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/logout\",function(data,status){\n");
      out.write("                location.reload();\n");
      out.write("            }); \n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        window.fbAsyncInit = function() {\n");
      out.write("        FB.init({\n");
      out.write("          appId      : '638862416204874', // App ID\n");
      out.write("          channelUrl : 'http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself',\n");
      out.write("          status     : true, // check login status\n");
      out.write("          cookie     : true, // enable cookies to allow the server to access the session\n");
      out.write("          xfbml      : true  // parse XFBML\n");
      out.write("        });\n");
      out.write("        };\n");
      out.write("\n");
      out.write("      (function(d){\n");
      out.write("         var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];\n");
      out.write("         if (d.getElementById(id)) {return;}\n");
      out.write("         js = d.createElement('script'); js.id = id; js.async = true;\n");
      out.write("         js.src = \"//connect.facebook.net/en_US/all.js\";\n");
      out.write("         ref.parentNode.insertBefore(js, ref);\n");
      out.write("       }(document));\n");
      out.write("        \n");
      out.write("        function loginFacebook() {\n");
      out.write("            FB.login(function(response) {\n");
      out.write("               alert(JSON.stringify(response));\n");
      out.write("               if (response.authResponse) {\n");
      out.write("                        FB.api('/me', function(response) {\n");
      out.write("                            userJson = {'user.name':response.name, 'user.password':response.id, 'user.email':response.email};\n");
      out.write("                            $.post(\"http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/doFBLogin\",userJson,function(data,status){\n");
      out.write("                                location.reload();\n");
      out.write("                            }); \n");
      out.write("                         });\n");
      out.write("               }\n");
      out.write("            }, {scope: 'email' });\n");
      out.write("        }\n");
      out.write("      \n");
      out.write("        function statusFacebook() {\n");
      out.write("            FB.getLoginStatus(function(response) {\n");
      out.write("               if (response.status === 'connected') {\n");
      out.write("                        FB.api('/me', function(response) {\n");
      out.write("                            userJson = {'user.name':response.name, 'user.password':response.id, 'user.email':response.email};\n");
      out.write("                            $.post(\"http://ec2-54-187-212-175.us-west-2.compute.amazonaws.com:8080/cookyourself/doFBLogin\",userJson,function(data,status){\n");
      out.write("                                location.reload();\n");
      out.write("                            }); \n");
      out.write("                         });               \n");
      out.write("               } else if (response.status === 'not_authorized') {\n");
      out.write("                   loginFacebook(); // nao autorizado, solicitar login\n");
      out.write("               } else {\n");
      out.write("                   loginFacebook(); // nao autorizado, solicitar login\n");
      out.write("             }});\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("    </script>\n");
      out.write("  </head>\n");
      out.write("\n");
      out.write("  <body>\n");
      out.write("        <!---------NAVBAR------->\n");
      out.write("        \n");
      out.write("         <nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                  <div class=\"navbar-header\">\n");
      out.write("                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n");
      out.write("                      <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                      <span class=\"icon-bar\"></span>\n");
      out.write("                      <span class=\"icon-bar\"></span>\n");
      out.write("                      <span class=\"icon-bar\"></span>\n");
      out.write("                    </button>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
      out.write("                       <ul class=\"nav navbar-nav\">\n");
      out.write("                        <li class=\"active\"><a href=\"#\">Home</a></li>\n");
      out.write("                        <li><a href=\"");
      if (_jspx_meth_c_url_2(_jspx_page_context))
        return;
      out.write("\">Destaques</a></li>\n");
      out.write("                       </ul>\n");
      out.write("                       ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                       ");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                  </div>\n");
      out.write("                </div>\n");
      out.write("          </nav>\n");
      out.write("        \n");
      out.write("        <!---------NAVBAR------->\n");
      out.write("        <hr>\n");
      out.write("        <nav class=\"navbar navbar-inverse navbar-fixed-top\" id=\"navIngredients\" role=\"navigation\">\n");
      out.write("                <div class=\"container-fluid\" id=\"ingNav\">\n");
      out.write("                  <div class=\"navbar-header\">\n");
      out.write("                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n");
      out.write("                      <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                      <span class=\"icon-bar\"></span>\n");
      out.write("                      <span class=\"icon-bar\"></span>\n");
      out.write("                      <span class=\"icon-bar\"></span>\n");
      out.write("                    </button>\n");
      out.write("                    <a class=\"navbar-brand\" href=\"#\">Ingredientes:</a>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
      out.write("                       <ul class=\"nav navbar-nav\" id=\"listIngredients\">\n");
      out.write("                       </ul>\n");
      out.write("                  </div>\n");
      out.write("                </div>\n");
      out.write("          </nav>\n");
      out.write("      \n");
      out.write("        <div class=\"container-full\" id=\"indexBg\">\n");
      out.write("\n");
      out.write("              <div class=\"row\">\n");
      out.write("\n");
      out.write("                <div class=\"col-lg-12 text-center v-center\">\n");
      out.write("                  <br><br><br><br><br><br>\n");
      out.write("                  \n");
      out.write("                  <img src=\"");
      if (_jspx_meth_c_url_3(_jspx_page_context))
        return;
      out.write("\" alt=\"Cookyourself\"/>\n");
      out.write("\n");
      out.write("                  <br><br>\n");
      out.write("\n");
      out.write("                  <form class=\"col-lg-12 form-inline\" role=\"form\" method=\"post\" action=\"");
      if (_jspx_meth_c_url_4(_jspx_page_context))
        return;
      out.write("\">\n");
      out.write("                    <div class=\"input-group\" style=\"width:600px;text-align:center;margin:0 auto;\" id=\"formIngredients\">\n");
      out.write("                    <input class=\"form-control input-lg\" id=\"inputIgredients\" placeholder=\"Procure por ingredientes\" type=\"text\">\n");
      out.write("                      <span class=\"input-group-btn\"><button class=\"btn btn-lg btn-danger\" id=\"addIng\" type=\"button\">+</button></span>\n");
      out.write("                    </div>\n");
      out.write("                    <br><br>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        ");
      if (_jspx_meth_c_if_2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                        ");
      if (_jspx_meth_c_if_3(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    </div> \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("              </div> <!-- /row -->\n");
      out.write("\n");
      out.write("                  <div class=\"row\">\n");
      out.write("\n");
      out.write("                <div class=\"col-lg-12 text-center v-center\" style=\"font-size:39pt;\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("              </div>\n");
      out.write("\n");
      out.write("                <br><br><br><br><br>\n");
      out.write("\n");
      out.write("        </div> <!-- /container full -->\n");
      out.write("\n");
      out.write("        <div class=\"container-fluid\">\n");
      out.write("\n");
      out.write("                <div class=\"row\" style=\"height: 100px; background-color: rgba(39,39,39,100);\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"row\" style=\"background-color: rgba(39,39,39,100); color: #ffffff\">\n");
      out.write("                    <div class=\"col-lg-12\">\n");
      out.write("                    <br><br>\n");
      out.write("                      <p class=\"pull-right\"> &nbsp; ©Copyright 2014 Cookyourself</p>\n");
      out.write("                    <br><br>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <script type='text/javascript' src=\"//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>\n");
      out.write("    <script type='text/javascript' src=\"");
      if (_jspx_meth_c_url_6(_jspx_page_context))
        return;
      out.write("\"></script>\n");
      out.write("    <script>\n");
      out.write("    $(document).ready(function (){\n");
      out.write("        var counter = 0;\n");
      out.write("        \n");
      out.write("        $(\"#inputIgredients\").keypress(function(event){\n");
      out.write("            if(event.which===43){\n");
      out.write("                var ingredient = $(\"#inputIgredients\").val();\n");
      out.write("                if(ingredient!== \"\"){\n");
      out.write("                    ingredient.replace(\"+\", \"\");\n");
      out.write("                    counter++;\n");
      out.write("                    $(\"#listIngredients\").append('<li id=\"comp'+counter+'\"><div class=\"ingredientComp ingredientComp-list alert-dismissable\"><button type=\"button\" class=\"close pull-right\" id=\"ing'+counter+'\" onclick=\"myFunction('+counter+')\" aria-hidden=\"true\">&times;</button>'+ingredient+'</div></li>');\n");
      out.write("                    $(\"#formIngredients\").append('<input type=\"text\" name=\"ingredients['+counter+']\" id=\"inputIng'+counter+'\" value='+ingredient+' hidden>');\n");
      out.write("                    $(this).val(\"\");\n");
      out.write("                    event.preventDefault();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("        \n");
      out.write("        $(\"#addIng\").click(function (){\n");
      out.write("           var ingredient = $(\"#inputIgredients\").val();\n");
      out.write("           counter++;\n");
      out.write("           $(\"#listIngredients\").append('<li id=\"comp'+counter+'\"><div class=\"ingredientComp ingredientComp-list alert-dismissable\"><button type=\"button\" class=\"close pull-right\" id=\"ing'+counter+'\" onclick=\"myFunction('+counter+')\" aria-hidden=\"true\">&times;</button>'+ingredient+'</div></li>');\n");
      out.write("           $(\"#formIngredients\").append('<input type=\"text\" name=\"ingredients['+counter+']\" id=\"inputIng'+counter+'\" value='+ingredient+' hidden>');\n");
      out.write("           $(\"#inputIgredients\").val(\"\");\n");
      out.write("        });\n");
      out.write("     });\n");
      out.write("    </script>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_url_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_0.setPageContext(_jspx_page_context);
    _jspx_th_c_url_0.setParent(null);
    _jspx_th_c_url_0.setValue("/css/bootstrap.css");
    int _jspx_eval_c_url_0 = _jspx_th_c_url_0.doStartTag();
    if (_jspx_th_c_url_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_0);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_0);
    return false;
  }

  private boolean _jspx_meth_c_url_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_1.setPageContext(_jspx_page_context);
    _jspx_th_c_url_1.setParent(null);
    _jspx_th_c_url_1.setValue("/css/style.css");
    int _jspx_eval_c_url_1 = _jspx_th_c_url_1.doStartTag();
    if (_jspx_th_c_url_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_1);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_1);
    return false;
  }

  private boolean _jspx_meth_c_url_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_2.setPageContext(_jspx_page_context);
    _jspx_th_c_url_2.setParent(null);
    _jspx_th_c_url_2.setValue("/destaques");
    int _jspx_eval_c_url_2 = _jspx_th_c_url_2.doStartTag();
    if (_jspx_th_c_url_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_2);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_2);
    return false;
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session == null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                       <ul class=\"nav navbar-nav navbar-right\">\n");
        out.write("                           <li>\n");
        out.write("                               <button class=\"btn btn-danger\" onclick=\"statusFacebook();\" style=\"margin-top: 10px; margin-right: 20px;\">Facebook Login</button>\n");
        out.write("                           </li>\n");
        out.write("                       </ul>\n");
        out.write("                       ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session != null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                          <ul class=\"nav navbar-nav navbar-right\">\n");
        out.write("                           <li>\n");
        out.write("                               <label style=\"margin-top: 15px;margin-right: 10px;color: #ffffff;\">Olá, ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</label>\n");
        out.write("                               <button class=\"btn btn-danger\" onclick=\"logout();\" style=\"margin-top: 0px; margin-right: 20px;\">Logout</button>\n");
        out.write("                           </li>\n");
        out.write("                          </ul> \n");
        out.write("                       ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_c_url_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_3 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_3.setPageContext(_jspx_page_context);
    _jspx_th_c_url_3.setParent(null);
    _jspx_th_c_url_3.setValue("/images/cyslogo.png");
    int _jspx_eval_c_url_3 = _jspx_th_c_url_3.doStartTag();
    if (_jspx_th_c_url_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_3);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_3);
    return false;
  }

  private boolean _jspx_meth_c_url_4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_4 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_4.setPageContext(_jspx_page_context);
    _jspx_th_c_url_4.setParent(null);
    _jspx_th_c_url_4.setValue("/search");
    int _jspx_eval_c_url_4 = _jspx_th_c_url_4.doStartTag();
    if (_jspx_th_c_url_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_4);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_4);
    return false;
  }

  private boolean _jspx_meth_c_if_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session!=null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                        <div class=\"col-sm-5 col-sm-offset-1\">\n");
        out.write("                            <button class=\"btn btn-lg btn-danger pull-right\" style=\"margin-right: 50px\" type=\"submit\"><span class=\"glyphicon glyphicon-search\"></span> Pesquisar</button>\n");
        out.write("                        </div>\n");
        out.write("                  </form>\n");
        out.write("                        <div class=\"col-sm-1\">\n");
        out.write("                            <form class=\"col-lg-12 form-inline\" role=\"form\" method=\"post\" action=\"");
        if (_jspx_meth_c_url_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_2, _jspx_page_context))
          return true;
        out.write("\">\n");
        out.write("                                <button class=\"btn btn-lg btn-danger\" type=\"submit\"><span class=\"glyphicon glyphicon-star\"></span> Surpreenda-me !</button>\n");
        out.write("                            </form>\n");
        out.write("                        </div>\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }

  private boolean _jspx_meth_c_url_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_5 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_5.setPageContext(_jspx_page_context);
    _jspx_th_c_url_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
    _jspx_th_c_url_5.setValue("/surpreenda");
    int _jspx_eval_c_url_5 = _jspx_th_c_url_5.doStartTag();
    if (_jspx_th_c_url_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_5);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_5);
    return false;
  }

  private boolean _jspx_meth_c_if_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
    _jspx_th_c_if_3.setParent(null);
    _jspx_th_c_if_3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${session==null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                           <button class=\"btn btn-lg btn-danger\" type=\"submit\"><span class=\"glyphicon glyphicon-search\"></span> Pesquisar</button> \n");
        out.write("                           </form>\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_c_if_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
    return false;
  }

  private boolean _jspx_meth_c_url_6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_url_6 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _jspx_tagPool_c_url_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_url_6.setPageContext(_jspx_page_context);
    _jspx_th_c_url_6.setParent(null);
    _jspx_th_c_url_6.setValue("/js/bootstrap.min.js");
    int _jspx_eval_c_url_6 = _jspx_th_c_url_6.doStartTag();
    if (_jspx_th_c_url_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_6);
      return true;
    }
    _jspx_tagPool_c_url_value_nobody.reuse(_jspx_th_c_url_6);
    return false;
  }
}
