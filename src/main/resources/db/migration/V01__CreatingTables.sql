CREATE TABLE products (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    unit_price DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reviews (
    id INT AUTO_INCREMENT NOT NULL,
    rating INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE comments (
    id INT AUTO_INCREMENT NOT NULL,
    visitor_name VARCHAR(255) NOT NULL,
    comm_text VARCHAR(1000) NOT NULL,
    review_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (review_id) REFERENCES reviews(id)
);
