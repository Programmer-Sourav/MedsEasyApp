package com.portfolio.apps.pharmeasymeds.Pojo;

public class MedicineCategory {
    public int getDrawableOfTheCategory() {
        return drawableOfTheCategory;
    }

    public void setDrawableOfTheCategory(int drawableOfTheCategory) {
        this.drawableOfTheCategory = drawableOfTheCategory;
    }

    public String getNameOfTheCategory() {
        return nameOfTheCategory;
    }

    public void setNameOfTheCategory(String nameOfTheCategory) {
        this.nameOfTheCategory = nameOfTheCategory;
    }

    int drawableOfTheCategory;
    String nameOfTheCategory;

    public MedicineCategory(int drawableOfTheCategory, String nameOfTheCategory){
        this.drawableOfTheCategory = drawableOfTheCategory;
        this.nameOfTheCategory = nameOfTheCategory;
    }
}
