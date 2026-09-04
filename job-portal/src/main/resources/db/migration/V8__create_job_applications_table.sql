CREATE TABLE job_application
(
    id BINARY(16) PRIMARY KEY NOT NULL,
    applied_date DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    job_seeker_id BINARY(16) NOT NULL,
    job_id BINARY(16) NOT NULL,
    CONSTRAINT fk_application_job_seeker
    FOREIGN KEY (job_seeker_id) REFERENCES job_seeker(id),
    CONSTRAINT fk_application_job
    FOREIGN KEY (job_id) REFERENCES job(id)
);