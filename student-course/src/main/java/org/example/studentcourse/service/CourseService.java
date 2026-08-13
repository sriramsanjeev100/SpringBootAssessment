package org.example.studentcourse.service;

import org.example.studentcourse.dto.CourseRequestDto;
import org.example.studentcourse.entity.Course;
import org.example.studentcourse.repository.CourseRepository;
import org.example.studentcourse.response.CourseResponse;
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

    public CourseResponse addCourse(CourseRequestDto dto)
    {
        Course course = new Course();
        course.setCourseName(dto.courseName());
        Course savedCourse = courseRepository.save(course);
        return mapToResponse(savedCourse);
    }

    public List<CourseResponse> getAllCourses()
    {
        return courseRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CourseResponse getCourseById(int id)
    {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return mapToResponse(course);
    }

    public void deleteCourse(int id)
    {
        courseRepository.deleteById(id);
    }

    private CourseResponse mapToResponse(Course course)
    {
        return new CourseResponse(course.getId(), course.getCourseName());
    }
}