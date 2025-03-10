package com.sensor.simulator.repository;


import com.sensor.simulator.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    SensorData findTopByOrderByTimestampDesc();
}