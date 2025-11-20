package com.example.traffic.scheduler;

import com.example.traffic.model.CongestionLevel;
import com.example.traffic.model.Road;
import com.example.traffic.model.TrafficData;
import com.example.traffic.model.TrafficDataId;
import com.example.traffic.repo.RoadRepository;
import com.example.traffic.service.TrafficDataService;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TrafficDataScheduler {

    private final RoadRepository roadRepository;
    private final TrafficDataService trafficDataService;

    public TrafficDataScheduler(RoadRepository roadRepository, TrafficDataService trafficDataService) {
        this.roadRepository = roadRepository;
        this.trafficDataService = trafficDataService;
    }

    @PostConstruct
    public void init() {
        System.out.println("🚦 TrafficDataScheduler initialized.");
    }

    /**
     * ----------------------------------------------------
     * GENERATE TRAFFIC DATA - RUNS EVERY 20 SECONDS
     * ----------------------------------------------------
     */
    @Scheduled(fixedRate = 20_000)
    public void generateTrafficData() {

        // Get all active roads only (detectorStatus = true)
        List<Road> activeRoads =
                roadRepository.findAll().stream()
                        .filter(road -> Boolean.TRUE.equals(road.getDetectorStatus()))
                        .toList();

        for (Road road : activeRoads) {

            // Random volumes between 1 and 9
            int classOne = ThreadLocalRandom.current().nextInt(1, 10);
            int classTwo = ThreadLocalRandom.current().nextInt(1, 10);
            int classThree = ThreadLocalRandom.current().nextInt(1, 10);

            // Random average speed
            double avgSpeed = ThreadLocalRandom.current().nextDouble(10.0, 100.0);

            int volume = classOne + classTwo + classThree;

            // Occupancy percentage = (Volume/30) * 100
            double occupancy = (volume * 100.0) / 30.0;

            // Determine congestion level
            CongestionLevel level = CongestionLevel.NORMAL;
            if (occupancy >= 75.0) {
                level = CongestionLevel.VERY_HIGH;
            } else if (occupancy >= 50.0) {
                level = CongestionLevel.HIGH;
            }

            // Create composite key
            TrafficDataId dataId = new TrafficDataId(
                    road.getDetectorName(),
                    road.getRoadName(),
                    new Date()
            );

            // Create TrafficData object
            TrafficData td = new TrafficData();
            td.setTrafficDataId(dataId);
            td.setClassOneVolume(classOne);
            td.setClassTwoVolume(classTwo);
            td.setClassThreeVolume(classThree);
            td.setAverageSpeed(avgSpeed);
            td.setVolume(volume);
            td.setOccupancyPercentage(occupancy);
            td.setPeriod(20); // Period set to 20 seconds
            td.setCongestionLevel(level);

            // Save to DB
            trafficDataService.save(td);
        }
    }

    /**
     * ----------------------------------------------------
     * DELETE OLD DATA (Older than 10 minutes)
     * RUNS EVERY 5 MINUTES
     * ----------------------------------------------------
     */
    @Scheduled(fixedRate = 300_000)
    public void deleteOldData() {
        long tenMinutesAgoMillis = System.currentTimeMillis() - (10 * 60 * 1000L);
        Date cutoffTime = new Date(tenMinutesAgoMillis);

        trafficDataService.deleteOlderThan(cutoffTime);
    }
}
