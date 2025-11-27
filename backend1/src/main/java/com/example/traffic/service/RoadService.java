package com.example.traffic.service;

import com.example.traffic.model.Road;
import com.example.traffic.repo.RoadRepository;
import com.example.traffic.exception.ResourceNotFoundException;
import com.example.traffic.service.interfaces.IRoadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadService implements IRoadService {

    private final RoadRepository roadRepository;

    public RoadService(RoadRepository roadRepository) {
        this.roadRepository = roadRepository;
    }

    @Override
    public Road createRoad(Road road) {
        return roadRepository.save(road);
    }

    @Override
    public Road updateRoad(Integer id, Road road) {
        Road existing = roadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Road", "ID", id));
        existing.setRoadName(road.getRoadName());
        existing.setDetectorName(road.getDetectorName());
        existing.setDetectorStatus(road.getDetectorStatus());
        return roadRepository.save(existing);
    }

    @Override
    public void deleteRoad(Integer id) {
        Road road = roadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Road", "ID", id));
        roadRepository.delete(road);
    }

    @Override
    public List<Road> getAllRoads() {
        return roadRepository.findAll();
    }

    @Override
    public Road getRoadById(Integer id) {
        return roadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Road", "ID", id));
    }

    @Override
    public List<Road> getActiveRoads() {
        return roadRepository.findAll()
                .stream()
                .filter(r -> Boolean.TRUE.equals(r.getDetectorStatus()))
                .toList();
    }
}
