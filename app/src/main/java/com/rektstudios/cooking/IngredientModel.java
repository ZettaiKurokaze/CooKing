package com.rektstudios.cooking;

public class IngredientModel {

    private String ingredientName, amountType;
    private int imageRes, amount;
    private boolean buttonFlag;

    public IngredientModel(int imageRes, String ingredientName) {
        this(imageRes, ingredientName, "", 0);
    }

    public IngredientModel(int imageRes, String ingredientName, String amountType, int amount){
        this.imageRes=imageRes;
        this.ingredientName=ingredientName;
        this.amountType = amountType;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public String getAmountText(){
        return amountType.toString()+" "+amountType;
    }
}
