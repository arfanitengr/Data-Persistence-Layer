package com.arfanitengr.reviewsapi.repository;

import com.arfanitengr.reviewsapi.entity.Comment;
import com.arfanitengr.reviewsapi.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByReview(Review review);
}
