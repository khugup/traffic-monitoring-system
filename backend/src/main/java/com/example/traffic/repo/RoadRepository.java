package com.example.traffic.repo;


import com.example.traffic.model.Road;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RoadRepository extends JpaRepository<Road, Integer> {
Optional<Road> findByDetectorName(String detectorName);
}