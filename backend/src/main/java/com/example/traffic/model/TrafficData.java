package com.example.traffic.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "traffic_data")
public class TrafficData {

	@EmbeddedId
	@AttributeOverrides({
	    @AttributeOverride(name = "detectorName", column = @Column(name = "detector_name")),
	    @AttributeOverride(name = "roadName", column = @Column(name = "road_name")),
	    @AttributeOverride(name = "date", column = @Column(name = "date_time"))
	})
	private TrafficDataId trafficDataId;


    private Integer classOneVolume;
    private Integer classTwoVolume;
    private Integer classThreeVolume;
    private Double averageSpeed;
    private Integer volume;
    private Integer period;
    private Double occupancyPercentage;

    @Enumerated(EnumType.STRING)
    private CongestionLevel congestionLevel;

    public TrafficDataId getTrafficDataId() {
		return trafficDataId;
	}

	public void setTrafficDataId(TrafficDataId trafficDataId) {
		this.trafficDataId = trafficDataId;
	}

	public Integer getClassOneVolume() {
		return classOneVolume;
	}

	public void setClassOneVolume(Integer classOneVolume) {
		this.classOneVolume = classOneVolume;
	}

	public Integer getClassTwoVolume() {
		return classTwoVolume;
	}

	public void setClassTwoVolume(Integer classTwoVolume) {
		this.classTwoVolume = classTwoVolume;
	}

	public Integer getClassThreeVolume() {
		return classThreeVolume;
	}

	public void setClassThreeVolume(Integer classThreeVolume) {
		this.classThreeVolume = classThreeVolume;
	}

	public Double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(Double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Double getOccupancyPercentage() {
		return occupancyPercentage;
	}

	public void setOccupancyPercentage(Double occupancyPercentage) {
		this.occupancyPercentage = occupancyPercentage;
	}

	public CongestionLevel getCongestionLevel() {
		return congestionLevel;
	}

	public void setCongestionLevel(CongestionLevel congestionLevel) {
		this.congestionLevel = congestionLevel;
	}

	public TrafficData() {}

    public TrafficData(TrafficDataId trafficDataId, Integer classOneVolume, Integer classTwoVolume,
                       Integer classThreeVolume, Double averageSpeed, Integer volume, Integer period,
                       Double occupancyPercentage, CongestionLevel congestionLevel) {
        this.trafficDataId = trafficDataId;
        this.classOneVolume = classOneVolume;
        this.classTwoVolume = classTwoVolume;
        this.classThreeVolume = classThreeVolume;
        this.averageSpeed = averageSpeed;
        this.volume = volume;
        this.period = period;
        this.occupancyPercentage = occupancyPercentage;
        this.congestionLevel = congestionLevel;
    }

    // Getters and setters...
}
