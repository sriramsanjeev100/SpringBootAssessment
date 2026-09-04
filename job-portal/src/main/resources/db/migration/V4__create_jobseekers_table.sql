CREATE TABLE job_seeker
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    experience INT NOT NULL,
    resume VARCHAR(500),
    user_id BINARY(16) NOT NULL UNIQUE,
    CONSTRAINT fk_job_seeker_user FOREIGN KEY (user_id) REFERENCES users(id)
);