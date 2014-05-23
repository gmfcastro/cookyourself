/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.edu.unifei.cookyourself.dao.QualificationDAO;
import br.edu.unifei.cookyourself.dao.RecipeDAO;
import br.edu.unifei.cookyourself.dao.SearchRecordsDAO;
import br.edu.unifei.cookyourself.login.Public;
import br.edu.unifei.cookyourself.login.UserSession;
import br.edu.unifei.cookyourself.model.Qualification;
import br.edu.unifei.cookyourself.model.Recipe;
import br.edu.unifei.cookyourself.model.SearchRecords;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author guilherme
 */
@Resource
@Public
public class IndexController {
    
    private final Result result;
    private RecipeDAO recipeDAO;
    private UserSession userSession;
    private QualificationDAO qualificationDAO;
    private SearchRecordsDAO searchRecordsDAO;
    
    public IndexController(Result result, RecipeDAO recipeDAO, UserSession userSession, QualificationDAO qualificationDAO, SearchRecordsDAO searchRecordsDAO){
        this.result = result;
        this.recipeDAO = recipeDAO;
        this.userSession = userSession;
        this.qualificationDAO = qualificationDAO;
        this.searchRecordsDAO = searchRecordsDAO;
    }
    
    @Path("/")
    public void index(){
        result.include("session", userSession.getUser());
    }
    
    @Path("/result")
    public void searchResult(){
        result.include("session",userSession.getUser());
    }
    
    @Path("/destaques")
    public void destaques(){
        
        result.include("recipes",recipeDAO.findMostLikedRecipes());
        result.include("session", userSession.getUser());
        
    }
    
    @Path("/view/{id}")
    public void view(Long id){
        Recipe recipe = recipeDAO.findById(id);
        result.include("recipe",recipe);
        result.include("session",userSession.getUser());
    }
    
    @Post("/like/recipe/{id}")
    public void like(Long id){
        Recipe recipe = recipeDAO.findById(id);
        int likes = recipe.getLikes();
        likes ++;
        recipe.setLikes(likes);
        recipeDAO.saveOrUpdate(recipe);
        if(userSession.isLogged()){
            Qualification like = new Qualification();
            like.setRecipe(recipe);
            like.setUser(userSession.getUser());
            qualificationDAO.saveOrUpdate(like);
        }
        result.use(Results.status()).ok();
        
    }
    
    @Post("/surpreenda")
    public void surpreenda(){
        if(userSession.isLogged()){
            List<String> ingredientsToSearch = searchRecordsDAO.findMostSearchRecs(userSession.getUser());
            result.redirectTo(IndexController.class).search(ingredientsToSearch);
        }else{
            result.include("error", "Você deve fazer o login primeiro").redirectTo(IndexController.class).index();
        }
    }
    
    @Path("/search")
    public void search(List<String> ingredients){
        if(!ingredients.isEmpty()){
            
            if(userSession.isLogged()){
                for(String ing: ingredients){
                    SearchRecords srecs = searchRecordsDAO.findSearchsByIngredients(userSession.getUser(), ing);
                    if(srecs == null){
                       srecs = new SearchRecords();
                       srecs.setUser(userSession.getUser());
                       srecs.setIngredient(ing);
                       srecs.setTimesOfSearch(1L);
                    }else{
                       srecs.setTimesOfSearch(srecs.getTimesOfSearch() + 1);
                      
                    }
                    searchRecordsDAO.saveOrUpdate(srecs);
                }
            }            
            
          List<Recipe> recipes = recipeDAO.findRecipeByIngredient(ingredients);
          Map<Recipe, Integer> counts = new HashMap<Recipe, Integer>();
          for(Recipe r: recipes){
              Integer count = counts.get(r);
              if(count==null){
                  counts.put(r, 1);
              }else{
                  counts.put(r, count+1);
              }
          }
          recipes.clear();
          Map<Recipe, Integer> finalMap = sortByValues(counts);
          for (Map.Entry<Recipe, Integer> entry : finalMap.entrySet()) {
              recipes.add(entry.getKey());
          }
          result.include("recipes",recipes).redirectTo(IndexController.class).searchResult();
        }else{
          result.redirectTo(IndexController.class).index();
        }       
    }
    
    private static Map sortByValues(Map map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o2)).getValue())
                  .compareTo(((Map.Entry) (o1)).getValue());
            }
       });

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       Map sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
  }
    
}
