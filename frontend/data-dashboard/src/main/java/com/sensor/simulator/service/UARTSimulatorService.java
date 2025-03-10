package com.sensor.simulator.service;

import com.sensor.api.service.SensorDataService;
import com.sensor.simulator.model.SensorData;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Service
public class UARTSimulatorService {

    private final SensorDataService sensorDataService;

    // Explicitly defined constructor clearly here
    public UARTSimulatorService(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @Scheduled(fixedRate = 2000)
    public void simulateUARTCommunication() {
        String uartData = generateUARTData();
        processAndStore(uartData);
    }

    private String generateUARTData() {
        double temperature = 15 + Math.random() * 20;
        double humidity = 30 + Math.random() * 50;
        return String.format("%.2f,%.2f\n", temperature, humidity);
    }

    private void processAndStore(String uartData) {
        try {
            String[] parts = uartData.trim().split(",");
            double temperature = Double.parseDouble(parts[0]);
            double humidity = Double.parseDouble(parts[1]);

            SensorData data = new SensorData();
            data.setTemperature(temperature);
            data.setHumidity(humidity);
            data.setTimestamp(LocalDateTime.now());

            sensorDataService.saveSensorData(data);
            System.out.println("Stored sensor data: " + data);

        } catch (Exception e) {
            System.err.println("Error processing UART data: " + e.getMessage());
        }
    }
}
