package com.example.traffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TrafficMonitoringApplication {
public static void main(String[] args) {
SpringApplication.run(TrafficMonitoringApplication.class, args);
}
}
