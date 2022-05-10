/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.recipes.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nikolaus
 */
@Entity
@Table(name = "nutrition_info")
public class NutritionInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calories")
    private float calories;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carbohydrates")
    private float carbohydrates;
    @Basic(optional = false)
    @NotNull
    @Column(name = "protein")
    private float protein;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fat")
    private float fat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saturated_fat")
    private float saturatedFat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sugars")
    private float sugars;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nutritionInfoId")
    private List<Recipe> recipeList;

    public NutritionInfo() {
    }

    public NutritionInfo(Integer id) {
        this.id = id;
    }

    public NutritionInfo(Integer id, float calories, float carbohydrates, float protein, float fat, float saturatedFat, float sugars) {
        this.id = id;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.sugars = sugars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getSugars() {
        return sugars;
    }

    public void setSugars(float sugars) {
        this.sugars = sugars;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NutritionInfo)) {
            return false;
        }
        NutritionInfo other = (NutritionInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.NutritionInfo[ id=" + id + " ]";
    }
    
}
