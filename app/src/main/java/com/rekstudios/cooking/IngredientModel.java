package com.rekstudios.cooking;

public class IngredientModel {

    private String ingredientName;
    private int imageRes;
    private boolean buttonFlag;

    public IngredientModel(int imageRes, String ingredientName){
        this.imageRes=imageRes;
        this.ingredientName=ingredientName;
        this.buttonFlag=false;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getImageRes() {
        return imageRes;
    }

    public boolean isButtonFlag() {
        return buttonFlag;
    }

    public void setButtonFlag(boolean buttonFlag) {
        this.buttonFlag = buttonFlag;
    }
}
