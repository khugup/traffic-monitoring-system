package com.example.traffic.service.interfaces;

import com.example.traffic.model.Road;
import java.util.List;

public interface IRoadService {
    Road createRoad(Road road);
    Road updateRoad(Integer id, Road road);
    void deleteRoad(Integer id);
    List<Road> getAllRoads();
    Road getRoadById(Integer id);
    List<Road> getActiveRoads();
}
