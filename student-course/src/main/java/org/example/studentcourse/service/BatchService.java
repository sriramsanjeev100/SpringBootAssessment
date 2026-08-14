package org.example.studentcourse.service;

import org.example.studentcourse.dto.request.BatchRequestDto;
import org.example.studentcourse.dto.response.BatchCourseResponse;
import org.example.studentcourse.entity.Batch;
import org.example.studentcourse.entity.Course;
import org.example.studentcourse.repository.BatchRepository;
import org.example.studentcourse.repository.CourseRepository;
import org.example.studentcourse.dto.response.BatchResponse;
import org.example.studentcourse.dto.response.CourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService
{
    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;

    public BatchService(BatchRepository batchRepository, CourseRepository courseRepository)
    {
        this.batchRepository = batchRepository;
        this.courseRepository = courseRepository;
    }

    public BatchResponse addBatch(BatchRequestDto dto)
    {
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Batch batch = new Batch();
        batch.setBatchName(dto.batchName());
        batch.setStartDate(dto.startDate());
        batch.setEndDate(dto.endDate());
        batch.setStartTime(dto.startTime());
        batch.setEndTime(dto.endTime());
        batch.setMode(dto.mode());
        batch.setCourse(course);
        Batch savedBatch = batchRepository.save(batch);
        return mapToResponse(savedBatch);
    }

    public List<BatchResponse> getAllBatches()
    {
        return batchRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BatchResponse getBatchById(int id)
    {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        return mapToResponse(batch);
    }

    public void deleteBatch(int id)
    {
        batchRepository.deleteById(id);
    }

    private BatchResponse mapToResponse(Batch batch)
    {
        Course course = batch.getCourse();
        BatchCourseResponse courseResponse = null;
        if (course != null)
        {
            courseResponse = new BatchCourseResponse(course.getId(), course.getCourseName());
        }

        return new BatchResponse(batch.getId(), batch.getBatchName(), batch.getStartDate(), batch.getEndDate(), batch.getStartTime(), batch.getEndTime(), batch.getMode(), courseResponse);
    }
}