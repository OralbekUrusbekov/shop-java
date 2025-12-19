-- =========================
-- USERS & PERMISSIONS
-- =========================

CREATE TABLE t_permission (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE user_permissions (
                                  user_id BIGINT NOT NULL,
                                  permission_id BIGINT NOT NULL,
                                  PRIMARY KEY (user_id, permission_id),
                                  CONSTRAINT fk_user_permissions_user
                                      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                  CONSTRAINT fk_user_permissions_permission
                                      FOREIGN KEY (permission_id) REFERENCES t_permission(id) ON DELETE CASCADE
);

-- =========================
-- CATS
-- =========================

CREATE TABLE cats (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255),
                      breed VARCHAR(255),
                      age INTEGER,
                      price DOUBLE PRECISION,
                      image_url VARCHAR(500)
);

-- =========================
-- CART & CART ITEMS
-- =========================

CREATE TABLE cart (
                      id BIGSERIAL PRIMARY KEY,
                      user_id BIGINT UNIQUE,
                      CONSTRAINT fk_cart_user
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE cart_item (
                           id BIGSERIAL PRIMARY KEY,
                           cart_id BIGINT NOT NULL,
                           cat_id BIGINT NOT NULL,
                           quantity INTEGER NOT NULL,
                           CONSTRAINT fk_cart_item_cart
                               FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
                           CONSTRAINT fk_cart_item_cat
                               FOREIGN KEY (cat_id) REFERENCES cats(id)
);

-- =========================
-- ORDERS & ORDER ITEMS
-- =========================

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT,
                        total_price DOUBLE PRECISION,
                        status VARCHAR(50),
                        created_at TIMESTAMP,
                        CONSTRAINT fk_order_user
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_item (
                            id BIGSERIAL PRIMARY KEY,
                            cat_id BIGINT NOT NULL,
                            quantity INTEGER NOT NULL,
                            price DOUBLE PRECISION,
                            CONSTRAINT fk_order_item_cat
                                FOREIGN KEY (cat_id) REFERENCES cats(id)
);

CREATE TABLE orders_items (
                              order_id BIGINT NOT NULL,
                              items_id BIGINT NOT NULL,
                              PRIMARY KEY (order_id, items_id),
                              CONSTRAINT fk_orders_items_order
                                  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                              CONSTRAINT fk_orders_items_item
                                  FOREIGN KEY (items_id) REFERENCES order_item(id) ON DELETE CASCADE
);

-- =========================
-- PAYMENT
-- =========================

CREATE TABLE payment (
                         id BIGSERIAL PRIMARY KEY,
                         order_id BIGINT UNIQUE,
                         amount DOUBLE PRECISION,
                         status VARCHAR(50),
                         payment_date TIMESTAMP,
                         CONSTRAINT fk_payment_order
                             FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);