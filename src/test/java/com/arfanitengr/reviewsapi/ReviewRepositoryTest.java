package com.arfanitengr.reviewsapi;

import com.arfanitengr.reviewsapi.entity.Product;
import com.arfanitengr.reviewsapi.entity.Review;
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
public class ReviewRepositoryTest {
    // inject components for testing
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository repository;

    // check do injected components are not null
    @Test
    public void doInjectedComponentsAreNotNull() {
        assertThat(productRepository).isNotNull();
        assertThat(repository).isNotNull();
    }

    @After("")
    public void cleanUp() {
        productRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    public void testCreateReviewForAProduct() {
        assertThat(productRepository.findAll()).isEmpty();
        assertThat(repository.findAll()).isEmpty();

        Review review = getReview();
        Product p = productRepository.save(getProduct());

        review.setProduct(p);

        Review actual = repository.save(review);
        assertThat(actual.getId()).isGreaterThan(0);
        assertThat(actual.getRating()).isEqualTo(4);
        assertThat(actual.getProduct().getId()).isEqualTo(p.getId());
    }



    @Test
    public void testReadAllReviewsForAProduct() {
        assertThat(productRepository.findAll()).isEmpty();
        assertThat(repository.findAll()).isEmpty();

        Review review1 = getReview();
        Review review2 = getReview2();
        Review review3 = getReview3();

        Product p1 = productRepository.save(getProduct());
        Product p2 = productRepository.save(getProduct2());

        review1.setProduct(p1);
        review2.setProduct(p1);

        review3.setProduct(p2);

        repository.save(review1);
        repository.save(review2);
        repository.save(review3);



        List<Review> actual = repository.findAllByProduct(p1);
        assertThat(actual).hasSize(2);
    }

    private Review getReview() {
        Review review = new Review();
        review.setRating(4);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return review;
    }

    private Review getReview2() {
        Review review = new Review();
        review.setRating(5);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return review;
    }

    private Review getReview3() {
        Review review = new Review();
        review.setRating(1);
        review.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
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
