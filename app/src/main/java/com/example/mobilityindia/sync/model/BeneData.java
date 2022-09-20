package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "beneficiary_table")
public class BeneData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    @NonNull
    public int Id;

    @ColumnInfo(name = "beneficiary_id")
    @SerializedName("beneficiary_id")
    @Expose
    public String beneficiaryId;

    @ColumnInfo(name = "temp_id")
    @SerializedName("temp_id")
    @Expose
    public String tempId;

    @ColumnInfo(name = "registration_date")
    @SerializedName("registration_date")
    @Expose
    public String registrationDate;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    public String name;

    @ColumnInfo(name = "parent_name")
    @SerializedName("parent_name")
    @Expose
    public String parentName;

    @ColumnInfo(name = "relation")
    @SerializedName("relation")
    @Expose
    public String relation;

    @ColumnInfo(name = "dob")
    @SerializedName("dob")
    @Expose
    public String dob;

    @ColumnInfo(name = "age")
    @SerializedName("age")
    @Expose
    public String age;

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    @Expose
    public String gender;

    @ColumnInfo(name = "caste")
    @SerializedName("caste")
    @Expose
    public String caste;

    @ColumnInfo(name = "religion")
    @SerializedName("religion")
    @Expose
    public String religion;

    @ColumnInfo(name = "adhaar_no")
    @SerializedName("adhaar_no")
    @Expose
    public String adhaarNo;

    @ColumnInfo(name = "annual_income")
    @SerializedName("annual_income")
    @Expose
    public String annualIncome;

    @ColumnInfo(name = "economic_status")
    @SerializedName("economic_status")
    @Expose
    public String economicStatus;

    @ColumnInfo(name = "marital_status")
    @SerializedName("marital_status")
    @Expose
    public String maritalStatus;

    @ColumnInfo(name = "education")
    @SerializedName("education")
    @Expose
    public String education;

    @ColumnInfo(name = "occupation")
    @SerializedName("occupation")
    @Expose
    public String occupation;

    @ColumnInfo(name = "type_of_disability")
    @SerializedName("type_of_disability")
    @Expose
    public String typeOfDisability;

    @ColumnInfo(name = "type_of_sub_disability")
    @SerializedName("type_of_sub_disability")
    @Expose
    public String typeOfSubDisability;

    @ColumnInfo(name = "percentage_of_disability")
    @SerializedName("percentage_of_disability")
    @Expose
    public String percentageOfDisability;

    @ColumnInfo(name = "id_card_no")
    @SerializedName("id_card_no")
    @Expose
    public String idCardNo;

    @ColumnInfo(name = "php_amount")
    @SerializedName("php_amount")
    @Expose
    public String phpAmount;

    @ColumnInfo(name = "type_of_beneficiary")
    @SerializedName("type_of_beneficiary")
    @Expose
    public String typeOfBeneficiary;

    @ColumnInfo(name = "purpose_of_visit")
    @SerializedName("purpose_of_visit")
    @Expose
    public String purposeOfVisit;

    @ColumnInfo(name = "purpose_visit_details")
    @SerializedName("purpose_visit_details")
    @Expose
    public String purposeVisitDetails;

    @ColumnInfo(name = "have_bank_acc")
    @SerializedName("have_bank_acc")
    @Expose
    public String haveBankAcc;

    @ColumnInfo(name = "acc_num")
    @SerializedName("acc_num")
    @Expose
    public String accNum;

    @ColumnInfo(name = "acc_holder_name")
    @SerializedName("acc_holder_name")
    @Expose
    public String accHolderName;

    @ColumnInfo(name = "ifsc")
    @SerializedName("ifsc")
    @Expose
    public String ifsc;

    @ColumnInfo(name = "acc_type")
    @SerializedName("acc_type")
    @Expose
    public String accType;

    @ColumnInfo(name = "name_of_pwd_cwd")
    @SerializedName("name_of_pwd_cwd")
    @Expose
    public String nameOfPwdCwd;

    @ColumnInfo(name = "created_by")
    @SerializedName("created_by")
    @Expose
    public String createdBy;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @ColumnInfo(name = "updated_by")
    @SerializedName("updated_by")
    @Expose
    public String updatedBy;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @ColumnInfo(name = "is_deleted")
    @SerializedName("is_deleted")
    @Expose
    public String isDeleted;

    @ColumnInfo(name = "deleted_at")
    @SerializedName("deleted_at")
    @Expose
    public String deletedAt;

    @ColumnInfo(name = "ben_other_id")
    @SerializedName("ben_other_id")
    @Expose
    public String benOtherId;

    @ColumnInfo(name = "registration_no")
    @SerializedName("registration_no")
    @Expose
    public String registrationNo;

    @ColumnInfo(name = "benificiary_id")
    @SerializedName("benificiary_id")
    @Expose
    public String benificiaryId;

    @ColumnInfo(name = "village_id")
    @SerializedName("village_id")
    @Expose
    public String villageId;

    @ColumnInfo(name = "address")
    @SerializedName("address")
    @Expose
    public String address;

    @ColumnInfo(name = "school_anganwadi_name")
    @SerializedName("school_anganwadi_name")
    @Expose
    public String schoolAnganwadiName;

    @ColumnInfo(name = "contact_no")
    @SerializedName("contact_no")
    @Expose
    public String contactNo;

    @ColumnInfo(name = "contact_no_other")
    @SerializedName("contact_no_other")
    @Expose
    public String contactNoOther;

    @ColumnInfo(name = "email_id")
    @SerializedName("email_id")
    @Expose
    public String emailId;

    @ColumnInfo(name = "ration_card_no")
    @SerializedName("ration_card_no")
    @Expose
    public String rationCardNo;

    @ColumnInfo(name = "sanitation_toilet")
    @SerializedName("sanitation_toilet")
    @Expose
    public String sanitationToilet;

    @ColumnInfo(name = "appliances")
    @SerializedName("appliances")
    @Expose
    public String appliances;

    @ColumnInfo(name = "surgery")
    @SerializedName("surgery")
    @Expose
    public String surgery;

    @ColumnInfo(name = "govt_facilities")
    @SerializedName("govt_facilities")
    @Expose
    public String govtFacilities;

    @ColumnInfo(name = "govt_facility_mention")
    @SerializedName("govt_facility_mention")
    @Expose
    public String govtFacilityMention;

    @ColumnInfo(name = "family_member")
    @SerializedName("family_member")
    @Expose
    public String familyMember;

    @ColumnInfo(name = "family_member_adults")
    @SerializedName("family_member_adults")
    @Expose
    public String familyMemberAdults;

    @ColumnInfo(name = "family_member_child_m")
    @SerializedName("family_member_child_m")
    @Expose
    public String familyMemberChildM;

    @ColumnInfo(name = "family_member_child_f")
    @SerializedName("family_member_child_f")
    @Expose
    public String familyMemberChildF;

    @ColumnInfo(name = "children_undergoing_education_m")
    @SerializedName("children_undergoing_education_m")
    @Expose
    public String childrenUndergoingEducationM;

    @ColumnInfo(name = "children_undergoing_education_f")
    @SerializedName("children_undergoing_education_f")
    @Expose
    public String childrenUndergoingEducationF;

    @ColumnInfo(name = "dropouts_less_14_m")
    @SerializedName("dropouts_less_14_m")
    @Expose
    public String dropoutsLess14M;

    @ColumnInfo(name = "dropouts_less_14_f")
    @SerializedName("dropouts_less_14_f")
    @Expose
    public String dropoutsLess14F;

    @ColumnInfo(name = "dropouts_14_18_m")
    @SerializedName("dropouts_14_18_m")
    @Expose
    public String dropouts1418M;

    @ColumnInfo(name = "dropouts_14_18_f")
    @SerializedName("dropouts_14_18_f")
    @Expose
    public String dropouts1418F;

    @ColumnInfo(name = "earning_members_fly_m")
    @SerializedName("earning_members_fly_m")
    @Expose
    public String earningMembersFlyM;

    @ColumnInfo(name = "earning_members_fly_f")
    @SerializedName("earning_members_fly_f")
    @Expose
    public String earningMembersFlyF;

    @ColumnInfo(name = "caste_name")
    @SerializedName("caste_name")
    @Expose
    public String casteName;

    @ColumnInfo(name = "religion_name")
    @SerializedName("religion_name")
    @Expose
    public String religionName;

    @ColumnInfo(name = "annual_name")
    @SerializedName("annual_name")
    @Expose
    public String annualName;

    @ColumnInfo(name = "economic_name")
    @SerializedName("economic_name")
    @Expose
    public String economicName;

    @ColumnInfo(name = "marital_name")
    @SerializedName("marital_name")
    @Expose
    public String maritalName;

    @ColumnInfo(name = "education_name")
    @SerializedName("education_name")
    @Expose
    public String educationName;

    @ColumnInfo(name = "occupation_name")
    @SerializedName("occupation_name")
    @Expose
    public String occupationName;

    @ColumnInfo(name = "disability_name")
    @SerializedName("disability_name")
    @Expose
    public String disabilityName;

    @ColumnInfo(name = "cbo_id")
    @SerializedName("cbo_id")
    @Expose
    public String cboId;

    @ColumnInfo(name = "benef_id")
    @SerializedName("benef_id")
    @Expose
    public String benefId;

    @ColumnInfo(name = "wheather_cbo_member")
    @SerializedName("wheather_cbo_member")
    @Expose
    public String wheatherCboMember;

    @ColumnInfo(name = "cbo_name")
    @SerializedName("cbo_name")
    @Expose
    public String cboName;

    @ColumnInfo(name = "start_year_of_cbo")
    @SerializedName("start_year_of_cbo")
    @Expose
    public String startYearOfCbo;

    @ColumnInfo(name = "year_join_cbo")
    @SerializedName("year_join_cbo")
    @Expose
    public String yearJoinCbo;

    @ColumnInfo(name = "village_name")
    @SerializedName("village_name")
    @Expose
    public String villageName;

    @ColumnInfo(name = "gp_name")
    @SerializedName("gp_name")
    @Expose
    public String gpName;

    @ColumnInfo(name = "hobli_name")
    @SerializedName("hobli_name")
    @Expose
    public String hobliName;

    @ColumnInfo(name = "block_name")
    @SerializedName("block_name")
    @Expose
    public String blockName;

    @ColumnInfo(name = "district_name")
    @SerializedName("district_name")
    @Expose
    public String districtName;

    @ColumnInfo(name = "state_name")
    @SerializedName("state_name")
    @Expose
    public String stateName;

    @ColumnInfo(name = "visit_name")
    @SerializedName("visit_name")
    @Expose
    public List<String> visitName = null;

    @ColumnInfo(name = "userid")
    @SerializedName("userid")
    @Expose
    public String userId;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    @Expose
    public String flag;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getAdhaarNo() {
        return adhaarNo;
    }

    public void setAdhaarNo(String adhaarNo) {
        this.adhaarNo = adhaarNo;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getEconomicStatus() {
        return economicStatus;
    }

    public void setEconomicStatus(String economicStatus) {
        this.economicStatus = economicStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getTypeOfDisability() {
        return typeOfDisability;
    }

    public void setTypeOfDisability(String typeOfDisability) {
        this.typeOfDisability = typeOfDisability;
    }

    public String getTypeOfSubDisability() {
        return typeOfSubDisability;
    }

    public void setTypeOfSubDisability(String typeOfSubDisability) {
        this.typeOfSubDisability = typeOfSubDisability;
    }

    public String getPercentageOfDisability() {
        return percentageOfDisability;
    }

    public void setPercentageOfDisability(String percentageOfDisability) {
        this.percentageOfDisability = percentageOfDisability;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPhpAmount() {
        return phpAmount;
    }

    public void setPhpAmount(String phpAmount) {
        this.phpAmount = phpAmount;
    }

    public String getTypeOfBeneficiary() {
        return typeOfBeneficiary;
    }

    public void setTypeOfBeneficiary(String typeOfBeneficiary) {
        this.typeOfBeneficiary = typeOfBeneficiary;
    }

    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }

    public String getPurposeVisitDetails() {
        return purposeVisitDetails;
    }

    public void setPurposeVisitDetails(String purposeVisitDetails) {
        this.purposeVisitDetails = purposeVisitDetails;
    }

    public String getHaveBankAcc() {
        return haveBankAcc;
    }

    public void setHaveBankAcc(String haveBankAcc) {
        this.haveBankAcc = haveBankAcc;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getNameOfPwdCwd() {
        return nameOfPwdCwd;
    }

    public void setNameOfPwdCwd(String nameOfPwdCwd) {
        this.nameOfPwdCwd = nameOfPwdCwd;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getBenOtherId() {
        return benOtherId;
    }

    public void setBenOtherId(String benOtherId) {
        this.benOtherId = benOtherId;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getBenificiaryId() {
        return benificiaryId;
    }

    public void setBenificiaryId(String benificiaryId) {
        this.benificiaryId = benificiaryId;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolAnganwadiName() {
        return schoolAnganwadiName;
    }

    public void setSchoolAnganwadiName(String schoolAnganwadiName) {
        this.schoolAnganwadiName = schoolAnganwadiName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactNoOther() {
        return contactNoOther;
    }

    public void setContactNoOther(String contactNoOther) {
        this.contactNoOther = contactNoOther;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRationCardNo() {
        return rationCardNo;
    }

    public void setRationCardNo(String rationCardNo) {
        this.rationCardNo = rationCardNo;
    }

    public String getSanitationToilet() {
        return sanitationToilet;
    }

    public void setSanitationToilet(String sanitationToilet) {
        this.sanitationToilet = sanitationToilet;
    }

    public String getAppliances() {
        return appliances;
    }

    public void setAppliances(String appliances) {
        this.appliances = appliances;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getGovtFacilities() {
        return govtFacilities;
    }

    public void setGovtFacilities(String govtFacilities) {
        this.govtFacilities = govtFacilities;
    }

    public String getGovtFacilityMention() {
        return govtFacilityMention;
    }

    public void setGovtFacilityMention(String govtFacilityMention) {
        this.govtFacilityMention = govtFacilityMention;
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getFamilyMemberAdults() {
        return familyMemberAdults;
    }

    public void setFamilyMemberAdults(String familyMemberAdults) {
        this.familyMemberAdults = familyMemberAdults;
    }

    public String getFamilyMemberChildM() {
        return familyMemberChildM;
    }

    public void setFamilyMemberChildM(String familyMemberChildM) {
        this.familyMemberChildM = familyMemberChildM;
    }

    public String getFamilyMemberChildF() {
        return familyMemberChildF;
    }

    public void setFamilyMemberChildF(String familyMemberChildF) {
        this.familyMemberChildF = familyMemberChildF;
    }

    public String getChildrenUndergoingEducationM() {
        return childrenUndergoingEducationM;
    }

    public void setChildrenUndergoingEducationM(String childrenUndergoingEducationM) {
        this.childrenUndergoingEducationM = childrenUndergoingEducationM;
    }

    public String getChildrenUndergoingEducationF() {
        return childrenUndergoingEducationF;
    }

    public void setChildrenUndergoingEducationF(String childrenUndergoingEducationF) {
        this.childrenUndergoingEducationF = childrenUndergoingEducationF;
    }

    public String getDropoutsLess14M() {
        return dropoutsLess14M;
    }

    public void setDropoutsLess14M(String dropoutsLess14M) {
        this.dropoutsLess14M = dropoutsLess14M;
    }

    public String getDropoutsLess14F() {
        return dropoutsLess14F;
    }

    public void setDropoutsLess14F(String dropoutsLess14F) {
        this.dropoutsLess14F = dropoutsLess14F;
    }

    public String getDropouts1418M() {
        return dropouts1418M;
    }

    public void setDropouts1418M(String dropouts1418M) {
        this.dropouts1418M = dropouts1418M;
    }

    public String getDropouts1418F() {
        return dropouts1418F;
    }

    public void setDropouts1418F(String dropouts1418F) {
        this.dropouts1418F = dropouts1418F;
    }

    public String getEarningMembersFlyM() {
        return earningMembersFlyM;
    }

    public void setEarningMembersFlyM(String earningMembersFlyM) {
        this.earningMembersFlyM = earningMembersFlyM;
    }

    public String getEarningMembersFlyF() {
        return earningMembersFlyF;
    }

    public void setEarningMembersFlyF(String earningMembersFlyF) {
        this.earningMembersFlyF = earningMembersFlyF;
    }

    public String getCasteName() {
        return casteName;
    }

    public void setCasteName(String casteName) {
        this.casteName = casteName;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getAnnualName() {
        return annualName;
    }

    public void setAnnualName(String annualName) {
        this.annualName = annualName;
    }

    public String getEconomicName() {
        return economicName;
    }

    public void setEconomicName(String economicName) {
        this.economicName = economicName;
    }

    public String getMaritalName() {
        return maritalName;
    }

    public void setMaritalName(String maritalName) {
        this.maritalName = maritalName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getDisabilityName() {
        return disabilityName;
    }

    public void setDisabilityName(String disabilityName) {
        this.disabilityName = disabilityName;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public String getBenefId() {
        return benefId;
    }

    public void setBenefId(String benefId) {
        this.benefId = benefId;
    }

    public String getWheatherCboMember() {
        return wheatherCboMember;
    }

    public void setWheatherCboMember(String wheatherCboMember) {
        this.wheatherCboMember = wheatherCboMember;
    }

    public String getCboName() {
        return cboName;
    }

    public void setCboName(String cboName) {
        this.cboName = cboName;
    }

    public String getStartYearOfCbo() {
        return startYearOfCbo;
    }

    public void setStartYearOfCbo(String startYearOfCbo) {
        this.startYearOfCbo = startYearOfCbo;
    }

    public String getYearJoinCbo() {
        return yearJoinCbo;
    }

    public void setYearJoinCbo(String yearJoinCbo) {
        this.yearJoinCbo = yearJoinCbo;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getHobliName() {
        return hobliName;
    }

    public void setHobliName(String hobliName) {
        this.hobliName = hobliName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<String> getVisitName() {
        return visitName;
    }

    public void setVisitName(List<String> visitName) {
        this.visitName = visitName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
