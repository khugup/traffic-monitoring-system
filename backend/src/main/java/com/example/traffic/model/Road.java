package com.example.traffic.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "roads")
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String roadName;

    @Column(nullable = false)
    private String detectorName;

    @Column(nullable = false)
    private Boolean detectorStatus;

    public Road() {}

    public Road(Integer id, String roadName, String detectorName, Boolean detectorStatus) {
        this.id = id;
        this.roadName = roadName;
        this.detectorName = detectorName;
        this.detectorStatus = detectorStatus;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRoadName() { return roadName; }
    public void setRoadName(String roadName) { this.roadName = roadName; }

    public String getDetectorName() { return detectorName; }
    public void setDetectorName(String detectorName) { this.detectorName = detectorName; }

    public Boolean getDetectorStatus() { return detectorStatus; }
    public void setDetectorStatus(Boolean detectorStatus) { this.detectorStatus = detectorStatus; }

    // Computed field for status (UP/DOWN)
    @Transient
    @JsonProperty("status")   // ensures Angular receives this in JSON
    public String getStatus() {
        return Boolean.TRUE.equals(detectorStatus) ? "UP" : "DOWN";
    }

    @Override
    public String toString() {
        return "Road{" +
                "id=" + id +
                ", roadName='" + roadName + '\'' +
                ", detectorName='" + detectorName + '\'' +
                ", detectorStatus=" + detectorStatus +
                '}';
    }
}
