package com.JSON.Assignment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataset_records")
public class DatasetRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String datasetName;

    @Column(columnDefinition = "TEXT")
    private String jsonData;

    public DatasetRecord() {
    }

    public DatasetRecord(String datasetName, String jsonData) {
        this.datasetName = datasetName;
        this.jsonData = jsonData;
    }

    public Long getId() {
        return id;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public String getJsonData() {
        return jsonData;
    }
}
