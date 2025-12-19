INSERT INTO t_permission (name) VALUES
                                    ('USER'),
                                    ('ADMIN');



INSERT INTO users (username, email, password) VALUES
                                                  ('Aruzhan', 'aruzhan@cats.kz', '$2a$10$userhash'),
                                                  ('Dias', 'dias@cats.kz', '$2a$10$userhash'),
                                                  ('Aigerim', 'aigerim@cats.kz', '$2a$10$userhash'),
                                                  ('Nurlan', 'nurlan@cats.kz', '$2a$10$userhash'),
                                                  ('Alina', 'alina@cats.kz', '$2a$10$userhash'),
                                                  ('Serik', 'serik@cats.kz', '$2a$10$userhash');





INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, t_permission p
WHERE p.name = 'USER';




INSERT INTO cats (name, breed, age, price, image_url) VALUES
                                                          ('Tom', 'Persian', 4, 200000, 'https://placekitten.com/304/304'),
                                                          ('Simba', 'Bengal', 2, 300000, 'https://placekitten.com/305/305'),
                                                          ('Oscar', 'Sphynx', 3, 280000, 'https://placekitten.com/306/306'),
                                                          ('Milo', 'Abyssinian', 1, 220000, 'https://placekitten.com/307/307'),
                                                          ('Chloe', 'Ragdoll', 2, 260000, 'https://placekitten.com/308/308'),
                                                          ('Jack', 'Russian Blue', 5, 210000, 'https://placekitten.com/309/309'),
                                                          ('Bella', 'Norwegian Forest', 3, 290000, 'https://placekitten.com/310/310'),
                                                          ('Rocky', 'American Shorthair', 4, 230000, 'https://placekitten.com/311/311'),
                                                          ('Luna', 'Maine Coon', 2, 310000, 'https://placekitten.com/312/312'),
                                                          ('Leo', 'Siberian', 3, 270000, 'https://placekitten.com/313/313');




INSERT INTO cart (user_id)
SELECT id FROM users;




INSERT INTO cart_item (cart_id, cat_id, quantity)
SELECT c.id, cat.id, 1
FROM cart c
         JOIN cats cat ON cat.id % 3 = c.id % 3;




INSERT INTO orders (user_id, total_price, status, created_at)
SELECT u.id, 500000, 'PAID', NOW() FROM users u
UNION ALL
SELECT u.id, 430000, 'DELIVERED', NOW() FROM users u;




INSERT INTO order_item (cat_id, quantity, price)
SELECT c.id, 1, c.price
FROM cats c
WHERE c.id <= 10;




INSERT INTO orders_items (order_id, items_id)
SELECT o.id, oi.id
FROM orders o
         JOIN order_item oi ON oi.id % 5 = o.id % 5;




INSERT INTO payment (order_id, amount, status, payment_date)
SELECT o.id, o.total_price, 'PAID', NOW()
FROM orders o
WHERE o.status = 'PAID';

