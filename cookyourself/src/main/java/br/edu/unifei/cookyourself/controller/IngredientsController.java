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
import br.edu.unifei.cookyourself.dao.IngredientDAO;
import br.edu.unifei.cookyourself.login.Permission;
import br.edu.unifei.cookyourself.model.Ingredient;
import br.edu.unifei.cookyourself.model.UserRole;


/**
 *
 * @author guilherme
 */
@Resource
@Permission(UserRole.ADMIN)
public class IngredientsController {
    
    private Result result;
    private IngredientDAO dao;
    
    public IngredientsController(Result result, IngredientDAO dao){
        this.result = result;
        this.dao = dao;
    }
    
    @Path("/admin/ingredients/add")
    public void add(){ 
        
    }
    
    @Path("/admin/ingredients/edit/{id}")
    public void edit(Long id){
        result.include("ingredient", dao.findById(id));
    }
    
    @Path("/admin/ingredients")
    public void index(){
        result.include("ingredients",dao.findAll());
    }
    
    @Path("/admin/ingredients/delete/{id}")
    public void delete(Long id){
        dao.delete(dao.findById(id));
        result.include("warning","Ingrediente deletado com sucesso").redirectTo(IngredientsController.class).index();
    }
    
    @Post("/admin/ingredients/save")
    public void save (Ingredient ingredient){
        dao.saveOrUpdate(ingredient);
        result.include("success","Ingrediente casdastrado com sucesso").redirectTo(IngredientsController.class).index();
    }
    
}
