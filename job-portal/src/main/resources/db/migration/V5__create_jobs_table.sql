CREATE TABLE job
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    salary DOUBLE,
    location VARCHAR(255) NOT NULL,
    experience INT NOT NULL,
    posted_date DATETIME NOT NULL,
    last_date DATETIME NOT NULL,
    employer_id BINARY(16) NOT NULL,
    CONSTRAINT fk_job_employer FOREIGN KEY (employer_id) REFERENCES employer(id)
);