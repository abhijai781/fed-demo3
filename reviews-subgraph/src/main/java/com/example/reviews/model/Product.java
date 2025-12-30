package com.example.reviews.model;

public class Product {

    private String productId;


    public Product() {
    }
    
    public Product(String productId) {
        this.productId = productId;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
