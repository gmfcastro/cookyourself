/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.login;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.edu.unifei.cookyourself.controller.AdminController;
import br.edu.unifei.cookyourself.controller.IndexController;
import br.edu.unifei.cookyourself.model.UserRole;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author guilherme
 */
@Intercepts
public class LoginInterceptor implements Interceptor {

    private UserSession userSession;
    private Result result;
    private Method m;

    public LoginInterceptor(UserSession userSession, Result result) {
        this.userSession = userSession;
        this.result = result;
    }

    private boolean hasAccess(Permission permission) {
        if (permission == null) {
            return true;
        }

        Collection<UserRole> perfilList = Arrays.asList(permission.value());

        return perfilList.contains(userSession.getUser().getUserRole());
    }

    @Override
    public void intercept(InterceptorStack is, ResourceMethod rm, Object o) throws InterceptionException {
        Permission methodPermission = rm.getMethod().getAnnotation(Permission.class);
        Permission controllerPermission = rm.getResource().getType().getAnnotation(Permission.class);
        if(userSession.isLogged()){
            if (this.hasAccess(methodPermission) && this.hasAccess(controllerPermission)) {
                is.next(rm, o);          
            }
       }else{
          if(methodPermission != null){
            Collection<UserRole> perfilList = Arrays.asList(methodPermission.value());
            if(perfilList.contains(UserRole.ADMIN)){
              result.redirectTo(AdminController.class).index();
            }else{
              result.redirectTo(IndexController.class).index();
            }
          }
          if(controllerPermission != null){
            Collection<UserRole> perfilListClass = Arrays.asList(controllerPermission.value());
            if(perfilListClass.contains(UserRole.ADMIN)){
                result.redirectTo(AdminController.class).index();
            }else{
              result.redirectTo(IndexController.class).index();
            } 
          }
       }
    }

    @Override
    public boolean accepts(ResourceMethod rm) {
        return !(rm.getMethod().isAnnotationPresent(Public.class) ||
            rm.getResource().getType().isAnnotationPresent(Public.class));
    }
}
