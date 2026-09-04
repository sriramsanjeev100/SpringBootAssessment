CREATE TABLE jobseeker_skills
(
    jobseeker_id BINARY(16) NOT NULL,
    skill_id BINARY(16) NOT NULL,
    PRIMARY KEY (jobseeker_id, skill_id),
    CONSTRAINT fk_jobseeker_skills_jobseeker
    FOREIGN KEY (jobseeker_id) REFERENCES job_seeker(id),
    CONSTRAINT fk_jobseeker_skills_skill
    FOREIGN KEY (skill_id) REFERENCES skill(id)
);