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
import br.edu.unifei.cookyourself.dao.CrawlerRecordDAO;
import br.edu.unifei.cookyourself.dao.RecipeDAO;
import br.edu.unifei.cookyourself.login.Permission;
import br.edu.unifei.cookyourself.login.Public;
import br.edu.unifei.cookyourself.login.UserSession;
import br.edu.unifei.cookyourself.model.CrawlerRecord;
import br.edu.unifei.cookyourself.model.Recipe;
import br.edu.unifei.cookyourself.model.UserRole;
import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author guilherme
 */
@Resource
public class AdminController {
    
    private final Result result;
    private final UserSession userSession;
    private final RecipeDAO recipeDAO;
    private final CrawlerRecordDAO crawlerDAO;
    public static int counter = 5701;
    public static int inseridos = 0;
    
    public AdminController(Result result, UserSession userSession, RecipeDAO recipeDAO, CrawlerRecordDAO crawlerDAO){
        this.result = result;
        this.userSession = userSession;
        this.recipeDAO = recipeDAO;
        this.crawlerDAO = crawlerDAO;
    }
    
    @Path("/admin")
    @Public
    public void index(){
        if(userSession.getUser() != null){
            if(userSession.getUser().getUserRole().equals(UserRole.ADMIN)){
                result.redirectTo(AdminController.class).welcome();
            }
        }
    }
    
    @Path("/admin/welcome")
    @Permission(UserRole.ADMIN)
    public void welcome(){
        result.include("recipes", recipeDAO.findAll());
    }
    
    @Path("/admin/crawler")
    public void crawler(){
        
    }
    
    @Post("/admin/execute/crawler")
    public void executeCrawler() throws Exception{
        recipeDAO.deleteAllAnaMaria();
        processPage("http://anamariabraga.globo.com/home/receitas/receitas.php?id_rec=");
    }
    
    private void processPage(String URL) throws IOException{
        try{
            String crawledRecipe = URL+counter;
            boolean isToSave = true;
            Document doc = Jsoup.connect(crawledRecipe).get();        
            String howToCook = doc.select("div#BoxModoPreparo").text();        
            String photoURL = doc.select("div#ContColLeft > p > img").attr("abs:src");        
            String recipeTitle = doc.select("h2#ReceitasTitulo").text();
            String ingredients = "";        
            try{
                String ingredients1 = doc.select("div#ContColLeft > p").get(2).text();
                if(ingredients1.length() > 50 ){
                    ingredients = ingredients1;
                }else{
                    isToSave = false;
                }
            }catch(IndexOutOfBoundsException e){
                isToSave = false;
                System.out.println("Out of Bounds");
            }
            try{
                String ingredients2 = doc.select("div#ContColLeft > p").get(3).text();
                if(ingredients2.length() > 50){
                    ingredients = ingredients2;
                }
            }catch(IndexOutOfBoundsException e){
                System.err.println("out of bounds");
                if(!isToSave){
                    isToSave = false;
                }
            }
            if(isToSave){
                Recipe recipe = new Recipe();
                recipe.setTitle(recipeTitle);
                recipe.setPhotoURL(photoURL);
                recipe.setIngredients(ingredients);
                recipe.setHowToCook(howToCook);
                recipe.setAuthor("Ana Maria Braga");
                recipeDAO.saveOrUpdate(recipe);
                inseridos ++;
            }
            if(counter <= 7500){
                counter++;
                processPage(URL);
            }else{
                result.include("success", "Inseridos: "+inseridos+" receitas").redirectTo(AdminController.class).crawler();
            }   
        }catch(OutOfMemoryError e){
            result.include("success", "Inseridos: "+inseridos+" receitas").redirectTo(AdminController.class).crawler();
        }
    }
}
