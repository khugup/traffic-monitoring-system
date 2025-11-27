package com.example.traffic.dto;

import java.util.Date;

public class TrafficDataDto {
	private String roadName;
	private String detectorName;
	private Double occupancyPercentage;
	private Double averageSpeed;
	private Integer volume;
	private Integer classOneVolume;
	private Integer classTwoVolume;
	private Integer classThreeVolume;
	private Integer period;
	private Date date;
	private int congestionLevel;

	public TrafficDataDto() {
		this.congestionLevel = 1;
	}

	public TrafficDataDto(String roadName, String detectorName, Double occupancyPercentage, Double averageSpeed,
			Integer volume, Integer classOneVolume, Integer classTwoVolume, Integer classThreeVolume, Integer period,
			Date date, int congestionLevel) {
		this.roadName = roadName;
		this.detectorName = detectorName;
		this.occupancyPercentage = occupancyPercentage;
		this.averageSpeed = averageSpeed;
		this.volume = volume;
		this.classOneVolume = classOneVolume;
		this.classTwoVolume = classTwoVolume;
		this.classThreeVolume = classThreeVolume;
		this.period = period;
		this.date = date;
		this.congestionLevel = congestionLevel;
	}

// getters and setters
	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getDetectorName() {
		return detectorName;
	}

	public void setDetectorName(String detectorName) {
		this.detectorName = detectorName;
	}

	public Double getOccupancyPercentage() {
		return occupancyPercentage;
	}

	public void setOccupancyPercentage(Double occupancyPercentage) {
		this.occupancyPercentage = occupancyPercentage;
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

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCongestionLevel() {
		return congestionLevel;
	}

	public void setCongestionLevel(int congestionLevel) {
		this.congestionLevel = congestionLevel;
	}

	@Override
	public String toString() {
		return "TrafficDataDto{" + "roadName='" + roadName + '\'' + ", detectorName='" + detectorName + '\''
				+ ", occupancyPercentage=" + occupancyPercentage + ", averageSpeed=" + averageSpeed + ", volume="
				+ volume + ", classOneVolume=" + classOneVolume + ", classTwoVolume=" + classTwoVolume
				+ ", classThreeVolume=" + classThreeVolume + ", period=" + period + ", date=" + date
				+ ", congestionLevel=" + congestionLevel + '}';
	}
}