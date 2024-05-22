package com.demo.HealthMonitoringApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.HealthMonitoringApplication.entity.PatientData;
import com.demo.HealthMonitoringApplication.repository.PatientDataRepository;

@Service
public class PatientDataService {
    private final PatientDataRepository patientDataRepository;

    @Autowired
    public PatientDataService(PatientDataRepository patientDataRepository) {
        this.patientDataRepository = patientDataRepository;
    }

    public PatientData getPatientData(Long id) {
        return patientDataRepository.findById(id).orElse(null);
    }
}