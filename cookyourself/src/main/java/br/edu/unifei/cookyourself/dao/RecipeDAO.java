/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.unifei.cookyourself.model.Recipe;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author guilherme
 */
@Component
public class RecipeDAO extends GenericDAO<Recipe>{

    public RecipeDAO(Session session) {
        super(session);
    }
    
    public List<Recipe> findMostLikedRecipes(){
        
        Query q = session.createQuery("from Recipe as r order by r.likes desc");
        q.setMaxResults(50);
        session.close();
        return q.list();
        
    }
    
    public List<Recipe> findRecipeByIngredient(List<String> ingredients){
        List<Recipe> recipes = new ArrayList<Recipe>();
        for(String s: ingredients){
            Query query = session.createQuery("Select r from Recipe as r where r.ingredients like '%"+s+"%'");
            for(Recipe r:(List<Recipe>)query.list()){
                recipes.add(r);
            }
        }
        session.close();
        return recipes;
    }
    
    public void deleteAllAnaMaria(){
       Query q = session.createQuery("Delete from Recipe r where r.author = 'Ana Maria Braga'");
       session.close();
    }
    
    public void deleteAll(){
        Query q = session.createQuery("Delete from Recipe");
        session.close();
    }
    
}
