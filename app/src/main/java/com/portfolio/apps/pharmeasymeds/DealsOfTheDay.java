package com.portfolio.apps.pharmeasymeds;

public class DealsOfTheDay {
    private String nameOfTheProduct;
    private String mrpOfTheProduct;
    private String discountedPriceOfTheProduct;
    private String drawableOfTheCategory;
    private String offerDiscount;

    public DealsOfTheDay(String product_image, String product_name, String mrp_price, String current_price) {
        this.drawableOfTheCategory = product_image;
        this.nameOfTheProduct = product_name;
        this.mrpOfTheProduct = mrp_price;
        this.discountedPriceOfTheProduct = current_price;
    }

    public String getDrawableOfTheCategory() {
        return drawableOfTheCategory;
    }

    public void setDrawableOfTheCategory(String drawableOfTheCategory) {
        this.drawableOfTheCategory = drawableOfTheCategory;
    }

    public String getNameOfTheProduct() {
        return nameOfTheProduct;
    }

    public void setNameOfTheProduct(String nameOfTheProduct) {
        this.nameOfTheProduct = nameOfTheProduct;
    }

    public String getMrpOfTheProduct() {
        return mrpOfTheProduct;
    }

    public void setMrpOfTheProduct(String mrpOfTheProduct) {
        this.mrpOfTheProduct = mrpOfTheProduct;
    }

    public String getDiscountedPriceOfTheProduct() {
        return discountedPriceOfTheProduct;
    }

    public void setDiscountedPriceOfTheProduct(String discountedPriceOfTheProduct) {
        this.discountedPriceOfTheProduct = discountedPriceOfTheProduct;
    }

    public String getOfferDiscount() {
        return offerDiscount;
    }

    public void setOfferDiscount(String offerDiscount) {
        this.offerDiscount = offerDiscount;
    }
}
