DROP TABLE IF EXISTS `customers`;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    second_last_name VARCHAR(100) NULL,
    date_of_birth DATE NOT NULL,
    credit_line_amount DOUBLE NOT NULL,
    available_credit_line_amount DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);