package com.arfanitengr.reviewsapi;

import com.arfanitengr.reviewsapi.entity.Comment;
import com.arfanitengr.reviewsapi.entity.Product;
import com.arfanitengr.reviewsapi.entity.Review;
import com.arfanitengr.reviewsapi.repository.CommentRepository;
import com.arfanitengr.reviewsapi.repository.ProductRepository;
import com.arfanitengr.reviewsapi.repository.ReviewRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentRepositoryTest {
    // inject components for testing

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;

    // check do injected components are not null
    @Test
    public void doInjectedComponentsAreNotNull() {
        assertThat(reviewRepository).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @After("")
    public void cleanUp() {
        productRepository.deleteAll();
        reviewRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    public void testCreateCommentForAReview() {
        assertThat(productRepository.findAll()).isEmpty();
        assertThat(reviewRepository.findAll()).isEmpty();
        assertThat(commentRepository.findAll()).isEmpty();

        Comment comment1 = getComment1();
        Review r1 = reviewRepository.save(getReview());

        comment1.setReview(r1);

        Comment actual = commentRepository.save(comment1);
        assertThat(actual.getId()).isGreaterThan(0);
        assertThat(actual.getVisitorName()).isEqualTo("Arfan");
        assertThat(actual.getCommText()).isEqualTo("Comment1 about review1");
        assertThat(actual.getReview().getId()).isEqualTo(r1.getId());
    }



    @Test
    public void testReadAllCommentsForAReview() {
        assertThat(productRepository.findAll()).isEmpty();
        assertThat(reviewRepository.findAll()).isEmpty();
        assertThat(commentRepository.findAll()).isEmpty();

        Comment comment1 = getComment1();
        Comment comment2 = getComment2();
        Comment comment3 = getComment3();

        Review r1 = reviewRepository.save(getReview());
        Review r2 = reviewRepository.save(getReview2());

        comment1.setReview(r1);
        comment2.setReview(r2);

        comment3.setReview(r1);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);



        List<Comment> actual = commentRepository.findAllByReview(r1);
        assertThat(actual).hasSize(2);
    }

    private Comment getComment1() {
        Comment comment = new Comment();
        comment.setVisitorName("Arfan");
        comment.setCommText("Comment1 about review1");
        return comment;
    }

    private Comment getComment2() {
        Comment comment = new Comment();
        comment.setVisitorName("Ali");
        comment.setCommText("Comment2 about review2");
        return comment;
    }

    private Comment getComment3() {
        Comment comment = new Comment();
        comment.setVisitorName("Jawwad");
        comment.setCommText("Comment3 about review1");
        return comment;
    }

    private Review getReview() {
        Review review = new Review();
        review.setRating(4);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        Product p = productRepository.save(getProduct());

        review.setProduct(p);
        return review;
    }

    private Review getReview2() {
        Review review = new Review();
        review.setRating(5);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        Product p = productRepository.save(getProduct2());

        review.setProduct(p);
        return review;
    }

    private Product getProduct() {
        Product product = new Product();
        product.setName("Mouse");
        product.setUnitPrice(250.50);
        return product;
    }

    private Product getProduct2() {
        Product product = new Product();
        product.setName("Keyboard");
        product.setUnitPrice(500.75);
        return product;
    }

}
