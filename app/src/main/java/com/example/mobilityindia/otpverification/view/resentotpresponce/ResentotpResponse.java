package com.example.mobilityindia.otpverification.view.resentotpresponce;

import com.google.gson.annotations.SerializedName;

public class ResentotpResponse{

	@SerializedName("result")
	private Result result;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	public Result getResult(){
		return result;
	}

	public void setResult(Result result){
		this.result = result;
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