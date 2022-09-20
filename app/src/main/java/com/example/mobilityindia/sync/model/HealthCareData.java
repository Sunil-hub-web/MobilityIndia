package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "healthcare_table")
public class HealthCareData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    @NonNull
    public String id;

    @ColumnInfo(name = "benificiary_id")
    @SerializedName("benificiary_id")
    @Expose
    private String benificiaryId;

    @ColumnInfo(name = "service_done")
    @SerializedName("service_done")
    @Expose
    private String serviceDone;

    @ColumnInfo(name = "screeningdate")
    @SerializedName("screeningdate")
    @Expose
    private String screeningdate;

    @ColumnInfo(name = "assessmentdate")
    @SerializedName("assessmentdate")
    @Expose
    private String assessmentdate;

    @ColumnInfo(name = "assessmentwho")
    @SerializedName("assessmentwho")
    @Expose
    private String assessmentwho;

    @ColumnInfo(name = "assessmentwhere")
    @SerializedName("assessmentwhere")
    @Expose
    private String assessmentwhere;

    @ColumnInfo(name = "referral")
    @SerializedName("referral")
    @Expose
    private String referral;

    @ColumnInfo(name = "referralplace")
    @SerializedName("referralplace")
    @Expose
    private String referralplace;

    @ColumnInfo(name = "referralprescription")
    @SerializedName("referralprescription")
    @Expose
    private String referralprescription;

    @ColumnInfo(name = "trialwhat")
    @SerializedName("trialwhat")
    @Expose
    private String trialwhat;

    @ColumnInfo(name = "trialdate")
    @SerializedName("trialdate")
    @Expose
    private String trialdate;

    @ColumnInfo(name = "gaitfrequency")
    @SerializedName("gaitfrequency")
    @Expose
    private String gaitfrequency;

    @ColumnInfo(name = "gaithowmany")
    @SerializedName("gaithowmany")
    @Expose
    private String gaithowmany;

    @ColumnInfo(name = "therapyfrequency")
    @SerializedName("therapyfrequency")
    @Expose
    private String therapyfrequency;

    @ColumnInfo(name = "therapysessions")
    @SerializedName("therapysessions")
    @Expose
    private String therapysessions;

    @ColumnInfo(name = "fitmentwho")
    @SerializedName("fitmentwho")
    @Expose
    private String fitmentwho;

    @ColumnInfo(name = "fitmentwhere")
    @SerializedName("fitmentwhere")
    @Expose
    private String fitmentwhere;

    @ColumnInfo(name = "fitmentdevice")
    @SerializedName("fitmentdevice")
    @Expose
    private String fitmentdevice;

    @ColumnInfo(name = "aid_appliances")
    @SerializedName("aid_appliances")
    @Expose
    private String aidAppliances;

    @ColumnInfo(name = "noof_appliances")
    @SerializedName("noof_appliances")
    @Expose
    private String noofAppliances;

    @ColumnInfo(name = "total_cost")
    @SerializedName("total_cost")
    @Expose
    private String totalCost;

    @ColumnInfo(name = "patient_contribution")
    @SerializedName("patient_contribution")
    @Expose
    private String patientContribution;

    @ColumnInfo(name = "donor_contribution")
    @SerializedName("donor_contribution")
    @Expose
    private String donorContribution;

    @ColumnInfo(name = "followupfrequency")
    @SerializedName("followupfrequency")
    @Expose
    private String followupfrequency;

    @ColumnInfo(name = "followupsheet")
    @SerializedName("followupsheet")
    @Expose
    private String followupsheet;

    @ColumnInfo(name = "surgery")
    @SerializedName("surgery")
    @Expose
    private String surgery;

    @ColumnInfo(name = "surgerywhere")
    @SerializedName("surgerywhere")
    @Expose
    private String surgerywhere;

    @ColumnInfo(name = "surgerywherewhat")
    @SerializedName("surgerywherewhat")
    @Expose
    private String surgerywherewhat;

    @ColumnInfo(name = "homerecommend")
    @SerializedName("homerecommend")
    @Expose
    private String homerecommend;

    @ColumnInfo(name = "homerecommendwhat")
    @SerializedName("homerecommendwhat")
    @Expose
    private String homerecommendwhat;

    @ColumnInfo(name = "repair_cost")
    @SerializedName("repair_cost")
    @Expose
    private String repairCost;

    @ColumnInfo(name = "patient_contribution_repair")
    @SerializedName("patient_contribution_repair")
    @Expose
    private String patientContributionRepair;

    @ColumnInfo(name = "donor_contribution_repair")
    @SerializedName("donor_contribution_repair")
    @Expose
    private String donorContributionRepair;

    @ColumnInfo(name = "repair_cost_cbr")
    @SerializedName("repair_cost_cbr")
    @Expose
    private String repairCostCbr;

    @ColumnInfo(name = "patient_contribution_cbr")
    @SerializedName("patient_contribution_cbr")
    @Expose
    private String patientContributionCbr;

    @ColumnInfo(name = "donor_contribution_cbr")
    @SerializedName("donor_contribution_cbr")
    @Expose
    private String donorContributionCbr;

    @ColumnInfo(name = "ihp")
    @SerializedName("ihp")
    @Expose
    private String ihp;

    @ColumnInfo(name = "ihp_doc")
    @SerializedName("ihp_doc")
    @Expose
    private String ihpDoc;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @ColumnInfo(name = "created_by")
    @SerializedName("created_by")
    @Expose
    private String createdBy;

    @ColumnInfo(name = "updated_by")
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;

    @ColumnInfo(name = "speech_hear_id")
    @SerializedName("speech_hear_id")
    @Expose
    private String speechHearId;

    @ColumnInfo(name = "speech_lang_dev")
    @SerializedName("speech_lang_dev")
    @Expose
    private String speechLangDev;

    @ColumnInfo(name = "opme_dd")
    @SerializedName("opme_dd")
    @Expose
    private String opmeDd;

    @ColumnInfo(name = "if_abnormal")
    @SerializedName("if_abnormal")
    @Expose
    private String ifAbnormal;

    @ColumnInfo(name = "speech_articulation")
    @SerializedName("speech_articulation")
    @Expose
    private String speechArticulation;

    @ColumnInfo(name = "if_misarticulation")
    @SerializedName("if_misarticulation")
    @Expose
    private String ifMisarticulation;

    @ColumnInfo(name = "fluency")
    @SerializedName("fluency")
    @Expose
    private String fluency;

    @ColumnInfo(name = "voice_quality")
    @SerializedName("voice_quality")
    @Expose
    private String voiceQuality;

    @ColumnInfo(name = "voice_pitch")
    @SerializedName("voice_pitch")
    @Expose
    private String voicePitch;

    @ColumnInfo(name = "voice_loudness")
    @SerializedName("voice_loudness")
    @Expose
    private String voiceLoudness;

    @ColumnInfo(name = "mode_communication")
    @SerializedName("mode_communication")
    @Expose
    private String modeCommunication;

    @ColumnInfo(name = "language_comprehension")
    @SerializedName("language_comprehension")
    @Expose
    private String languageComprehension;

    @ColumnInfo(name = "language_expression")
    @SerializedName("language_expression")
    @Expose
    private String languageExpression;

    @ColumnInfo(name = "test_result")
    @SerializedName("test_result")
    @Expose
    private String testResult;

    @ColumnInfo(name = "right_otoscopy")
    @SerializedName("right_otoscopy")
    @Expose
    private String rightOtoscopy;

    @ColumnInfo(name = "left_otoscopy")
    @SerializedName("left_otoscopy")
    @Expose
    private String leftOtoscopy;

    @ColumnInfo(name = "pure_tone_audio_right")
    @SerializedName("pure_tone_audio_right")
    @Expose
    private String pureToneAudioRight;

    @ColumnInfo(name = "pure_tone_audio_left")
    @SerializedName("pure_tone_audio_left")
    @Expose
    private String pureToneAudioLeft;

    @ColumnInfo(name = "speech_audio_test")
    @SerializedName("speech_audio_test")
    @Expose
    private String speechAudioTest;

    @ColumnInfo(name = "ear_discharge")
    @SerializedName("ear_discharge")
    @Expose
    private String earDischarge;

    @ColumnInfo(name = "ling_sound")
    @SerializedName("ling_sound")
    @Expose
    private String lingSound;

    @ColumnInfo(name = "hearing_model_right")
    @SerializedName("hearing_model_right")
    @Expose
    private String hearingModelRight;

    @ColumnInfo(name = "hearing_model_left")
    @SerializedName("hearing_model_left")
    @Expose
    private String hearingModelLeft;

    @ColumnInfo(name = "ear_mould_right")
    @SerializedName("ear_mould_right")
    @Expose
    private String earMouldRight;

    @ColumnInfo(name = "ear_mould_left")
    @SerializedName("ear_mould_left")
    @Expose
    private String earMouldLeft;

    @ColumnInfo(name = "specialists_remark")
    @SerializedName("specialists_remark")
    @Expose
    private String specialistsRemark;

    @ColumnInfo(name = "health_service_id")
    @SerializedName("health_service_id")
    @Expose
    private String healthServiceId;

    @ColumnInfo(name = "beneficiary_id")
    @SerializedName("beneficiary_id")
    @Expose
    private String beneficiaryId;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    @Expose
    private String flag;

    @ColumnInfo(name = "ihpdocs")
    @SerializedName("ihpdocs")
    @Expose
    private List<String> ihpdocs = null;

    @ColumnInfo(name = "service_name")
    @SerializedName("service_name")
    @Expose
    private List<String> serviceName = null;

    @ColumnInfo(name = "device_name")
    @SerializedName("device_name")
    @Expose
    private List<String> deviceName = null;

    public List<String> getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(List<String> deviceName) {
        this.deviceName = deviceName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBenificiaryId() {
        return benificiaryId;
    }

    public void setBenificiaryId(String benificiaryId) {
        this.benificiaryId = benificiaryId;
    }

    public String getServiceDone() {
        return serviceDone;
    }

    public void setServiceDone(String serviceDone) {
        this.serviceDone = serviceDone;
    }

    public String getScreeningdate() {
        return screeningdate;
    }

    public void setScreeningdate(String screeningdate) {
        this.screeningdate = screeningdate;
    }

    public String getAssessmentdate() {
        return assessmentdate;
    }

    public void setAssessmentdate(String assessmentdate) {
        this.assessmentdate = assessmentdate;
    }

    public String getAssessmentwho() {
        return assessmentwho;
    }

    public void setAssessmentwho(String assessmentwho) {
        this.assessmentwho = assessmentwho;
    }

    public String getAssessmentwhere() {
        return assessmentwhere;
    }

    public void setAssessmentwhere(String assessmentwhere) {
        this.assessmentwhere = assessmentwhere;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getReferralplace() {
        return referralplace;
    }

    public void setReferralplace(String referralplace) {
        this.referralplace = referralplace;
    }

    public String getReferralprescription() {
        return referralprescription;
    }

    public void setReferralprescription(String referralprescription) {
        this.referralprescription = referralprescription;
    }

    public String getTrialwhat() {
        return trialwhat;
    }

    public void setTrialwhat(String trialwhat) {
        this.trialwhat = trialwhat;
    }

    public String getTrialdate() {
        return trialdate;
    }

    public void setTrialdate(String trialdate) {
        this.trialdate = trialdate;
    }

    public String getGaitfrequency() {
        return gaitfrequency;
    }

    public void setGaitfrequency(String gaitfrequency) {
        this.gaitfrequency = gaitfrequency;
    }

    public String getGaithowmany() {
        return gaithowmany;
    }

    public void setGaithowmany(String gaithowmany) {
        this.gaithowmany = gaithowmany;
    }

    public String getTherapyfrequency() {
        return therapyfrequency;
    }

    public void setTherapyfrequency(String therapyfrequency) {
        this.therapyfrequency = therapyfrequency;
    }

    public String getTherapysessions() {
        return therapysessions;
    }

    public void setTherapysessions(String therapysessions) {
        this.therapysessions = therapysessions;
    }

    public String getFitmentwho() {
        return fitmentwho;
    }

    public void setFitmentwho(String fitmentwho) {
        this.fitmentwho = fitmentwho;
    }

    public String getFitmentwhere() {
        return fitmentwhere;
    }

    public void setFitmentwhere(String fitmentwhere) {
        this.fitmentwhere = fitmentwhere;
    }

    public String getFitmentdevice() {
        return fitmentdevice;
    }

    public void setFitmentdevice(String fitmentdevice) {
        this.fitmentdevice = fitmentdevice;
    }

    public String getAidAppliances() {
        return aidAppliances;
    }

    public void setAidAppliances(String aidAppliances) {
        this.aidAppliances = aidAppliances;
    }

    public String getNoofAppliances() {
        return noofAppliances;
    }

    public void setNoofAppliances(String noofAppliances) {
        this.noofAppliances = noofAppliances;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getPatientContribution() {
        return patientContribution;
    }

    public void setPatientContribution(String patientContribution) {
        this.patientContribution = patientContribution;
    }

    public String getDonorContribution() {
        return donorContribution;
    }

    public void setDonorContribution(String donorContribution) {
        this.donorContribution = donorContribution;
    }

    public String getFollowupfrequency() {
        return followupfrequency;
    }

    public void setFollowupfrequency(String followupfrequency) {
        this.followupfrequency = followupfrequency;
    }

    public String getFollowupsheet() {
        return followupsheet;
    }

    public void setFollowupsheet(String followupsheet) {
        this.followupsheet = followupsheet;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getSurgerywhere() {
        return surgerywhere;
    }

    public void setSurgerywhere(String surgerywhere) {
        this.surgerywhere = surgerywhere;
    }

    public String getSurgerywherewhat() {
        return surgerywherewhat;
    }

    public void setSurgerywherewhat(String surgerywherewhat) {
        this.surgerywherewhat = surgerywherewhat;
    }

    public String getHomerecommend() {
        return homerecommend;
    }

    public void setHomerecommend(String homerecommend) {
        this.homerecommend = homerecommend;
    }

    public String getHomerecommendwhat() {
        return homerecommendwhat;
    }

    public void setHomerecommendwhat(String homerecommendwhat) {
        this.homerecommendwhat = homerecommendwhat;
    }

    public String getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(String repairCost) {
        this.repairCost = repairCost;
    }

    public String getPatientContributionRepair() {
        return patientContributionRepair;
    }

    public void setPatientContributionRepair(String patientContributionRepair) {
        this.patientContributionRepair = patientContributionRepair;
    }

    public String getDonorContributionRepair() {
        return donorContributionRepair;
    }

    public void setDonorContributionRepair(String donorContributionRepair) {
        this.donorContributionRepair = donorContributionRepair;
    }

    public String getRepairCostCbr() {
        return repairCostCbr;
    }

    public void setRepairCostCbr(String repairCostCbr) {
        this.repairCostCbr = repairCostCbr;
    }

    public String getPatientContributionCbr() {
        return patientContributionCbr;
    }

    public void setPatientContributionCbr(String patientContributionCbr) {
        this.patientContributionCbr = patientContributionCbr;
    }

    public String getDonorContributionCbr() {
        return donorContributionCbr;
    }

    public void setDonorContributionCbr(String donorContributionCbr) {
        this.donorContributionCbr = donorContributionCbr;
    }

    public String getIhp() {
        return ihp;
    }

    public void setIhp(String ihp) {
        this.ihp = ihp;
    }

    public String getIhpDoc() {
        return ihpDoc;
    }

    public void setIhpDoc(String ihpDoc) {
        this.ihpDoc = ihpDoc;
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

    public String getSpeechHearId() {
        return speechHearId;
    }

    public void setSpeechHearId(String speechHearId) {
        this.speechHearId = speechHearId;
    }

    public String getSpeechLangDev() {
        return speechLangDev;
    }

    public void setSpeechLangDev(String speechLangDev) {
        this.speechLangDev = speechLangDev;
    }

    public String getOpmeDd() {
        return opmeDd;
    }

    public void setOpmeDd(String opmeDd) {
        this.opmeDd = opmeDd;
    }

    public String getIfAbnormal() {
        return ifAbnormal;
    }

    public void setIfAbnormal(String ifAbnormal) {
        this.ifAbnormal = ifAbnormal;
    }

    public String getSpeechArticulation() {
        return speechArticulation;
    }

    public void setSpeechArticulation(String speechArticulation) {
        this.speechArticulation = speechArticulation;
    }

    public String getIfMisarticulation() {
        return ifMisarticulation;
    }

    public void setIfMisarticulation(String ifMisarticulation) {
        this.ifMisarticulation = ifMisarticulation;
    }

    public String getFluency() {
        return fluency;
    }

    public void setFluency(String fluency) {
        this.fluency = fluency;
    }

    public String getVoiceQuality() {
        return voiceQuality;
    }

    public void setVoiceQuality(String voiceQuality) {
        this.voiceQuality = voiceQuality;
    }

    public String getVoicePitch() {
        return voicePitch;
    }

    public void setVoicePitch(String voicePitch) {
        this.voicePitch = voicePitch;
    }

    public String getVoiceLoudness() {
        return voiceLoudness;
    }

    public void setVoiceLoudness(String voiceLoudness) {
        this.voiceLoudness = voiceLoudness;
    }

    public String getModeCommunication() {
        return modeCommunication;
    }

    public void setModeCommunication(String modeCommunication) {
        this.modeCommunication = modeCommunication;
    }

    public String getLanguageComprehension() {
        return languageComprehension;
    }

    public void setLanguageComprehension(String languageComprehension) {
        this.languageComprehension = languageComprehension;
    }

    public String getLanguageExpression() {
        return languageExpression;
    }

    public void setLanguageExpression(String languageExpression) {
        this.languageExpression = languageExpression;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getRightOtoscopy() {
        return rightOtoscopy;
    }

    public void setRightOtoscopy(String rightOtoscopy) {
        this.rightOtoscopy = rightOtoscopy;
    }

    public String getLeftOtoscopy() {
        return leftOtoscopy;
    }

    public void setLeftOtoscopy(String leftOtoscopy) {
        this.leftOtoscopy = leftOtoscopy;
    }

    public String getPureToneAudioRight() {
        return pureToneAudioRight;
    }

    public void setPureToneAudioRight(String pureToneAudioRight) {
        this.pureToneAudioRight = pureToneAudioRight;
    }

    public String getPureToneAudioLeft() {
        return pureToneAudioLeft;
    }

    public void setPureToneAudioLeft(String pureToneAudioLeft) {
        this.pureToneAudioLeft = pureToneAudioLeft;
    }

    public String getSpeechAudioTest() {
        return speechAudioTest;
    }

    public void setSpeechAudioTest(String speechAudioTest) {
        this.speechAudioTest = speechAudioTest;
    }

    public String getEarDischarge() {
        return earDischarge;
    }

    public void setEarDischarge(String earDischarge) {
        this.earDischarge = earDischarge;
    }

    public String getLingSound() {
        return lingSound;
    }

    public void setLingSound(String lingSound) {
        this.lingSound = lingSound;
    }

    public String getHearingModelRight() {
        return hearingModelRight;
    }

    public void setHearingModelRight(String hearingModelRight) {
        this.hearingModelRight = hearingModelRight;
    }

    public String getHearingModelLeft() {
        return hearingModelLeft;
    }

    public void setHearingModelLeft(String hearingModelLeft) {
        this.hearingModelLeft = hearingModelLeft;
    }

    public String getEarMouldRight() {
        return earMouldRight;
    }

    public void setEarMouldRight(String earMouldRight) {
        this.earMouldRight = earMouldRight;
    }

    public String getEarMouldLeft() {
        return earMouldLeft;
    }

    public void setEarMouldLeft(String earMouldLeft) {
        this.earMouldLeft = earMouldLeft;
    }

    public String getSpecialistsRemark() {
        return specialistsRemark;
    }

    public void setSpecialistsRemark(String specialistsRemark) {
        this.specialistsRemark = specialistsRemark;
    }

    public String getHealthServiceId() {
        return healthServiceId;
    }

    public void setHealthServiceId(String healthServiceId) {
        this.healthServiceId = healthServiceId;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public List<String> getIhpdocs() {
        return ihpdocs;
    }

    public void setIhpdocs(List<String> ihpdocs) {
        this.ihpdocs = ihpdocs;
    }

    public List<String> getServiceName() {
        return serviceName;
    }

    public void setServiceName(List<String> serviceName) {
        this.serviceName = serviceName;
    }
}

