CREATE TABLE product (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(150) NOT NULL,

    description VARCHAR(500),

    price DECIMAL(10,2) NOT NULL,

    stock INT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL

);