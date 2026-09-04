CREATE TABLE job_skills
(
    job_id BINARY(16) NOT NULL,
    skill_id BINARY(16) NOT NULL,
    PRIMARY KEY (job_id, skill_id),
    CONSTRAINT fk_job_skills_job
    FOREIGN KEY (job_id) REFERENCES job(id),
    CONSTRAINT fk_job_skills_skill
    FOREIGN KEY (skill_id) REFERENCES skill(id)
);