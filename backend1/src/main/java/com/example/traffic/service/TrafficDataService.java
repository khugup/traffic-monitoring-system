package com.example.traffic.service;

import com.example.traffic.model.TrafficData;
import com.example.traffic.repo.TrafficDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TrafficDataService {

    private final TrafficDataRepository trafficDataRepo;

    public TrafficDataService(TrafficDataRepository trafficDataRepo) {
        this.trafficDataRepo = trafficDataRepo;
    }

    // -------------------------------
    // Save traffic data
    // -------------------------------
    @Transactional
    public TrafficData save(TrafficData data) {
        return trafficDataRepo.save(data);
    }

    // -------------------------------
    // Fetch all traffic data latest first
    // -------------------------------
    @Transactional(readOnly = true)
    public List<TrafficData> getAllLatestFirst() {
        return trafficDataRepo.findAllLatestFirst();
    }

    // -------------------------------
    // Delete traffic data older than cutoff
    // -------------------------------
    @Transactional
    public void deleteOlderThan(Date cutoff) {
        int deleted = trafficDataRepo.deleteOlderThan(cutoff);
        System.out.println("🧹 Deleted " + deleted + " old rows older than " + cutoff);
    }

    // -------------------------------
    // Count all traffic records
    // -------------------------------
    @Transactional(readOnly = true)
    public long count() {
        return trafficDataRepo.count();
    }
}
