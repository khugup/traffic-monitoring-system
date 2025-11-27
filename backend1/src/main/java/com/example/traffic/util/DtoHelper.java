package com.example.traffic.util;

import com.example.traffic.dto.TrafficDataDto;
import com.example.traffic.model.TrafficData;
import com.example.traffic.model.TrafficDataId;

public class DtoHelper {
	public static TrafficDataDto toDto(TrafficData td) {
		if (td == null)
			return null;
		TrafficDataDto dto = new TrafficDataDto();
		dto.setAverageSpeed(td.getAverageSpeed());
		dto.setClassOneVolume(td.getClassOneVolume());
		dto.setClassTwoVolume(td.getClassTwoVolume());
		dto.setClassThreeVolume(td.getClassThreeVolume());
		dto.setVolume(td.getVolume());
		dto.setOccupancyPercentage(td.getOccupancyPercentage());
		dto.setPeriod(td.getPeriod());
		if (td.getTrafficDataId() != null) {
			TrafficDataId id = td.getTrafficDataId();
			dto.setRoadName(id.getRoadName());
			dto.setDetectorName(id.getDetectorName());
			dto.setDate(id.getDate());
		}
		if (td.getCongestionLevel() != null) {
			dto.setCongestionLevel(td.getCongestionLevel().getValue());
		}
		return dto;
	}
}