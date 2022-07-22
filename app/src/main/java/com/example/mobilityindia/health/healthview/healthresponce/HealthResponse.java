package com.example.mobilityindia.health.healthview.healthresponce;

import com.google.gson.annotations.SerializedName;

public class HealthResponse{

	@SerializedName("healthdata")
	private Healthdata healthdata;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	public Healthdata getHealthdata(){
		return healthdata;
	}

	public void setHealthdata(Healthdata healthdata){
		this.healthdata = healthdata;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}

	public Boolean isStatus(){
		return status;
	}
}