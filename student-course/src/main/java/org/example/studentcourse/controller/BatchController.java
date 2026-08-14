package org.example.studentcourse.controller;

import org.example.studentcourse.dto.request.BatchRequestDto;
import org.example.studentcourse.dto.response.BatchResponse;
import org.example.studentcourse.service.BatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController
{
    private final BatchService batchService;
    public BatchController(BatchService batchService)
    {
        this.batchService = batchService;
    }

    @PostMapping
    public BatchResponse addBatch(@RequestBody BatchRequestDto dto)
    {
        return batchService.addBatch(dto);
    }

    @GetMapping
    public List<BatchResponse> getAllBatches()
    {
        return batchService.getAllBatches();
    }

    @GetMapping("/{id}")
    public BatchResponse getBatchById(@PathVariable int id)
    {
        return batchService.getBatchById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBatch(@PathVariable int id)
    {
        batchService.deleteBatch(id);
        return "Batch deleted successfully";
    }
}