package com.example.mobilityindia.syn1.view.allresponse.social;

import com.example.mobilityindia.sync.model.SocialData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDemo_Social {

    @SerializedName("socialdata")
    @Expose
    private SocialData socialdata;

    public SocialData getSocialdata() {
        return socialdata;
    }

    public void setSocialdata(SocialData socialdata) {
        this.socialdata = socialdata;
    }
}
