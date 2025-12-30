package com.example.reviews.service;

import com.example.reviews.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final List<Review> reviews = new ArrayList<>();

    public ReviewService() {
        reviews.add(new Review("r1", "p1", "Fantastic performance !", 5));
        reviews.add(new Review("r2", "p1", "Battery could be better.", 3));
        reviews.add(new Review("r3", "p2", "Great sound and ANC.", 4));
        reviews.add(new Review("r4", "p3", "Comfortable to wear, solid features.", 4));
    }

    public List<Review> forProduct(String productId) {
        return reviews.stream()
                .filter(r -> Objects.equals(r.getProductId(), productId))
                .collect(Collectors.toList());
    }
    public List<Review> all() {
        return Collections.unmodifiableList(reviews);
    }

    public Review findById(String reviewId) {
        return reviews.stream()
                .filter(r -> Objects.equals(r.getReviewId(), reviewId))
                .findFirst().orElse(null);
    }
}
