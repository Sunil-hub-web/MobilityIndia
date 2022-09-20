package com.example.mobilityindia.retrofit;

import com.example.mobilityindia.actionplan.model.ActionPlanResponseL;
import com.example.mobilityindia.beneficarylist.beneficaryresponce.BeneficaryResponse;
import com.example.mobilityindia.changepin.view.changepinresponce.ChangepinresponceResponse;
import com.example.mobilityindia.constant.BaseResponse;
import com.example.mobilityindia.createpin.view.createpinresponce.ChangepinResponse;
import com.example.mobilityindia.education.educationresponce.EducationResponse;
import com.example.mobilityindia.forgetpin.view.forgetpinresponce.ForgetPinResponse;
import com.example.mobilityindia.health.healthresponce.HealthResponse;
import com.example.mobilityindia.livelihood.livelihoodresponce.LivelihoodresponceResponse;
import com.example.mobilityindia.loginpage.loginresponce.LoginDataResponse;
import com.example.mobilityindia.myprofile.view.myprofileresponce.MyProfileResponse;
import com.example.mobilityindia.otpverification.view.otpverifyresponce.VerifyotpResponse;
import com.example.mobilityindia.otpverification.view.resentotpresponce.ResentotpResponse;
import com.example.mobilityindia.social.socialresponce.SocialResponse;
import com.example.mobilityindia.syn1.view.allresponse.actionplan.Example_ActionPlan;
import com.example.mobilityindia.syn1.view.allresponse.activityreport.Example_ActivityReport;
import com.example.mobilityindia.syn1.view.allresponse.activityreport.ExapmleActivityReport;
import com.example.mobilityindia.syn1.view.allresponse.benificiary.Example_Benificiary;
import com.example.mobilityindia.syn1.view.allresponse.block.Example_Block;
import com.example.mobilityindia.syn1.view.allresponse.clickinout.Example_ClickInOut;
import com.example.mobilityindia.syn1.view.allresponse.distic.Example_Distic;
import com.example.mobilityindia.syn1.view.allresponse.downloadpdf.DownloadPDF;
import com.example.mobilityindia.syn1.view.allresponse.eduction.Example_Eduction;
import com.example.mobilityindia.syn1.view.allresponse.gp.Example_Gp;
import com.example.mobilityindia.syn1.view.allresponse.health.Health_Example;
import com.example.mobilityindia.syn1.view.allresponse.livelihood.Livelihood_Example;
import com.example.mobilityindia.syn1.view.allresponse.social.Social_Example;
import com.example.mobilityindia.syn1.view.allresponse.village.Example_Village;
import com.example.mobilityindia.sync.model.ActivityReportResponse;
import com.example.mobilityindia.sync.model.ServiceResponse;
import com.example.mobilityindia.sync.model.SubDisabilityResponse;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRequest
{
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginDataResponse> login(@FieldMap Map<String,Object> user);


    @FormUrlEncoded
    @POST("api/getbenlist")
    Call<Example_Benificiary> getBenificiaryData(@Header("Authorization") String token, @FieldMap Map<String, Object> belelist);


    ////////ActionPlan////////////////
    @FormUrlEncoded
    @POST("api/getactionplan")
    Call<Example_ActionPlan> getActionPlanData(@FieldMap Map<String, Object> user);


    /////ActivityReport////////
    @FormUrlEncoded
    @POST("api/getactivityreport")
    Call<Example_ActivityReport> getActivityReport(@FieldMap Map<String, Object> user);

    //@FormUrlEncoded
    @POST("api/activityreport/own")
    Call<ExapmleActivityReport> getActivityReport1(@Body Map<String, Object> user);


    @Headers({"Accept:application/json"})
    @GET("api/getactivityreportattendance")
    Call<ActivityReportResponse> getActivityReportAttendenceData(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/listService")
    Call<ServiceResponse> getServiceapiData(@Header("Authorization") String token, @FieldMap Map<String, Object> servicelist);


    @Headers({"Accept:application/json"})
    @GET("api/getstate")
    Call<JsonObject> getStateMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getdistrict")
    Call<Example_Distic> getDistMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getblock")
    Call<Example_Block> getBlockMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getgp")
    Call<Example_Gp> getGpMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getvillage")
    Call<Example_Village> getVillageMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getcaste")
    Call<JsonObject> getCasteMaster(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/getoccupation")
    Call<JsonObject> getMasterOccuption(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getreligion")
    Call<JsonObject> getReligionMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/geteconomic")
    Call<JsonObject> getEconomicMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getannualincome")
    Call<JsonObject> getAnualincomeMaster(@Header("Authorization") String token);


    @Headers({"Accept:application/json"})
    @GET("api/getmaritalstatus")
    Call<JsonObject> getMaritialStatusMaster(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/geteducation")
    Call<JsonObject> getEducationMaster(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/getoccupation")
    Call<JsonObject> getOcupationMaster(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/getdisability")
    Call<JsonObject> getDisabilityMaster(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/getsubdisability")
    Call<SubDisabilityResponse> getSubDisabilityMaster(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/getpurposevisit")
    Call<JsonObject> getPurposeVisitMaster(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/gethobli")
    Call<JsonObject> getHoboliMaster(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST("api/activityreport/update_attendance")
    Call<ActionPlanResponseL> addActionPlan(@Header("Authorization") String token, @FieldMap Map<String,Object> actionplan);

    @FormUrlEncoded
    @POST("api/activityreport/update_own")
    Call<BaseResponse> addActivityReport(@Header("Authorization") String token, @FieldMap Map<String,Object> actionplan);


    @FormUrlEncoded
    @POST("api/updateactivityreport")
    Call<BaseResponse> updateActivityReport(@Header("Authorization") String token, @FieldMap Map<String,Object> actionplan);


    @FormUrlEncoded
    @POST("api/addben")
    Call<BeneficaryResponse> addbeneficary(@Header("Authorization") String token,@FieldMap Map<String,Object> user);

    @Headers({"Accept:application/json"})
    @GET("api/get_user/{userId}")
    Call<MyProfileResponse> myprofile(@Header("Authorization") String token, @Path("userId") String userId);

    @POST("api/reset_pin_post")
    Call<ChangepinresponceResponse> changemypin(@Header("Authorization") String token,@Body Map<String,Object> user);


    @FormUrlEncoded
    @POST("api/forgotpin")
    Call<ForgetPinResponse> forgetpin(@FieldMap Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/verifyOTP")
    Call<VerifyotpResponse> verifyotp(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/resendOTP")
    Call<ResentotpResponse> resendotp(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/createPIN")
    Call<ChangepinResponse> createpin(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/addLivelihood")
    Call<LivelihoodresponceResponse> addlivelihood(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    //@FormUrlEncoded
    @POST("api/addSocial")
    Call<SocialResponse> addSocialData(@Header("Authorization") String token, @Body Map<String,Object> user);

    //@FormUrlEncoded
    @POST("api/addHealth")
    Call<HealthResponse> addHealthData(@Header("Authorization") String token, @Body Map<String,Object> user);

    //@FormUrlEncoded
    @POST("api/addEducation")
    Call<EducationResponse> addEducationData(@Header("Authorization") String token, @Body Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/listEducationService")
    Call<Example_Eduction> listEducationService(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    @Headers({"Accept:application/json"})
    @GET("api/gettraining")
    Call<JsonObject> gettraining(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/getservice")
    Call<JsonObject> getHealthView(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("api/getdevice")
    Call<JsonObject> getHealthDevices(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/listService")
    Call<Health_Example> listHealthService(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/listLivelihoodService")
    Call<Livelihood_Example> listLivelihoodService(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/clockInList")
    Call<Example_ClickInOut> listclockInList(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    @FormUrlEncoded
    @POST("api/listSocialService")
    Call<Social_Example> listSocialService(@Header("Authorization") String token, @FieldMap Map<String,Object> user);

    //@FormUrlEncoded
    @POST("api/activityreport/download_pdf")
    Call<DownloadPDF> reportDownLoadPdf(@Header("Authorization") String token, @Body Map<String,Object> user);

    //@FormUrlEncoded
    @POST("api/actionplan/download_pdf")
    Call<DownloadPDF> actionDownLoadPdf(@Header("Authorization") String token, @Body Map<String,Object> user);


}
