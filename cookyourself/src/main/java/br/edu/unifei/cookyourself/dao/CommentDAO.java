/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.unifei.cookyourself.model.Comment;
import br.edu.unifei.cookyourself.model.Recipe;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author guilherme
 */
@Component
public class CommentDAO extends GenericDAO<Comment>{

    public CommentDAO(Session session) {
        super(session);
    }
    
    public List<Comment> findByRecipe(Recipe recipe){
        Query q = session.createQuery("from Comment c where c.recipe = :recipe");
        q.setParameter("recipe", recipe);
        
        return q.list();
    }
    
}
