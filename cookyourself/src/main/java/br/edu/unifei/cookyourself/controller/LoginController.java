/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.controller;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.unifei.cookyourself.dao.UserDAO;
import br.edu.unifei.cookyourself.login.Public;
import br.edu.unifei.cookyourself.login.UserSession;
import br.edu.unifei.cookyourself.model.User;
import br.edu.unifei.cookyourself.model.UserRole;

/**
 *
 * @author guilherme
 */
@Resource
@Public
public class LoginController {
    
    private Result result;
    private UserDAO userDAO;
    private UserSession userSession;
    
    public LoginController(Result result,UserDAO userDAO, UserSession userSession){
        this.result = result;
        this.userDAO = userDAO;
        this.userSession = userSession;
    }
    
    @Post("/logout")
    public void logout(){
        User u = userSession.getUser();
        userSession.setUser(null);
        if(u.getUserRole() == UserRole.ADMIN){
            result.redirectTo(AdminController.class).index();
        }else{
            result.redirectTo(IndexController.class).index();
        }
    }
    
    @Post("/doFBLogin")
    public void doFBLogin(User user){
        User u = userDAO.findByCredentials(user.getEmail(), user.getPassword());
        if(u!=null){
            userSession.setUser(u);
        }else{
            user.setUserRole(UserRole.USER);
            userDAO.saveOrUpdate(user);
            userSession.setUser(user);
        }
        result.redirectTo(IndexController.class).index();
    }
    
    
    @Post("/dologin")
    public void doLogin(String email, String password){
        
        User u = userDAO.findByCredentials(email, password);
        
        if(u != null){
          userSession.setUser(u);
        }else{
          result.include("erros", "Usuário não existe ou os dados estão incorretos");
        }
        result.redirectTo(AdminController.class).index();
        
    }
    
}
