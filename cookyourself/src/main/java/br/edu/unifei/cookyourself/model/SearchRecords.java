/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author guilherme
 */
@Entity
@Table(name = "search_records")
public class SearchRecords implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private String ingredient;
    private Long timesOfSearch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Long getTimesOfSearch() {
        return timesOfSearch;
    }

    public void setTimesOfSearch(Long timesOfSearch) {
        this.timesOfSearch = timesOfSearch;
    }
    
    
    
}
