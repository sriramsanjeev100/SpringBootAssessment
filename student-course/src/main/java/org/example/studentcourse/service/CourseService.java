package org.example.studentcourse.service;

import org.example.studentcourse.dto.request.CourseRequestDto;
import org.example.studentcourse.entity.Course;
import org.example.studentcourse.entity.Skill;
import org.example.studentcourse.repository.CourseRepository;
import org.example.studentcourse.repository.SkillRepository;
import org.example.studentcourse.dto.response.CourseResponse;
import org.example.studentcourse.dto.response.SkillResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService
{
    private final CourseRepository courseRepository;
    private final SkillRepository skillRepository;

    public CourseService(CourseRepository courseRepository, SkillRepository skillRepository)
    {
        this.courseRepository = courseRepository;
        this.skillRepository = skillRepository;
    }

    public CourseResponse addCourse(CourseRequestDto dto)
    {
        Course course = new Course();
        course.setCourseName(dto.courseName());
        List<Skill> skills = new ArrayList<>();
        for (String skillName : dto.skills())
        {
            Skill skill = skillRepository.findBySkillName(skillName)
                    .orElseGet(() -> {
                        Skill newSkill = new Skill();
                        newSkill.setSkillName(skillName);
                        return skillRepository.save(newSkill);
                    });

            skills.add(skill);
        }
        course.setSkills(skills);
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

    // ENTITY → RESPONSE
    private CourseResponse mapToResponse(Course course) {

        List<SkillResponse> skillResponses = course.getSkills().stream()
                        .map(skill -> new SkillResponse(skill.getId(), skill.getSkillName()))
                        .toList();

        return new CourseResponse(course.getId(), course.getCourseName(), skillResponses);
    }
}