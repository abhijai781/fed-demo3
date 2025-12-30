package com.example.products.model;

public class Product {

    private String productId;
    private String productDescription;
    private Long userId;

    public Product() {
    }
    
    public Product(String productId, String productDescription) {
        this.productId = productId;
        this.productDescription = productDescription;
    }

    public Product(String productId, String productDescription, Long id) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.userId = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
