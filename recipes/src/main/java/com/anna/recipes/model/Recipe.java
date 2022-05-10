/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anna.recipes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nikolaus
 */
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4096)
    @Column(name = "uri")
    private String uri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65535)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "hash_value")
    private String hashValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preparation_time")
    private int preparationTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cooking_time")
    private int cookingTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "servings")
    private String servings;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scraped_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scrapedAt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "writer")
    private String writer;
    @Size(max = 65535)
    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_ingredients")
    private int numberOfIngredients;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Category categoryId;
    @JoinColumn(name = "difficulty_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Difficulty difficultyId;
    @JoinColumn(name = "nutrition_info_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private NutritionInfo nutritionInfoId;
    @JoinColumn(name = "scraper_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Scraper scraperId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId", fetch = FetchType.EAGER)
    private List<Step> stepList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId")
    private List<IngredientGroup> ingredientGroupList;

    public Recipe() {
    }

    public Recipe(Integer id) {
        this.id = id;
    }

    public Recipe(Integer id, String uri, String title, String description, String hashValue, int preparationTime, int cookingTime, String servings, Date scrapedAt, String writer, int numberOfIngredients) {
        this.id = id;
        this.uri = uri;
        this.title = title;
        this.description = description;
        this.hashValue = hashValue;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.servings = servings;
        this.scrapedAt = scrapedAt;
        this.writer = writer;
        this.numberOfIngredients = numberOfIngredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public Date getScrapedAt() {
        return scrapedAt;
    }

    public void setScrapedAt(Date scrapedAt) {
        this.scrapedAt = scrapedAt;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public void setNumberOfIngredients(int numberOfIngredients) {
        this.numberOfIngredients = numberOfIngredients;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Difficulty getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Difficulty difficultyId) {
        this.difficultyId = difficultyId;
    }

    public NutritionInfo getNutritionInfoId() {
        return nutritionInfoId;
    }

    public void setNutritionInfoId(NutritionInfo nutritionInfoId) {
        this.nutritionInfoId = nutritionInfoId;
    }

    public Scraper getScraperId() {
        return scraperId;
    }

    public void setScraperId(Scraper scraperId) {
        this.scraperId = scraperId;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
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
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Recipe[ id=" + id + " ]";
    }
    
}
