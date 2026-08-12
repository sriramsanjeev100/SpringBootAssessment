package org.example.studentcourse.controller;

import org.example.studentcourse.dto.CourseRequestDto;
import org.example.studentcourse.entity.Course;
import org.example.studentcourse.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController
{
    private final CourseService courseService;
    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }

    @PostMapping
    public Course addCourse(@RequestBody CourseRequestDto dto)
    {
        return courseService.addCourse(dto);
    }

    @GetMapping
    public List<Course> getAllCourses()
    {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id)
    {
        return courseService.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id)
    {
        courseService.deleteCourse(id);
        return "Course deleted successfully";
    }
}