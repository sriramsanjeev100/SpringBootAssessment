package org.example.studentcourse.repository;

import org.example.studentcourse.entity.Course;
import org.example.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>
{
    List<Student> findByCourse(Course course);
}