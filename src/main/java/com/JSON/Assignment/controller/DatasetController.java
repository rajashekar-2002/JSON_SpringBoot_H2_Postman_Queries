package com.JSON.Assignment.controller;

import com.JSON.Assignment.service.DatasetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dataset")
public class DatasetController {

    private final DatasetService service;

    public DatasetController(DatasetService service) {
        this.service = service;
    }

    @PostMapping("/{datasetName}/record")
    public ResponseEntity<?> addRecord(
            @PathVariable String datasetName,
            @RequestBody Map<String, Object> body) {
        Long id = service.saveRecord(datasetName, body);

        return ResponseEntity.ok(Map.of(
                "message", "Record added successfully",
                "dataset", datasetName,
                "recordId", id));
    }

    @GetMapping("/{datasetName}/query")
    public ResponseEntity<?> query(
            @PathVariable String datasetName,
            @RequestParam(required = false) String groupBy,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order) {
        Object result = service.queryData(datasetName, groupBy, sortBy, order);

        if (groupBy != null) {
            return ResponseEntity.ok(Map.of("groupedRecords", result));
        }

        if (sortBy != null) {
            return ResponseEntity.ok(Map.of("sortedRecords", result));
        }

        return ResponseEntity.ok(result);
    }
}
