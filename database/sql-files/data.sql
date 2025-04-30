DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `loans`;
DROP TABLE IF EXISTS `customers`;

CREATE TABLE customers (
    id CHAR(36) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    second_last_name VARCHAR(100) NULL,
    date_of_birth DATE NOT NULL,
    credit_line_amount DOUBLE NOT NULL,
    available_credit_line_amount DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);  

CREATE TABLE loans (
    id CHAR(36) PRIMARY KEY,
    customer_id CHAR(36) NOT NULL,
    amount DOUBLE NOT NULL CHECK (amount > 0),
    status ENUM('ACTIVE', 'LATE', 'COMPLETED') NOT NULL,
    commission_amount DOUBLE NOT NULL CHECK (commission_amount > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE installments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    loan_id CHAR(36) NOT NULL,
    amount DOUBLE NOT NULL CHECK (amount > 0),
    scheduled_payment_date DATE NOT NULL,
    status ENUM('NEXT', 'PENDING', 'ERROR') NOT NULL,
    FOREIGN KEY (loan_id) REFERENCES loans(id)
);
