package com.example.traffic.service.interfaces;

import com.example.traffic.model.TrafficData;
import java.util.Date;
import java.util.List;

public interface ITrafficDataService {
    TrafficData save(TrafficData data);
    List<TrafficData> getAllLatestFirst();
    void deleteOlderThan(Date cutoff);
}

