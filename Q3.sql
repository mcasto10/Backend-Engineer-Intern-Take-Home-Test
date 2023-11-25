-- Create a database named TestDataBase
CREATE DATABASE TestDataBase;

-- Switch to the TestDataBase
USE TestDataBase;

-- Create the 'product' table
CREATE TABLE product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    added_by VARCHAR(100) NOT NULL
);

-- Create the 'product_price' table
CREATE TABLE product_price (
    price_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    price DECIMAL(10, 2) NOT NULL,
    discount_percent DECIMAL(5, 2) DEFAULT 0,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(100) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Create the 'product_price_change_log' table
CREATE TABLE product_price_change_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    old_price DECIMAL(10, 2),
    old_discount_percent DECIMAL(5, 2),
    new_price DECIMAL(10, 2),
    new_discount_percent DECIMAL(5, 2),
    operation_type ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    operation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    performed_by VARCHAR(100) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Query to join 'product' and 'product_price' tables
SELECT
    p.name AS product_name,
    p.category AS product_category,
    pp.price AS product_price,
    pp.updated_by AS updated_by,
    pp.updated_time AS updated_time
FROM
    product p
JOIN
    product_price pp ON p.product_id = pp.product_id;
