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

        List<Road> activeRoads = roadRepository.findAll().stream()
                .filter(road -> Boolean.TRUE.equals(road.getDetectorStatus()))
                .toList();

        if (activeRoads.isEmpty()) {
            System.out.println("⚠️ No active roads found for traffic data generation.");
            return;
        }

        for (Road road : activeRoads) {

            int classOne = ThreadLocalRandom.current().nextInt(1, 10);
            int classTwo = ThreadLocalRandom.current().nextInt(1, 10);
            int classThree = ThreadLocalRandom.current().nextInt(1, 10);

            double avgSpeed = ThreadLocalRandom.current().nextDouble(10.0, 100.0);

            int volume = classOne + classTwo + classThree;
            double occupancy = (volume * 100.0) / 30.0;

            CongestionLevel level = CongestionLevel.NORMAL;
            if (occupancy >= 75.0) {
                level = CongestionLevel.VERY_HIGH;
            } else if (occupancy >= 50.0) {
                level = CongestionLevel.HIGH;
            }

            TrafficDataId dataId = new TrafficDataId(
                    road.getDetectorName(),
                    road.getRoadName(),
                    new Date()
            );

            TrafficData td = new TrafficData();
            td.setTrafficDataId(dataId);
            td.setClassOneVolume(classOne);
            td.setClassTwoVolume(classTwo);
            td.setClassThreeVolume(classThree);
            td.setAverageSpeed(avgSpeed);
            td.setVolume(volume);
            td.setOccupancyPercentage(occupancy);
            td.setPeriod(20);
            td.setCongestionLevel(level);

            trafficDataService.save(td);
        }

        System.out.println("✅ Generated traffic data for " + activeRoads.size() + " active roads at " + new Date());
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

        System.out.println("🗑️ Checked for old traffic data deletion at " + new Date());
    }
}
