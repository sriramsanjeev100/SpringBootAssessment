CREATE TABLE user_profiles
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL UNIQUE,
    website VARCHAR(100)
);

CREATE TABLE users
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    profile_id BINARY(16),
    CONSTRAINT uc_users_profile UNIQUE (profile_id),
    CONSTRAINT fk_users_profile FOREIGN KEY (profile_id) REFERENCES user_profiles(id)
);

CREATE TABLE categories
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE posts
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(100) NOT NULL,
    created_date DATETIME(6) NOT NULL,
    user_id BINARY(16) NOT NULL,
    CONSTRAINT fk_posts_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE post_category
(
    post_id BINARY(16) NOT NULL,
    category_id BINARY(16) NOT NULL,
    PRIMARY KEY (post_id, category_id),
    CONSTRAINT fk_post_category_post FOREIGN KEY (post_id) REFERENCES posts(id),
    CONSTRAINT fk_post_category_category FOREIGN KEY (category_id) REFERENCES categories(id)
);