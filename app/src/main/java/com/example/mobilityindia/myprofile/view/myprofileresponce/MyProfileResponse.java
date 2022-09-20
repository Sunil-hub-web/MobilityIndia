package com.example.mobilityindia.myprofile.view.myprofileresponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyProfileResponse{

	@SerializedName("status")
	@Expose
	private Boolean status;
	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("user_details")
	@Expose
	private UserDetails userDetails;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}