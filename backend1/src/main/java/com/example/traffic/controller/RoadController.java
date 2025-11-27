package com.example.traffic.controller;

import com.example.traffic.model.Road;
import com.example.traffic.model.TrafficData;
import com.example.traffic.repo.TrafficDataRepository;
import com.example.traffic.service.interfaces.IRoadService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roads")
@CrossOrigin(origins = "http://localhost:4200")
public class RoadController {

    private final IRoadService roadService;
    private final TrafficDataRepository trafficDataRepository;

    public RoadController(IRoadService roadService, TrafficDataRepository trafficDataRepository) {
        this.roadService = roadService;
        this.trafficDataRepository = trafficDataRepository;
    }

    @PostMapping("/")
    public Road addRoad(@RequestBody Road road) {
        return roadService.createRoad(road);
    }

    @GetMapping("/")
    public List<Road> getAllRoads() {
        List<Road> roads = roadService.getAllRoads();

        for (Road road : roads) {
            TrafficData latest = trafficDataRepository
                    .findTopByTrafficDataIdRoadNameOrderByTrafficDataIdDateDesc(road.getRoadName());

            if (latest != null && latest.getCongestionLevel() != null) {
                // Map enum to readable string
                switch (latest.getCongestionLevel()) {
                    case NORMAL -> road.setCongestionLevel("Low");
                    case HIGH -> road.setCongestionLevel("Medium");
                    case VERY_HIGH -> road.setCongestionLevel("High");
                    default -> road.setCongestionLevel("Unknown");
                }
            } else {
                road.setCongestionLevel("Unknown");
            }
        }

        return roads;
    }

    @GetMapping("/{id}")
    public Road getRoadById(@PathVariable Integer id) {
        Road road = roadService.getRoadById(id);

        TrafficData latest = trafficDataRepository
                .findTopByTrafficDataIdRoadNameOrderByTrafficDataIdDateDesc(road.getRoadName());

        if (latest != null && latest.getCongestionLevel() != null) {
            switch (latest.getCongestionLevel()) {
                case NORMAL -> road.setCongestionLevel("Low");
                case HIGH -> road.setCongestionLevel("Medium");
                case VERY_HIGH -> road.setCongestionLevel("High");
                default -> road.setCongestionLevel("Unknown");
            }
        } else {
            road.setCongestionLevel("Unknown");
        }

        return road;
    }

    @PutMapping("/{id}")
    public Road updateRoad(@PathVariable Integer id, @RequestBody Road road) {
        return roadService.updateRoad(id, road);
    }

    @DeleteMapping("/{id}")
    public String deleteRoad(@PathVariable Integer id) {
        roadService.deleteRoad(id);
        return "Road deleted successfully with ID: " + id;
    }
}
