package com.example.mobilityindia.otpverification.view.otpverifyresponce;

import com.google.gson.annotations.SerializedName;

public class VerifyotpResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

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