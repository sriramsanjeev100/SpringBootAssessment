package org.example.studentcourse.controller;

import org.example.studentcourse.dto.CourseRequestDto;
import org.example.studentcourse.response.CourseResponse;
import org.example.studentcourse.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public CourseResponse addCourse(@RequestBody CourseRequestDto dto)
    {
        return courseService.addCourse(dto);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses()
    {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable int id)
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