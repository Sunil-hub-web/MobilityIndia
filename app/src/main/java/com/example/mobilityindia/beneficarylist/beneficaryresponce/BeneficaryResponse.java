package com.example.mobilityindia.beneficarylist.beneficaryresponce;

import com.google.gson.annotations.SerializedName;

public class BeneficaryResponse{

	@SerializedName("beneficrydata")
	private Beneficrydata beneficrydata;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	public Beneficrydata getBeneficrydata(){
		return beneficrydata;
	}

	public void setBeneficrydata(Beneficrydata beneficrydata){
		this.beneficrydata = beneficrydata;
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