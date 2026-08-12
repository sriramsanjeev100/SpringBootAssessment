package org.example.studentcourse.service;

import org.example.studentcourse.dto.CourseRequestDto;
import org.example.studentcourse.entity.Course;
import org.example.studentcourse.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService
{
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(CourseRequestDto dto)
    {
        Course course = new Course();
        course.setCourseName(dto.courseName());
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id)
    {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteCourse(int id)
    {
        courseRepository.deleteById(id);
    }
}