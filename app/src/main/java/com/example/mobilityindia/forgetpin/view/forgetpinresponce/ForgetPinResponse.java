package com.example.mobilityindia.forgetpin.view.forgetpinresponce;

import com.google.gson.annotations.SerializedName;

public class ForgetPinResponse{

	@SerializedName("result")
	private Result result;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	@SerializedName("token")
	private String token;

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

	public String getToken(){
		return token;
	}

	public void setToken(String token){
		this.token = token;
	}
}