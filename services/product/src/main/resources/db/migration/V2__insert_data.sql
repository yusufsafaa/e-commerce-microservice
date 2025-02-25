INSERT INTO category (id, description, name) VALUES
 (NEXTVAL('category_seq'), 'Electronics and gadgets', 'Electronics'),
 (NEXTVAL('category_seq'), 'Home and Kitchen appliances', 'Home & Kitchen'),
 (NEXTVAL('category_seq'), 'Clothing and fashion items', 'Fashion'),
 (NEXTVAL('category_seq'), 'Books and Stationery', 'Books'),
 (NEXTVAL('category_seq'), 'Health and Beauty products', 'Health & Beauty');


INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
(NEXTVAL('product_seq'), 'Latest iPhone model', 'iPhone 14', 100, 999.99, (SELECT id FROM category WHERE name = 'Electronics')),
(NEXTVAL('product_seq'), '55-inch 4K Smart TV', 'Samsung TV', 50, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
(NEXTVAL('product_seq'), 'Premium quality blender', 'KitchenAid Blender', 75, 129.99, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
(NEXTVAL('product_seq'), 'Men''s cotton t-shirt', 'Nike T-shirt', 200, 19.99, (SELECT id FROM category WHERE name = 'Fashion')),
(NEXTVAL('product_seq'), 'Best-selling science fiction novel', 'Dune by Frank Herbert', 150, 14.99, (SELECT id FROM category WHERE name = 'Books')),
(NEXTVAL('product_seq'), 'Moisturizing face cream', 'Neutrogena Face Cream', 120, 24.99, (SELECT id FROM category WHERE name = 'Health & Beauty'));

