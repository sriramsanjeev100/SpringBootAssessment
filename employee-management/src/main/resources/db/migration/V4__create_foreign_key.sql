ALTER TABLE employee
DROP COLUMN department,
ADD COLUMN dept_id INT,
ADD CONSTRAINT fk_employee_department
FOREIGN KEY (dept_id)
REFERENCES department(id);