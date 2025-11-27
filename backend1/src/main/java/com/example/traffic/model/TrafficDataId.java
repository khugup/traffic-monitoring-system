package com.example.traffic.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class TrafficDataId implements Serializable {
    private String detectorName;
    private String roadName;

    @Column(name = "date_time")
    private Date date;



    public TrafficDataId() {}

    public TrafficDataId(String detectorName, String roadName, Date date) {
        this.detectorName = detectorName;
        this.roadName = roadName;
        this.date = date;
    }

    public String getDetectorName() {
        return detectorName;
    }

    public void setDetectorName(String detectorName) {
        this.detectorName = detectorName;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrafficDataId)) return false;
        TrafficDataId that = (TrafficDataId) o;
        return Objects.equals(detectorName, that.detectorName) &&
               Objects.equals(roadName, that.roadName) &&
               Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detectorName, roadName, date);
    }
}
