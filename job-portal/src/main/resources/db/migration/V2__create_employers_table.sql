CREATE TABLE employer
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    user_id BINARY(16) NOT NULL UNIQUE,
    CONSTRAINT fk_employer_user FOREIGN KEY (user_id) REFERENCES users(id)
);