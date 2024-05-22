package com.demo.HealthMonitoringApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.HealthMonitoringApplication.entity.PatientData;

public interface PatientDataRepository extends JpaRepository<PatientData, Long>{

}
