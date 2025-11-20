package com.example.traffic.controller;

import com.example.traffic.model.Road;
import com.example.traffic.service.interfaces.IRoadService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/road")
@CrossOrigin(origins = "http://localhost:4200")
public class RoadController {

    private final IRoadService roadService;

    // ✅ Constructor for Spring to use
    public RoadController(IRoadService roadService) {
        this.roadService = roadService;
    }

    @PostMapping("/")
    public Road addRoad(@RequestBody Road road) {
        return roadService.createRoad(road);
    }

    @GetMapping("/")
    public List<Road> getAllRoads() {
        return roadService.getAllRoads();
    }

    @GetMapping("/{id}")
    public Road getRoadById(@PathVariable Integer id) {
        return roadService.getRoadById(id);
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
