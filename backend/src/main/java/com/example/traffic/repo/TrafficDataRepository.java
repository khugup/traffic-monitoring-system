package com.example.traffic.repo;

import com.example.traffic.model.TrafficData;
import com.example.traffic.model.TrafficDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface TrafficDataRepository extends JpaRepository<TrafficData, TrafficDataId> {
    List<TrafficData> findAllByOrderByTrafficDataIdDateDesc();
    void deleteByTrafficDataIdDateLessThan(Date date);
}
