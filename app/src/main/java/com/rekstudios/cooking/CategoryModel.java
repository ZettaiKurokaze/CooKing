package com.rekstudios.cooking;

public class CategoryModel {

    private String categoryName;
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
