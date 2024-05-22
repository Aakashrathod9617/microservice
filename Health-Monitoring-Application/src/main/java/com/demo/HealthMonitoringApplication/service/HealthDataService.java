package com.demo.HealthMonitoringApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.HealthMonitoringApplication.entity.HealthData;
import com.demo.HealthMonitoringApplication.repository.HealthDataRepository;

@Service
public class HealthDataService {
    private final HealthDataRepository healthDataRepository;

    @Autowired
    public HealthDataService(HealthDataRepository healthDataRepository) {
        this.healthDataRepository = healthDataRepository;
    }

    public void saveHealthData(HealthData healthData) {
        healthDataRepository.save(healthData);
    }
}
