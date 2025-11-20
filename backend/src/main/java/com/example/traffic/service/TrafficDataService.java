package com.example.traffic.service;

import com.example.traffic.model.TrafficData;
import com.example.traffic.repo.TrafficDataRepository;
import com.example.traffic.service.interfaces.ITrafficDataService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TrafficDataService implements ITrafficDataService {

    private final TrafficDataRepository trafficDataRepo;

    public TrafficDataService(TrafficDataRepository trafficDataRepo) {
        this.trafficDataRepo = trafficDataRepo;
    }

    @Override
    public TrafficData save(TrafficData data) {
        return trafficDataRepo.save(data);
    }

    @Override
    public List<TrafficData> getAllLatestFirst() {
        return trafficDataRepo.findAllByOrderByTrafficDataIdDateDesc();
    }

    @Override
    public void deleteOlderThan(Date cutoff) {
        trafficDataRepo.deleteByTrafficDataIdDateLessThan(cutoff);
    }
}
