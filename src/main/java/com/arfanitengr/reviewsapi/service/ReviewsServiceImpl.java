package com.arfanitengr.reviewsapi.service;

import com.arfanitengr.reviewsapi.entity.Product;
import com.arfanitengr.reviewsapi.mongodb.model.Comment;
import com.arfanitengr.reviewsapi.mongodb.model.Review;
import com.arfanitengr.reviewsapi.mongodb.repository.ReviewRepository;
import com.arfanitengr.reviewsapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReviewsServiceImpl implements ReviewsService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public ResponseEntity<?> findById(Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<?> readAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        if (products != null) {
            return products;
        } else {
            throw new NullPointerException("Product list is null");
        }

    }

    @Override
    public ResponseEntity<?> createReviewForProduct(Integer productId, Review review) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()) {


            review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            review.setProductId(optional.get().getId());

            Review r = reviewRepository.save(review);
            return new ResponseEntity<>(r, HttpStatus.CREATED) ;
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<List<?>> readReviewsForProduct(Integer productId) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()) {
            List<Review> reviews = reviewRepository.findByProductId(optional.get().getId());
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<?> createCommentForReview(String reviewId, Comment comment) {
        Optional<Review> optional = reviewRepository.findById(reviewId);
        if (optional.isPresent()) {
            Review review = optional.get();

            List<Comment> comments = review.getComments();

            comments.add(comment);
            review.setComments(comments);
            reviewRepository.save(review);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public ResponseEntity<List<?>> readCommentsForReview(String reviewId) {
        Optional<Review> optional = reviewRepository.findById(reviewId);
        if (optional.isPresent()) {
            List<Comment> comments = optional.get().getComments();
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
