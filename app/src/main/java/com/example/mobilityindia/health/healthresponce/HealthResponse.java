package com.example.mobilityindia.health.healthresponce;

import com.google.gson.annotations.SerializedName;

public class HealthResponse{

	@SerializedName("healthdata")
	private com.example.mobilityindia.health.healthresponce.Healthdata healthdata;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	public void setHealthdata(com.example.mobilityindia.health.healthresponce.Healthdata healthdata){
		this.healthdata = healthdata;
	}

	public Healthdata getHealthdata(){
		return healthdata;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}

	public Boolean isStatus(){
		return status;
	}
}