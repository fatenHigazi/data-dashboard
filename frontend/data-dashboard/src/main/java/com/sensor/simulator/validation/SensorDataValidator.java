package com.sensor.simulator.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SensorDataValidator {

    public boolean isValidTemperature(Double temperature) {
        return temperature != null && temperature >= -50 && temperature <= 50;
    }

    public boolean isValidHumidity(Double humidity) {
        return humidity != null && humidity >= 0 && humidity <= 100;
    }

    public boolean isValidSensorData(Double temperature, Double humidity) {
        return isValidTemperature(temperature) && isValidHumidity(humidity);
    }

    public boolean isValidFormat(String uartData) {
        return uartData != null && uartData.matches("^\\s*-?\\d+(\\.\\d+)?,\\s*-?\\d+(\\.\\d+)?\\s*$");
    }

    // Optional Date validation example (if date string provided externally)
    public boolean isValidTimestamp(String timestampStr) {
        try {
            LocalDateTime.parse(timestampStr); // default ISO-8601 format
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}