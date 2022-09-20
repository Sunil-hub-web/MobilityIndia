package com.example.mobilityindia.social.socialresponce;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("socialgovtwhat")
	private String socialgovtwhat;

	@SerializedName("socialtrainingwhat")
	private String socialtrainingwhat;

	@SerializedName("socialsports")
	private String socialsports;

	@SerializedName("socialtraining")
	private String socialtraining;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("spcialdpo")
	private String spcialdpo;

	@SerializedName("socialgovt")
	private String socialgovt;

	@SerializedName("socialtrainingwhere")
	private String socialtrainingwhere;

	@SerializedName("created_at")
	private String createdAt;

	public String getSocialgovtwhat(){
		return socialgovtwhat;
	}

	public void setSocialgovtwhat(String socialgovtwhat){
		this.socialgovtwhat = socialgovtwhat;
	}

	public String getSocialtrainingwhat(){
		return socialtrainingwhat;
	}

	public void setSocialtrainingwhat(String socialtrainingwhat){
		this.socialtrainingwhat = socialtrainingwhat;
	}

	public String getSocialsports(){
		return socialsports;
	}

	public void setSocialsports(String socialsports){
		this.socialsports = socialsports;
	}

	public String getSocialtraining(){
		return socialtraining;
	}

	public void setSocialtraining(String socialtraining){
		this.socialtraining = socialtraining;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getSpcialdpo(){
		return spcialdpo;
	}

	public void setSpcialdpo(String spcialdpo){
		this.spcialdpo = spcialdpo;
	}

	public String getSocialgovt(){
		return socialgovt;
	}

	public void setSocialgovt(String socialgovt){
		this.socialgovt = socialgovt;
	}

	public String getSocialtrainingwhere(){
		return socialtrainingwhere;
	}

	public void setSocialtrainingwhere(String socialtrainingwhere){
		this.socialtrainingwhere = socialtrainingwhere;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}
}