-- User table
    CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- VHS table
CREATE TABLE IF NOT EXISTS vhs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    release_year INT NOT NULL
);

-- Rental table
CREATE TABLE IF NOT EXISTS rental (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    vhs_id BIGINT NOT NULL,
    rental_date DATE NOT NULL,
    due_date DATE NOT NULL,
    late_fee DOUBLE,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (vhs_id) REFERENCES vhs(id)
);
