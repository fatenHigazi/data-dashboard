package com.sensor.api.service;

import com.sensor.simulator.model.SensorData;
import com.sensor.simulator.repository.SensorDataRepository;
import com.sensor.simulator.validation.SensorDataValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class SensorDataService {

    private final SensorDataRepository repository;
    private final SensorDataValidator validator;

    public SensorDataService(SensorDataRepository repository, SensorDataValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    public List<SensorData> getAllSensorData() {
        return repository.findAll();
    }


    public SensorData getLatestSensorData() {
        return repository.findTopByOrderByTimestampDesc();
    }

    public SensorData getSensorDataById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sensor data with ID " + id + " not found."));
    }

    public SensorData saveSensorData(SensorData data) {
        if (!validator.isValidSensorData(data.getTemperature(), data.getHumidity())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid sensor data.");
        }
        return repository.save(data);
    }

    public void deleteSensorData(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cannot delete: Sensor data with ID " + id + " not found.");
        }
        repository.deleteById(id);
    }

}
