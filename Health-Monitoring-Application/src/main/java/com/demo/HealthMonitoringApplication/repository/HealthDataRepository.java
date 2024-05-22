package com.demo.HealthMonitoringApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.HealthMonitoringApplication.entity.HealthData;

public interface HealthDataRepository extends JpaRepository<HealthData, Long> {

}