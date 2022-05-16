package com.rektstudios.cooking;

public class RecipeModel {

    private final String recipeName;
    private final String recipeTime;
    private final String recipeRating;
    private final String recipeCal;
    private final int recipeImage;
    private boolean favoriteFlag;

    public RecipeModel(String recipeName, String recipeTime, String recipeRating, String recipeCal, int recipeImage) {
        this.recipeName = recipeName;
        this.recipeTime = recipeTime;
        this.recipeRating = recipeRating;
        this.recipeCal = recipeCal;
        this.recipeImage = recipeImage;
        this.favoriteFlag = false;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeTime() {
        return recipeTime;
    }

    public String getRecipeRating() {
        return recipeRating;
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
