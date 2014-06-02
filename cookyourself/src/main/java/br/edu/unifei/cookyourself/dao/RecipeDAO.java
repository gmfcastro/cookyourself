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
        
        return q.list();
        
    }
    
    public List<Recipe> findRecipeByIngredient(List<String> ingredients, Double price, String duration, int yield){
        List<Recipe> recipes = new ArrayList<Recipe>();
        
        if(price !=null || yield != 0  ){
            
          if(duration == null){
              if(price==null){
                  price = 200d;
              }
              for(String s: ingredients){
                Query query = session.createQuery("Select r from Recipe as r where r.ingredients like '%"+s+"%' or r.title like '%"+s+"%' and r.price<"+price+" and r.yield >"+yield+"");
                for(Recipe r:(List<Recipe>)query.list()){
                    recipes.add(r);
                }
            }
          }else{
            for(String s: ingredients){
                Query query = session.createQuery("Select r from Recipe as r where r.ingredients like '%"+s+"%' or r.title like '%"+s+"%' and r.price<"+price+" and r.yield >"+yield+" and r.duration ='"+duration+"' ");
                for(Recipe r:(List<Recipe>)query.list()){
                    recipes.add(r);
                }
            }  
          }
            
            
        }else{
        
            for(String s: ingredients){
                Query query = session.createQuery("Select r from Recipe as r where r.ingredients like '%"+s+"%' or r.title like '%"+s+"%'");
                for(Recipe r:(List<Recipe>)query.list()){
                    recipes.add(r);
                }
            }
        }
        return recipes;
    }
    
    public void deleteAllAnaMaria(){
       Query q = session.createQuery("Delete from Recipe r where r.author = 'Ana Maria Braga'");
    }
    
    public void deleteAll(){
        Query q = session.createQuery("Delete from Recipe");
    }
    
}
