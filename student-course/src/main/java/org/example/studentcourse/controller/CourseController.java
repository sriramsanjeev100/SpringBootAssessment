package org.example.studentcourse.controller;

import jakarta.validation.Valid;
import org.example.studentcourse.dto.request.CourseRequestDto;
import org.example.studentcourse.dto.response.CourseResponse;
import org.example.studentcourse.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CourseResponse> addCourse(@Valid @RequestBody CourseRequestDto dto)
    {
        CourseResponse response = courseService.addCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses()
    {
        List<CourseResponse> response = courseService.getAllCourses();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable int id)
    {
        CourseResponse response = courseService.getCourseById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id)
    {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}