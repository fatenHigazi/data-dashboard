package com.sensor.api.service;



import com.sensor.simulator.model.SensorData;
import com.sensor.simulator.repository.SensorDataRepository;
import com.sensor.simulator.validation.SensorDataValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class SensorDataServiceTest {

    private SensorDataRepository repository;
    private SensorDataValidator validator;
    private SensorDataService service;

    @BeforeEach
    void setUp() {
        repository = mock(SensorDataRepository.class);
        validator = mock(SensorDataValidator.class);
        service = new SensorDataService(repository, validator);
    }

    @Test
    void getSensorDataById_Found() {
        SensorData data = new SensorData(1L, 22.0, 50.0, LocalDateTime.now());
        when(repository.findById(1L)).thenReturn(Optional.of(data));

        SensorData result = service.getSensorDataById(1L);

        assertNotNull(result);
        assertEquals(22.0, result.getTemperature());
    }

    @Test
    void saveSensorData_InvalidData_ThrowsException() {
        SensorData invalidData = new SensorData(null, 120.0, -20.0, LocalDateTime.now());
        when(validator.isValidSensorData(100.0, 50.0)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            service.saveSensorData(invalidData);
        });
    }
}
