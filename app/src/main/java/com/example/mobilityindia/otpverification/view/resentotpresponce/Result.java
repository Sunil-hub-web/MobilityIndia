package com.example.mobilityindia.otpverification.view.resentotpresponce;

import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("last_login")
	private String lastLogin;

	@SerializedName("ip")
	private String ip;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("remember_access_token")
	private Object rememberAccessToken;

	@SerializedName("profile_image")
	private Object profileImage;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("user_agents")
	private String userAgents;

	@SerializedName("user_type_id")
	private String userTypeId;

	@SerializedName("remember_token")
	private String rememberToken;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("status")
	private String status;

	public String getLastLogin(){
		return lastLogin;
	}

	public void setLastLogin(String lastLogin){
		this.lastLogin = lastLogin;
	}

	public String getIp(){
		return ip;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public Object getRememberAccessToken(){
		return rememberAccessToken;
	}

	public void setRememberAccessToken(Object rememberAccessToken){
		this.rememberAccessToken = rememberAccessToken;
	}

	public Object getProfileImage(){
		return profileImage;
	}

	public void setProfileImage(Object profileImage){
		this.profileImage = profileImage;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getUserAgents(){
		return userAgents;
	}

	public void setUserAgents(String userAgents){
		this.userAgents = userAgents;
	}

	public String getUserTypeId(){
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId){
		this.userTypeId = userTypeId;
	}

	public String getRememberToken(){
		return rememberToken;
	}

	public void setRememberToken(String rememberToken){
		this.rememberToken = rememberToken;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}
}