/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.unifei.cookyourself.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author guilherme
 */
@Component
public class UserDAO extends GenericDAO<User>{

    public UserDAO(Session session) {
        super(session);
    }
    
    public User findByCredentials(String email, String password){
        
        Query q = session.createQuery("from User u where u.email = :email and u.password = :password ");
        q.setParameter("email", email);
        q.setParameter("password", password);
        
        User u = (User) q.uniqueResult();
        session.close();
        return u;
        
    }
    
}
