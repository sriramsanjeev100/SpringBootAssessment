package org.example.studentcourse.controller;

import jakarta.validation.Valid;
import org.example.studentcourse.dto.request.BatchRequestDto;
import org.example.studentcourse.dto.response.BatchResponse;
import org.example.studentcourse.service.BatchService;
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
@RequestMapping("/api/batches")
public class BatchController
{
    private final BatchService batchService;
    public BatchController(BatchService batchService)
    {
        this.batchService = batchService;
    }

    @PostMapping
    public ResponseEntity<BatchResponse> addBatch(@Valid @RequestBody BatchRequestDto dto)
    {
        BatchResponse response = batchService.addBatch(dto);
        return ResponseEntity.status(HttpStatus.CREATED).header("X-Message", "Batch created").body(response);
    }

    @GetMapping
    public ResponseEntity<List<BatchResponse>> getAllBatches()
    {
        List<BatchResponse> response = batchService.getAllBatches();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchResponse> getBatchById(@PathVariable int id)
    {
        BatchResponse response = batchService.getBatchById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBatch(@PathVariable int id)
    {
        batchService.deleteBatch(id);
        return ResponseEntity.ok("Batch deleted successfully");
    }
}