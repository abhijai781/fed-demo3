package com.example.products.service;

import com.example.products.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    static List<Product> productStores = List.of(
            new Product("p1", "Product1"),
            new Product("p2", "Product2"),
            new Product("p3", "Product3")
    );

      static List<Product> stores = List.of(
              new Product("p1", "Product1", 1l),
              new Product("p1", "Product1", 2l),
              new Product("p2", "Product1", 1l),
              new Product("p2", "Product2", 2l),
              new Product("p3", "Product3",2l)
      );

      public Product findById(String productId) {
          return productStores.stream()
                  .filter(p -> p.getProductId().equals(productId))
                  .findFirst().orElse(null);
      }

      public List<Product> findAll() {
          return stores;
      }

      public List<Product> forUser(Long userId) {
          return stores.stream()
                  .filter(p -> Objects.equals(p.getUserId(), userId))
                  .collect(Collectors.toList());
      }
}
