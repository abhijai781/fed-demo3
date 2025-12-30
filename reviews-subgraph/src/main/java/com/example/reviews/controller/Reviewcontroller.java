package com.example.reviews.controller;


import com.example.reviews.model.Product;
import com.example.reviews.model.Review;
import com.example.reviews.service.ReviewService;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Reviewcontroller {

    private final ReviewService reviewService;


    public Reviewcontroller(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @QueryMapping
    public List<Review> allReviews() {
        return reviewService.all();
    }

    //@QueryMapping
    @EntityMapping
    public Review review(@Argument String reviewId) {
        return reviewService.findById(reviewId);
    }

    @EntityMapping
    public Product product(@Argument String productId) {
        return new Product(productId);
    }

    @SchemaMapping(typeName = "Product", field = "reviews")
    //@EntityMapping
    public List<Review> reviews(Product product) {
        return reviewService.forProduct(product.getProductId());
    }
}
