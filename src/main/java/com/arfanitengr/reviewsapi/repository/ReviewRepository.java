package com.arfanitengr.reviewsapi.repository;

import com.arfanitengr.reviewsapi.entity.Product;
import com.arfanitengr.reviewsapi.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    List<Review> findAllByProduct(Product product);
}
