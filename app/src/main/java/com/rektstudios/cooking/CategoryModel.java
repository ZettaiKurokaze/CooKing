package com.rektstudios.cooking;

public class CategoryModel {

    private final String categoryName;
    private boolean touchFlag;

    public CategoryModel(String categoryName){
        this.categoryName=categoryName;
        this.touchFlag=false;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isTouchFlag() {
        return touchFlag;
    }

    public void setTouchFlag(boolean touchFlag) {
        this.touchFlag = touchFlag;
    }

}
