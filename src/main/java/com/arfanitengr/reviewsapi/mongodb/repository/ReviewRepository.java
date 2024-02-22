package com.arfanitengr.reviewsapi.mongodb.repository;

import com.arfanitengr.reviewsapi.mongodb.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByProductId(Integer productId);
}
