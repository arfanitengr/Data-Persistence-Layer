package com.arfanitengr.reviewsapi.service;

import com.arfanitengr.reviewsapi.entity.Product;
import com.arfanitengr.reviewsapi.mongodb.model.Comment;
import com.arfanitengr.reviewsapi.mongodb.model.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewsService {
    void createProduct(Product product);
    ResponseEntity<?> findById(Integer id);
    List<?> readAllProducts();

    ResponseEntity<?> createReviewForProduct(Integer productId, Review review);
    ResponseEntity<List<?>> readReviewsForProduct(Integer productId);

    ResponseEntity<?> createCommentForReview(String reviewId, Comment comment);
    ResponseEntity<List<?>> readCommentsForReview(String reviewId);
}
