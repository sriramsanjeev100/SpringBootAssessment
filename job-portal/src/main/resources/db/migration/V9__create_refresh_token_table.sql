CREATE TABLE refresh_token
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiry_date DATETIME NOT NULL,
    user_id BINARY(16) NOT NULL,

    CONSTRAINT uk_refresh_token_token UNIQUE (token),
    CONSTRAINT uk_refresh_token_user UNIQUE (user_id),
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users(id)
);