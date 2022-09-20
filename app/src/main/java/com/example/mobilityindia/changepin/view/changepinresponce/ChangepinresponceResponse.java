package com.example.mobilityindia.changepin.view.changepinresponce;

import com.google.gson.annotations.SerializedName;

public class ChangepinresponceResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String isStatus(){
		return status;
	}

	public String getStatus() {
		return status;
	}
}