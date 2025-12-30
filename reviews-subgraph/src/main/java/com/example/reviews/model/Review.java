package com.example.reviews.model;

public class Review {

    private String reviewId;
    private String productId;
    private String text;
    private int starRating;

    public Review(String reviewId, String productId, String text, int starRating) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.text = text;
        this.starRating = starRating;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }
}
