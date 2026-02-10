package com.JSON.Assignment.service;

import com.JSON.Assignment.model.DatasetRecord;
import com.JSON.Assignment.repository.DatasetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatasetService {

    private final DatasetRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DatasetService(DatasetRepository repository) {
        this.repository = repository;
    }

    public Long saveRecord(String datasetName, Map<String, Object> data) {
        try {
            String json = objectMapper.writeValueAsString(data);
            DatasetRecord record = new DatasetRecord(datasetName, json);
            return repository.save(record).getId();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON data");
        }
    }

    public Object queryData(String datasetName, String groupBy, String sortBy, String order) {
        List<Map<String, Object>> records = repository
                .findByDatasetName(datasetName)
                .stream()
                .map(this::toMap)
                .toList();

        if (groupBy != null) {
            return records.stream()
                    .filter(r -> r.containsKey(groupBy))
                    .collect(Collectors.groupingBy(
                            r -> String.valueOf(r.get(groupBy))));
        }

        if (sortBy != null) {
            Comparator<Map<String, Object>> comparator = Comparator.comparing(
                    r -> (Comparable) r.get(sortBy),
                    Comparator.nullsLast(Comparator.naturalOrder()));

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }

            return records.stream().sorted(comparator).toList();
        }

        return records;
    }

    private Map<String, Object> toMap(DatasetRecord record) {
        try {
            return objectMapper.readValue(record.getJsonData(), Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read stored data");
        }
    }
}
