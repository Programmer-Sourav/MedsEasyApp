package com.portfolio.apps.pharmeasymeds.Pojo;

public class BrowsedItems {
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

    public String getMax_retail_price() {
        return max_retail_price;
    }

    public void setMax_retail_price(String max_retail_price) {
        this.max_retail_price = max_retail_price;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    String max_retail_price;
    String selling_price;

    public BrowsedItems(int drawableOfTheCategory, String nameOfTheCategory, String max_retail_price, String selling_price){
        this.drawableOfTheCategory = drawableOfTheCategory;
        this.nameOfTheCategory = nameOfTheCategory;
        this.max_retail_price = max_retail_price;
        this.selling_price = selling_price;
    }
}

