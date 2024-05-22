package com.demo.HealthMonitoringApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.HealthMonitoringApplication.entity.HealthData;
import com.demo.HealthMonitoringApplication.service.AlertService;
import com.demo.HealthMonitoringApplication.service.HealthDataService;

@RestController
public class HealthDataController {
    private final HealthDataService healthDataService;
    private final AlertService alertService;

    @Autowired
    public HealthDataController(HealthDataService healthDataService, AlertService alertService) {
        this.healthDataService = healthDataService;
        this.alertService = alertService;
    }

    @PostMapping("/api/data")
    public void sendData(@RequestBody HealthData data) {
        healthDataService.saveHealthData(data);
        alertService.checkForAbnormalReadings(data);
    }
}