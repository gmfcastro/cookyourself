/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.unifei.cookyourself.model.SearchRecords;
import br.edu.unifei.cookyourself.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author guilherme
 */
@Component
public class SearchRecordsDAO extends GenericDAO<SearchRecords>{

    public SearchRecordsDAO(Session session) {
        super(session);
    }
    
    public List<String> findMostSearchRecs(User user){
        List<String> ingredients = new ArrayList<String>();
        Query q = session.createQuery("from SearchRecords as sr where sr.user = :user order by sr.timesOfSearch desc");
        q.setMaxResults(6);
        q.setParameter("user", user);
        
        for(SearchRecords sr: (List<SearchRecords>)q.list()){
            ingredients.add(sr.getIngredient());
        }
        
        return ingredients;
    }
    
    public SearchRecords findSearchsByIngredients(User user, String ingredient){
        Query q = session.createQuery("from SearchRecords as sr where sr.user = :user and sr.ingredient like '%"+ingredient+"%'");
        q.setParameter("user", user);
        
        return (SearchRecords)q.uniqueResult();
        
    }
    
}
