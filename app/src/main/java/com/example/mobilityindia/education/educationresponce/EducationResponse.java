package com.example.mobilityindia.education.educationresponce;

import com.google.gson.annotations.SerializedName;

public class EducationResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("educationdata")
	private Educationdata educationdata;

	@SerializedName("status")
	private Boolean status;

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public Educationdata getEducationdata(){
		return educationdata;
	}

	public void setEducationdata(Educationdata educationdata){
		this.educationdata = educationdata;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}

	public Boolean isStatus(){
		return status;
	}
}