CREATE TABLE roles(
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(256),
    PRIMARY KEY (id)
);

CREATE TABLE users(
    id bigint NOT NULL AUTO_INCREMENT,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    full_name VARCHAR(256),
    country VARCHAR(256),
    city VARCHAR(256),
    street VARCHAR(256),
    phone VARCHAR(256),
    PRIMARY KEY (id)
);

CREATE TABLE user_roles(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE brand(
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(256),
    PRIMARY KEY (id)
);

CREATE TABLE type(
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(256),
    PRIMARY KEY (id)
);

CREATE TABLE product(
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(256),
    price DOUBLE,
    title VARCHAR(3000),
    description VARCHAR(10000),
    brand_id bigint,
    type_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (brand_id) REFERENCES brand (id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES type (id) ON DELETE CASCADE
);

CREATE TABLE cart(
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint,
    total_price DOUBLE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE cart_products(
    cart_id bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

CREATE TABLE wishlist(
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE wishlist_products(
    wishlist_id bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (wishlist_id, product_id),
    FOREIGN KEY (wishlist_id) REFERENCES wishlist (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

CREATE TABLE orders(
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint,
    price DOUBLE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE orders_products(
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(username, password) VALUES ('admin', '$2a$12$CNJLdrkxnaDHgGwWuCqdaec7L7hW305FR27XJIBlLhZQQndCuTnkq');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 3);

INSERT INTO cart(user_id, total_price) VALUES (1, 0);

INSERT INTO wishlist(user_id) VALUES (1);

INSERT INTO brand(name)
VALUES ('Lenovo'), ('Xiaomi'), ('Apple');

INSERT INTO type(name)
VALUES ('laptop'), ('smartphone'), ('headphones');

INSERT INTO product(name, price, title, description, brand_id, type_id)
VALUES ('Lenovo LEGION Y520', 1000, 'Lightweight, portable gaming PC, that easily plays your games.',
        'Powered by 7th Gen Intel® Core™ processors and processors up to NVIDIA® GeForce® GTX 1060 or
        AMD Radeon RX 560 discrete graphics, and featuring premium audio and optional dual drive storage
        for added speed, this i7 gaming laptop is ready for action.', 1, 1),
    ('Xiaomi Redmi Note 11', 450, 'Modern and cheap smartphone for all needs', 'Xiaomi Redmi Note 11SE comes
    with IPS LCD, 90Hz, 400 nits (typ), It offers a size of 6.5 inches, 102.0 cm2 (~83.7% screen-to-body ratio),
    It is suitable for browsing the internet, playing games & watching videos, It has a resolution of 1080 x
    2400 pixels, 20:9 ratio (~405 ppi density), It presents a high-end processor & graphics processor.', 2, 2),
    ('Xiaomi Redmi AirDots 2', 25, 'Wireless headphones with microphone', 'Fully support dual channel mode using
    left or right earphone independently at any time. Latest BT 5.0 technology for superior sound and ultra-fast
    pairing.', 2, 3);