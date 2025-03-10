package com.sensor.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor.api.service.SensorDataService;
import com.sensor.simulator.model.SensorData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SensorDataController.class)
class SensorDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SensorDataService sensorDataService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllSensorData() throws Exception {
        List<SensorData> dataList = List.of(
                new SensorData(1L, 25.0, 60.0, LocalDateTime.now()),
                new SensorData(2L, 27.0, 55.0, LocalDateTime.now())
        );

        when(sensorDataService.getAllSensorData()).thenReturn(dataList);

        mockMvc.perform(get("/api/sensor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].temperature", is(25.0)));
    }

    @Test
    void testGetSensorDataById() throws Exception {
        SensorData data = new SensorData(1L, 22.5, 50.0, LocalDateTime.now());

        when(sensorDataService.getSensorDataById(1L)).thenReturn(data);

        mockMvc.perform(get("/api/sensor/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.temperature", is(22.5)));
    }

    @Test
    void testGetLatestSensorData() throws Exception {
        SensorData latestData = new SensorData(3L, 26.0, 65.0, LocalDateTime.now());

        when(sensorDataService.getLatestSensorData()).thenReturn(latestData);

        mockMvc.perform(get("/api/sensor/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.humidity", is(65.0)));
    }

    @Test
    void testAddSensorData() throws Exception {
        SensorData newData = new SensorData(null, 23.0, 45.0, LocalDateTime.now());
        SensorData savedData = new SensorData(4L, 23.0, 45.0, LocalDateTime.now());

        when(sensorDataService.saveSensorData(any(SensorData.class))).thenReturn(savedData);

        mockMvc.perform(post("/api/sensor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.temperature", is(23.0)));
    }

    @Test
    void testDeleteSensorData() throws Exception {
        mockMvc.perform(delete("/api/sensor/{id}", 1))
                .andExpect(status().isOk());
    }
}
