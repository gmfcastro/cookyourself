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
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.edu.unifei.cookyourself.dao.IngredientDAO;
import br.edu.unifei.cookyourself.dao.RecipeDAO;
import br.edu.unifei.cookyourself.dao.RecipeIngredientDAO;
import br.edu.unifei.cookyourself.login.Permission;
import br.edu.unifei.cookyourself.model.Recipe;
import br.edu.unifei.cookyourself.model.RecipeIngredient;
import br.edu.unifei.cookyourself.model.UserRole;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author guilherme
 */
@Resource
@Permission(UserRole.ADMIN)
public class RecipesController {
    
    private Result result;
    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO recipeIngredientDAO;
    private IngredientDAO ingredientDAO;
    
    public RecipesController(Result result, RecipeDAO recipeDAO, RecipeIngredientDAO recipeIngredientDAO, IngredientDAO ingredientDAO){
        
        this.recipeDAO = recipeDAO;
        this.result = result;
        this.recipeIngredientDAO = recipeIngredientDAO;
        this.ingredientDAO = ingredientDAO;
        
    }
    
    @Path("/admin/recipe")
    public void index(){
        result.include("recipes",recipeDAO.findAll());
    }
    
    @Path("/admin/recipe/add")
    public void add(){
        
    }
    
    @Path("/admin/recipe/edit/{id}")
    public void edit(Long id){
        result.include("recipe",recipeDAO.findById(id));
        result.include("ingredients",ingredientDAO.findAll());
    }
    
    @Path("/admin/recipe/delete/{id}")
    public void delete(Long id){
        recipeDAO.delete(recipeDAO.findById(id));
        result.include("warning", "Receita deletada com sucesso").redirectTo(RecipesController.class).index();
    }
    
    @Post("/admin/recipe/save")
    public void save(Recipe recipe,UploadedFile imagem) throws IOException{
        if(imagem!=null){
            String fileName = imagem.getFileName();
            String newfileName = recipe.getTitle();
            int start = fileName.lastIndexOf(".");
            String extensao = (start > 0) ? fileName.substring(start) : ".jpg";
            File destino = new File("/Users/guilherme/NetBeansProjects/cookyourself/src/main/webapp/recipeimages",newfileName+extensao); 
             try {
                IOUtils.copyLarge(imagem.getFile(), new FileOutputStream(destino));
                recipe.setPhotoURL("/cookyourself/recipeimages/"+newfileName+extensao);
             } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new FileNotFoundException("Arquivo não encontrado!");
             } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Não foi possível enviar o arquivo!");
             }
        }
        recipeDAO.saveOrUpdate(recipe);
        result.include("success", "Receita cadastrada com suscesso").redirectTo(RecipesController.class).index();
    }
    
}
