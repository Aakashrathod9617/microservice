package com.demo.HealthMonitoringApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HealthData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String deviceId;
	private int heartRate;
	private String bloodPressure;

	public HealthData() {
		// TODO Auto-generated constructor stub
	}

	public HealthData(Long id, String deviceId, int heartRate, String bloodPressure) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.heartRate = heartRate;
		this.bloodPressure = bloodPressure;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

}