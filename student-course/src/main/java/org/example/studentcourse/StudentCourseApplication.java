package org.example.studentcourse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Student Management API",
                version = "1.0",
                description = "REST APIs for managing students"
        )
)


@SpringBootApplication
public class StudentCourseApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(StudentCourseApplication.class, args);
    }

}
