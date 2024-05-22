package com.demo.HealthMonitoringApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.HealthMonitoringApplication.entity.PatientData;
import com.demo.HealthMonitoringApplication.service.PatientDataService;

@RestController
public class PatientDataController {
    private final PatientDataService patientDataService;

    @Autowired
    public PatientDataController(PatientDataService patientDataService) {
        this.patientDataService = patientDataService;
    }

    @GetMapping("/api/patient/data/{id}")
    public PatientData getPatientData(@PathVariable Long id) {
        return patientDataService.getPatientData(id);
    }
}
