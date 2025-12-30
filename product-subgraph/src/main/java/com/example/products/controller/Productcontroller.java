package com.example.products.controller;

import com.example.products.model.Product;
import com.example.products.model.User;
import com.example.products.service.ProductService;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class Productcontroller {

    private final ProductService productService;

    public Productcontroller(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> allProducts() {
        return productService.findAll();
    }

    @EntityMapping
    public User user(@Argument Long userId) { return new User(userId);}

    @EntityMapping
    public Product product(@Argument String productId) {return productService.findById(productId);}

    @SchemaMapping(typeName = "User", field = "products")
    public List<Product> products(User user) {
        return productService.forUser(user.getUserId());
    }


/// ////////
    /*@QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }

   @EntityMapping
    @QueryMapping
    public Product productById(@Argument String productId) {
        return productService.findById(productId);
    }

    @EntityMapping
    public Product product(@Argument String productId) {
        return productService.findById(productId);
    }*/
 }
