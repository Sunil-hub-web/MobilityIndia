package com.example.mobilityindia.myprofile.view.myprofileresponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

	@SerializedName("user_id")
	@Expose
	private String userId;
	@SerializedName("user_type_id")
	@Expose
	private String userTypeId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("phone")
	@Expose
	private String phone;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("created_at")
	@Expose
	private String createdAt;
	@SerializedName("updated_at")
	@Expose
	private String updatedAt;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("designation")
	@Expose
	private String designation;
	@SerializedName("profile_image")
	@Expose
	private Object profileImage;
	@SerializedName("last_login")
	@Expose
	private String lastLogin;
	@SerializedName("remember_access_token")
	@Expose
	private Object rememberAccessToken;
	@SerializedName("ip")
	@Expose
	private String ip;
	@SerializedName("user_agents")
	@Expose
	private String userAgents;
	@SerializedName("remember_token")
	@Expose
	private String rememberToken;
	@SerializedName("is_supper")
	@Expose
	private String isSupper;
	@SerializedName("admin_role_id")
	@Expose
	private String adminRoleId;
	@SerializedName("created_by")
	@Expose
	private String createdBy;
	@SerializedName("updated_by")
	@Expose
	private String updatedBy;
	@SerializedName("gender")
	@Expose
	private String gender;
	@SerializedName("ra_id")
	@Expose
	private String raId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Object getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Object profileImage) {
		this.profileImage = profileImage;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Object getRememberAccessToken() {
		return rememberAccessToken;
	}

	public void setRememberAccessToken(Object rememberAccessToken) {
		this.rememberAccessToken = rememberAccessToken;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgents() {
		return userAgents;
	}

	public void setUserAgents(String userAgents) {
		this.userAgents = userAgents;
	}

	public String getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	public String getIsSupper() {
		return isSupper;
	}

	public void setIsSupper(String isSupper) {
		this.isSupper = isSupper;
	}

	public String getAdminRoleId() {
		return adminRoleId;
	}

	public void setAdminRoleId(String adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRaId() {
		return raId;
	}

	public void setRaId(String raId) {
		this.raId = raId;
	}
}