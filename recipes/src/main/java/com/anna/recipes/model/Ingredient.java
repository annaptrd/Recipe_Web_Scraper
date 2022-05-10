/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.recipes.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nikolaus
 */
@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "hash_value")
    private String hashValue;
    @JoinTable(name = "ingredient_group_has_ingredient", joinColumns = {
        @JoinColumn(name = "ingredient_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "ingredient_group_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<IngredientGroup> ingredientGroupList;

    public Ingredient() {
    }

    public Ingredient(Integer id) {
        this.id = id;
    }

    public Ingredient(Integer id, String description, String hashValue) {
        this.id = id;
        this.description = description;
        this.hashValue = hashValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public List<IngredientGroup> getIngredientGroupList() {
        return ingredientGroupList;
    }

    public void setIngredientGroupList(List<IngredientGroup> ingredientGroupList) {
        this.ingredientGroupList = ingredientGroupList;
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
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ingredient[ id=" + id + " ]";
    }
    
}
