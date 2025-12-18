-- =========================
-- MORE USERS
-- =========================
INSERT INTO users (username, email, password) VALUES
                                                  ('Aruzhan', 'aruzhan@cats.kz', '$2a$10$userhash'),
                                                  ('Dias', 'dias@cats.kz', '$2a$10$userhash'),
                                                  ('Aigerim', 'aigerim@cats.kz', '$2a$10$userhash'),
                                                  ('Nurlan', 'nurlan@cats.kz', '$2a$10$userhash');

-- =========================
-- USER PERMISSIONS (all USER)
-- =========================
INSERT INTO user_permissions (user_id, permission_id) VALUES
                                                          (3, 2),
                                                          (4, 2),
                                                          (5, 2),
                                                          (6, 2);

-- =========================
-- MORE CATS
-- =========================
INSERT INTO cats (name, breed, age, price, image_url) VALUES
                                                          ('Tom', 'Persian', 4, 200000, 'https://placekitten.com/304/304'),
                                                          ('Simba', 'Bengal', 2, 300000, 'https://placekitten.com/305/305'),
                                                          ('Oscar', 'Sphynx', 3, 280000, 'https://placekitten.com/306/306'),
                                                          ('Milo', 'Abyssinian', 1, 220000, 'https://placekitten.com/307/307'),
                                                          ('Chloe', 'Ragdoll', 2, 260000, 'https://placekitten.com/308/308'),
                                                          ('Jack', 'Russian Blue', 5, 210000, 'https://placekitten.com/309/309'),
                                                          ('Bella', 'Norwegian Forest', 3, 290000, 'https://placekitten.com/310/310'),
                                                          ('Rocky', 'American Shorthair', 4, 230000, 'https://placekitten.com/311/311');

-- =========================
-- CARTS FOR NEW USERS
-- =========================
INSERT INTO cart (user_id) VALUES
                               (3),
                               (4),
                               (5),
                               (6);

-- =========================
-- CART ITEMS
-- =========================
INSERT INTO cart_item (cart_id, cat_id, quantity) VALUES
                                                      (2, 3, 1),
                                                      (2, 4, 1),

                                                      (3, 5, 2),
                                                      (3, 6, 1),

                                                      (4, 7, 1),
                                                      (4, 8, 2),

                                                      (5, 9, 1),
                                                      (5, 10, 1);

-- =========================
-- MORE ORDERS
-- =========================
INSERT INTO orders (user_id, total_price, status, created_at) VALUES
                                                                  (3, 500000, 'PAID', NOW()),
                                                                  (4, 430000, 'DELIVERED', NOW()),
                                                                  (5, 290000, 'CANCELED', NOW()),
                                                                  (6, 760000, 'PAID', NOW());

-- =========================
-- ORDER ITEMS
-- =========================
INSERT INTO order_item (cat_id, quantity, price) VALUES
                                                     (3, 1, 250000),
                                                     (4, 1, 250000),

                                                     (5, 2, 520000),

                                                     (6, 1, 210000),
                                                     (7, 1, 220000),

                                                     (8, 2, 460000);

-- =========================
-- LINK ORDERS <-> ITEMS
-- =========================
INSERT INTO orders_items (order_id, items_id) VALUES
                                                  (2, 3),
                                                  (2, 4),

                                                  (3, 5),

                                                  (4, 6),
                                                  (4, 7),

                                                  (5, 8);

-- =========================
-- PAYMENTS
-- =========================
INSERT INTO payment (order_id, amount, status, payment_date) VALUES
                                                                 (2, 500000, 'PAID', NOW()),
                                                                 (3, 430000, 'PAID', NOW()),
                                                                 (4, 290000, 'FAILED', NOW()),
                                                                 (5, 760000, 'PAID', NOW());
