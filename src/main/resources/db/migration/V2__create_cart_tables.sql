CREATE TABLE carts (
                       id UUID PRIMARY KEY,
                       user_id VARCHAR(255) NOT NULL,
                       total_price DECIMAL(19, 2) NOT NULL,
                       CONSTRAINT uk_carts_user_id UNIQUE (user_id)
);

CREATE TABLE cart_items (
                            id UUID PRIMARY KEY,
                            cart_id UUID NOT NULL,
                            product_id UUID NOT NULL,
                            product_name VARCHAR(255) NOT NULL,
                            product_price DECIMAL(19, 2) NOT NULL,
                            quantity INT NOT NULL,
                            subtotal DECIMAL(19, 2) NOT NULL,
                            CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id) REFERENCES carts (id)
);

CREATE INDEX idx_cart_items_cart_id ON cart_items (cart_id);