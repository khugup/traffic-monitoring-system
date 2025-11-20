package com.example.traffic.controller;


import com.example.traffic.dto.TrafficDataDto;
import com.example.traffic.model.TrafficData;
import com.example.traffic.service.TrafficDataService;
import com.example.traffic.util.DtoHelper;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/traffic")
@CrossOrigin(origins = "*")
public class TrafficDataController {


private final TrafficDataService trafficDataService;


public TrafficDataController(TrafficDataService trafficDataService) {
this.trafficDataService = trafficDataService;
}


@GetMapping("/")
public List<TrafficDataDto> getAllTraffic() {
List<TrafficData> list = trafficDataService.getAllLatestFirst();
return list.stream().map(DtoHelper::toDto).collect(Collectors.toList());
}
}