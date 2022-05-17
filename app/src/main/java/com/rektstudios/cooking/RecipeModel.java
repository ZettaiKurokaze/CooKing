package com.rektstudios.cooking;

import java.util.ArrayList;

public class RecipeModel {

    private String recipeName, recipeTime, recipeCal;
    private int recipeImage, ratingCount;
    private float recipeRating;
    private boolean favoriteFlag;
    private ArrayList<IngredientModel> ingredients;
    private ArrayList<StepsModel> steps;

    public RecipeModel(String recipeName, String recipeTime, float recipeRating, String recipeCal, int recipeImage) {
        this(recipeName, recipeTime, recipeRating, recipeCal, recipeImage, 0, new ArrayList<>(), new ArrayList<>());
    }

    public RecipeModel(String recipeName, String recipeTime, float recipeRating, String recipeCal, int recipeImage, int ratingCount, ArrayList<IngredientModel> ingredients, ArrayList<StepsModel> steps) {
        this.recipeName = recipeName;
        this.recipeTime = recipeTime;
        this.recipeRating = recipeRating;
        this.recipeCal = recipeCal;
        this.recipeImage = recipeImage;
        this.ratingCount = ratingCount;
        this.ingredients = ingredients;
        this.steps = steps;
        this.favoriteFlag = false;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeTime() {
        return recipeTime;
    }

    public String getRecipeRating() {
        return Float.toString(recipeRating);
    }

    public String getRecipeCal() {
        return recipeCal;
    }

    public int getRecipeImage() {
        return recipeImage;
    }

    public boolean isFavoriteFlag() {
        return favoriteFlag;
    }

    public void setFavoriteFlag(boolean favoriteFlag) {
        this.favoriteFlag = favoriteFlag;
    }
}
