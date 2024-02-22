package com.arfanitengr.reviewsapi.mongodb;

import com.arfanitengr.reviewsapi.mongodb.model.Comment;
import com.arfanitengr.reviewsapi.mongodb.model.Review;
import com.arfanitengr.reviewsapi.mongodb.repository.ReviewRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository repository;

    @After("")
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void createReviewForProduct() {
        List<Review> reviews = repository.findAll();
        assertThat(reviews).isEmpty();

        Review review = new Review();
        review.setRating(4);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        review.setProductId(1);

        Review result = repository.save(review);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getRating()).isEqualTo(4);
        assertThat(result.getProductId()).isEqualTo(1);
        assertThat(repository.findById(result.getId()).get().toString()).isEqualTo(result.toString());

    }

    @Test
    public void readReviewsForProduct() {
        assertThat(repository.findAll()).isEmpty();

        repository.save(getReview());
        repository.save(getReview2());
        repository.save(getReview3());

        assertThat(repository.findByProductId(2)).hasSize(2);

    }

    @Test
    public void createCommentForReview() {
        List<Review> reviews = repository.findAll();
        assertThat(reviews).isEmpty();

        Review review = new Review();
        review.setRating(4);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        review.setProductId(1);

        Review inserted = repository.save(review);

        Review r = repository.findById(inserted.getId()).get();
        List<Comment> comments = r.getComments();

        comments.add(getComment());

        r.setComments(comments);
        Review updated = repository.save(r);

        assertThat(updated.getComments().get(0).getVisitorName()).isEqualTo("Arfan");
        assertThat(updated.getComments().get(0).getCommText()).isEqualTo("Rating 4 is fine");
    }

    @Test
    public void readCommentsForReview() {
        assertThat(repository.findAll()).isEmpty();

        Review review = repository.save(getReview());
        Review review2 = repository.save(getReview2());

        Review r = repository.findById(review.getId()).get();
        Review r2 = repository.findById(review2.getId()).get();

        List<Comment> comments = new ArrayList<>();
        List<Comment> comments2 = new ArrayList<>();

        comments.add(getComment());
        comments2.add(getComment2());
        comments2.add(getComment3());

        r.setComments(comments);
        r2.setComments(comments2);

        repository.save(r);
        repository.save(r2);


        List<Comment> comms = repository.findById(r2.getId()).get().getComments();
        assertThat(comms).hasSize(2);

    }


    private Review getReview() {
        Review review = new Review();
        review.setRating(2);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        review.setProductId(1);
        return review;
    }
    private Review getReview2() {
        Review review2 = new Review();
        review2.setRating(4);
        review2.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        review2.setProductId(2);
        return review2;
    }
    private Review getReview3() {
        Review review3 = new Review();
        review3.setRating(5);
        review3.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        review3.setProductId(2);
        return review3;
    }

    private Comment getComment() {
        Comment comment = new Comment();
        comment.setVisitorName("Ali");
        comment.setCommText("Comment about review having rating 2");
        return comment;
    }
    private Comment getComment2() {
        Comment comment = new Comment();
        comment.setVisitorName("Arfan");
        comment.setCommText("Arfan's comment about review having rating 4");
        return comment;
    }
    private Comment getComment3() {
        Comment comment = new Comment();
        comment.setVisitorName("Jawwad");
        comment.setCommText("Jawwad's comment about review having rating 4");
        return comment;
    }

}
