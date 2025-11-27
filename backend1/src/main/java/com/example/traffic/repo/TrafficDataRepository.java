package com.example.traffic.repo;

import com.example.traffic.model.TrafficData;
import com.example.traffic.model.TrafficDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface TrafficDataRepository extends JpaRepository<TrafficData, TrafficDataId> {

    // All traffic data ordered latest first
    @Query("SELECT t FROM TrafficData t ORDER BY t.trafficDataId.date DESC")
    List<TrafficData> findAllLatestFirst();

    // Latest traffic data for a road
    TrafficData findTopByTrafficDataIdRoadNameOrderByTrafficDataIdDateDesc(String roadName);

    // Delete old traffic data
    @Modifying
    @Transactional
    @Query("DELETE FROM TrafficData t WHERE t.trafficDataId.date < :cutoff")
    int deleteOlderThan(@Param("cutoff") Date cutoff);
}
