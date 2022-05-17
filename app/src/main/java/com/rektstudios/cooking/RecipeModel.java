package com.rektstudios.cooking;

import java.util.ArrayList;

public class RecipeModel {

    private String recipeName, recipeTime, recipeCal;
    private int recipeImage, ratingCount, protein, fat, carbohydrate;
    private float recipeRating, oldRating;
    private boolean favoriteFlag, ratingFlag;
    private ArrayList<IngredientModel> ingredients;
    private ArrayList<StepsModel> steps;

    public RecipeModel(String recipeName, String recipeTime, float recipeRating, String recipeCal, int recipeImage) {
        this(recipeName, recipeTime, recipeRating, recipeCal, recipeImage, 0, new ArrayList<>(), new ArrayList<>(), 0, 0, 0);
    }

    public RecipeModel(String recipeName, String recipeTime, float recipeRating, String recipeCal, int recipeImage, int ratingCount, ArrayList<IngredientModel> ingredients, ArrayList<StepsModel> steps, int protein, int fat, int carbohydrate) {
        this.recipeName = recipeName;
        this.recipeTime = recipeTime;
        this.recipeRating = recipeRating;
        this.recipeCal = recipeCal;
        this.recipeImage = recipeImage;
        this.ratingCount = ratingCount;
        this.ingredients = ingredients;
        this.steps = steps;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.favoriteFlag = false;
        this.ratingFlag = false;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeTime() {
        return recipeTime;
    }

    public String getRecipeRating() {
        return String.format("%.1f", recipeRating);
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

    public String getCarbohydrate() {
        return Integer.toString(carbohydrate);
    }

    public String getFat() {
        return Integer.toString(fat);
    }

    public String getProtein() {
        return Integer.toString(protein);
    }

    public ArrayList<IngredientModel> getIngredients() {
        return ingredients;
    }

    public ArrayList<StepsModel> getSteps() {
        return steps;
    }
    public void updateRating(float rating){
        if(!ratingFlag) {
            oldRating=recipeRating;
            ratingFlag = true;
            ratingCount++;
        }
        recipeRating=(oldRating*(ratingCount-1))+rating;
        recipeRating/=ratingCount;
    }
}
