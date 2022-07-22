package com.example.mobilityindia.livelihood.livelihoodresponce;

import com.google.gson.annotations.SerializedName;

public class LivelihoodresponceResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	public Data getData(){
		return data;
	}

	public void setData(Data data){
		this.data = data;
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