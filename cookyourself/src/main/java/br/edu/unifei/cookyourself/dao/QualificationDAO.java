/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.unifei.cookyourself.model.Qualification;
import br.edu.unifei.cookyourself.model.Recipe;
import br.edu.unifei.cookyourself.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author guilherme
 */
@Component
public class QualificationDAO extends GenericDAO<Qualification>{

    public QualificationDAO(Session session) {
        super(session);
    }
    
    public List<Qualification> findByUser(User user){
        Query q = session.createQuery("from Qualification q where q.user = :user");
        q.setParameter("user", user);
        
        return q.list();
    }
    
    public Qualification findByUserAndRecipe(User user, Recipe recipe){
        Query q = session.createQuery("from Qualification q where q.user = :user and q.recipe = :recipe");
        q.setParameter("user", user);
        q.setParameter("recipe", recipe);
        
        return (Qualification)q.uniqueResult();
    }
    
}
