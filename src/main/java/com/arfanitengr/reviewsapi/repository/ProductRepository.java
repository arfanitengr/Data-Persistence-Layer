package com.arfanitengr.reviewsapi.repository;

import com.arfanitengr.reviewsapi.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
