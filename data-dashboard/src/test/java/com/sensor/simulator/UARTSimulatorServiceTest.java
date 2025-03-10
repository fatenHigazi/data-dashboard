package com.sensor.simulator;


import com.sensor.api.service.SensorDataService;
import com.sensor.simulator.model.SensorData;
import com.sensor.simulator.service.UARTSimulatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UARTSimulatorServiceTest {

    private UARTSimulatorService uartSimulatorService;
    private SensorDataService sensorDataService;

    @BeforeEach
    void setup() {
        sensorDataService = Mockito.mock(SensorDataService.class);
        uartSimulatorService = new UARTSimulatorService(sensorDataService);
    }

    @Test
    void testSimulateUARTCommunication() {
        when(sensorDataService.saveSensorData(any(SensorData.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        uartSimulatorService.simulateUARTCommunication();

        verify(sensorDataService, times(1)).saveSensorData(any(SensorData.class));
    }

}
