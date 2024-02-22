package com.arfanitengr.reviewsapi;

import com.arfanitengr.reviewsapi.entity.Product;
import com.arfanitengr.reviewsapi.repository.ProductRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    // inject components for testing
    @Autowired
    private ProductRepository repository;

    // check do injected components are not null
    @Test
    public void doInjectedComponentsAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @After("")
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void testCreateProduct() {
        assertThat(repository.findAll()).isEmpty();

        Product actual = repository.save(getProduct());
        assertThat(actual.getId()).isGreaterThan(0);
        assertThat(actual.getName()).isEqualTo("Mouse");
        assertThat(actual.getUnitPrice()).isEqualTo(250.50);
    }

    @Test
    public void testFindById() {
        assertThat(repository.findAll()).isEmpty();

        Product product = repository.save(getProduct());
        Product actual = repository.findById(product.getId()).get();

        assertThat(actual.getId()).isEqualTo(product.getId());
        assertThat(actual.getName()).isEqualTo("Mouse");
        assertThat(actual.getUnitPrice()).isEqualTo(250.50);
    }

    @Test
    public void testFindAll() {
        assertThat(repository.findAll()).isEmpty();

        repository.save(getProduct());
        repository.save(getProduct2());

        List<Product> actual = (List<Product>) repository.findAll();
        assertThat(actual).hasSize(2);
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
