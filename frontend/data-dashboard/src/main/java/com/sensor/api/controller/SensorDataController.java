package com.sensor.api.controller;

import com.sensor.api.service.SensorDataService;
import com.sensor.simulator.model.SensorData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @GetMapping
    public List<SensorData> getAllSensorData() {
        return sensorDataService.getAllSensorData();
    }

    @GetMapping("/{id}")
    public SensorData getSensorDataById(@PathVariable Long id) {
        return sensorDataService.getSensorDataById(id);
    }

    @GetMapping("/latest")
    public SensorData getLatestSensorData() {
        return sensorDataService.getLatestSensorData();
    }

    @PostMapping
    public SensorData addSensorData(@RequestBody SensorData data) {
        return sensorDataService.saveSensorData(data);
    }

    @DeleteMapping("/{id}")
    public void deleteSensorData(@PathVariable Long id) {
        sensorDataService.deleteSensorData(id);
    }
}