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
import br.edu.unifei.cookyourself.dao.CommentDAO;
import br.edu.unifei.cookyourself.dao.QualificationDAO;
import br.edu.unifei.cookyourself.dao.RecipeDAO;
import br.edu.unifei.cookyourself.dao.SearchRecordsDAO;
import br.edu.unifei.cookyourself.login.Public;
import br.edu.unifei.cookyourself.login.UserSession;
import br.edu.unifei.cookyourself.model.Comment;
import br.edu.unifei.cookyourself.model.Qualification;
import br.edu.unifei.cookyourself.model.Recipe;
import br.edu.unifei.cookyourself.model.SearchRecords;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private CommentDAO commentDAO;
    
    public IndexController(Result result, RecipeDAO recipeDAO, UserSession userSession, QualificationDAO qualificationDAO, SearchRecordsDAO searchRecordsDAO, CommentDAO commentDAO){
        this.result = result;
        this.recipeDAO = recipeDAO;
        this.userSession = userSession;
        this.qualificationDAO = qualificationDAO;
        this.searchRecordsDAO = searchRecordsDAO;
        this.commentDAO = commentDAO;
    }
    
    @Path("/")
    public void index(){
        result.include("session", userSession.getUser());
    }
    
    @Path("/result")
    public void searchResult(List<String> ingredients, Double price, String duration, int yield){
        result.include("session",userSession.getUser());
        result.include("ingredients",ingredients);
        result.include("curtis",qualificationDAO.findByUser(userSession.getUser()));
        if(price == null){
            price = 200d;
        }
        result.include("price",price);
        result.include("duration",duration);
        result.include("yield",yield);
    }
    
    @Path("/destaques")
    public void destaques(){
        
        result.include("recipes",recipeDAO.findMostLikedRecipes());
        result.include("session", userSession.getUser());
        result.include("curtis",qualificationDAO.findByUser(userSession.getUser()));
    }
    
    @Path("/view/{id}")
    public void view(Long id, List<String> ingredientes){
        Recipe recipe = recipeDAO.findById(id);
        result.include("recipe",recipe);
        result.include("comments",commentDAO.findByRecipe(recipe));
        result.include("session",userSession.getUser());
        if(ingredientes !=null){
        result.include("ingredientes",ingredientes);
        int count = 0;
        List<Recipe> list = this.resturnSearchResults(ingredientes, null, null, 0);
        List<Recipe> similares = new ArrayList<Recipe>();
        if(list!=null){

            for(Recipe r: list){
                if(r.getId() != id){
                    similares.add(r);
                    count ++;
                }
                if(count >= 3){
                    break;
                }
            }
        }
        result.include("similares",similares);
        }
    }
    
    @Post("/recipe/comment")
    public void comment(Long recipeId, Comment comment,List<String> ingredients){
        Recipe recipe = recipeDAO.findById(recipeId);
        
        comment.setUser(userSession.getUser());
        comment.setRecipe(recipe);
        
        commentDAO.saveOrUpdate(comment);
        
        result.redirectTo(IndexController.class).view(recipeId,ingredients);
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
    
    @Post("/click/recipe")
    public void clickRecipe(Long id, List<String> ingredients){
        
        result.redirectTo(IndexController.class).view(id, ingredients);
        
    }
    
    @Post("/unlike/recipe/{id}")
    public void unlike(Long id){
        Recipe recipe = recipeDAO.findById(id);
        int likes = recipe.getLikes();
        likes --;
        recipe.setLikes(likes);
        recipeDAO.saveOrUpdate(recipe);
        if(userSession.isLogged()){
            Qualification like = qualificationDAO.findByUserAndRecipe(userSession.getUser(), recipe);
            qualificationDAO.delete(like);
        }
        result.use(Results.status()).ok();
    }
    
    @Post("/surpreenda")
    public void surpreenda(){
        if(userSession.isLogged()){
            List<String> ingredientsToSearch = searchRecordsDAO.findMostSearchRecs(userSession.getUser());
            if(ingredientsToSearch == null){
                result.redirectTo(IndexController.class).destaques();
            }else{
                result.redirectTo(IndexController.class).search(ingredientsToSearch, null, null, 0);
            }
        }else{
            result.include("error", "Você deve fazer o login primeiro").redirectTo(IndexController.class).index();
        }
    }
    
    @Post("/recipe/update/yield")
    public void updateYield(Recipe recipe,List<String> ingredients){
        
        Recipe newRecipe = recipeDAO.findById(recipe.getId());
        
        int newYield;
        
        if(newRecipe.getYield() != 0){
        
            newYield = (recipe.getYield() + newRecipe.getYield())/2;
        }else{
            newYield = recipe.getYield();
        }
        
        newRecipe.setYield(newYield);
        
        recipeDAO.saveOrUpdate(newRecipe);
        
        result.redirectTo(IndexController.class).view(recipe.getId(),ingredients);
    }
    
    @Post("/recipe/update/duration")
    public void updateDuration(Recipe recipe,List<String> ingredients){
        
        Recipe newRecipe = recipeDAO.findById(recipe.getId());
        newRecipe.setDuration(recipe.getDuration());
        
        recipeDAO.saveOrUpdate(newRecipe);
        
        result.redirectTo(IndexController.class).view(recipe.getId(),ingredients);
        
    }
    
    @Post("/recipe/update/price")
    public void updatePrice(Recipe recipe,List<String> ingredients){
        
        Recipe newRecipe = recipeDAO.findById(recipe.getId());
        
        Double newPrice;
        
        if(newRecipe.getPrice() != null){
            newPrice = (recipe.getPrice() + newRecipe.getPrice())/2;
        }else{
            newPrice = recipe.getPrice();
        }
        
        newRecipe.setPrice(newPrice);
        
        recipeDAO.saveOrUpdate(newRecipe);
        
        result.redirectTo(IndexController.class).view(recipe.getId(),ingredients);
        
    }
    
    @Post("/recipe/update/ingredients")
    public void updateIngredients(Recipe recipe,List<String> ingredients){
        Recipe newRecipe = recipeDAO.findById(recipe.getId());
        
        newRecipe.setIngredients(recipe.getIngredients());
        
        recipeDAO.saveOrUpdate(newRecipe);
        
        result.redirectTo(IndexController.class).view(recipe.getId(),ingredients);
    }
    
    @Post("/recipe/update/howtocook")
    public void updateHowToCook(Recipe recipe,List<String> ingredients){
        Recipe newRecipe = recipeDAO.findById(recipe.getId());
        
        newRecipe.setHowToCook(recipe.getHowToCook());
        
        recipeDAO.saveOrUpdate(newRecipe);
        
        result.redirectTo(IndexController.class).view(recipe.getId(),ingredients);
    }
    
    @Path("/search")
    public void search(List<String> ingredients, Double price, String duration, int yield){
        
        List<Recipe> recipes = this.resturnSearchResults(ingredients, price, duration, yield);
        
        if(recipes == null){
            result.redirectTo(IndexController.class).index();
        }else{
            result.include("recipes",recipes).redirectTo(IndexController.class).searchResult(ingredients, price, duration, yield);
        }    
    }
    
    private List<Recipe> resturnSearchResults(List<String> ingredients, Double price, String duration, int yield){
        boolean isThereAllIngredients = false;
        if(ingredients != null){
            
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
            
          List<Recipe> recipes = recipeDAO.findRecipeByIngredient(ingredients, price, duration, yield);
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
          if(isThereAllIngredients == false){
              result.include("message","Não encontramos nenhuma receita que contenha TODOS os ingredientes solicitados, mas as receitas a seguir são as mais parecidas com o que vc procura");
          }
          return recipes;
        }else{
          return null;
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
