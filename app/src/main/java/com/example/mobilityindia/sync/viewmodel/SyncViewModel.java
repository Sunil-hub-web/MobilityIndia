package com.example.mobilityindia.sync.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.sync.model.ActionPlanResponse;
import com.example.mobilityindia.sync.model.ActivityReportResponse;
import com.example.mobilityindia.sync.model.BeneResponse;
import com.example.mobilityindia.sync.model.BlockResponse;
import com.example.mobilityindia.sync.model.DistResponse;
import com.example.mobilityindia.sync.model.GPResponse;
import com.example.mobilityindia.sync.model.ServiceResponse;
import com.example.mobilityindia.sync.model.SubDisabilityResponse;
import com.example.mobilityindia.sync.model.VillageResponse;
import com.example.mobilityindia.sync.model.WorkPlanResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncViewModel extends ViewModel {

    private MutableLiveData<String> pageNavigate;

    public MutableLiveData<Integer> isprogress = new MutableLiveData<>();
    private ApiRequest apiRequest;
    private MutableLiveData<BeneResponse> BeneficiaryMutableLiveData;
    private MutableLiveData<ActionPlanResponse> ActionplanMutableLiveData;
    private MutableLiveData<WorkPlanResponse> WorkMutableLiveData;
    private MutableLiveData<ActivityReportResponse> activityReportMutableLiveData;

    private MutableLiveData<ServiceResponse> socialMutableLiveData;


    private MutableLiveData<JsonObject> StateMutableLiveData;
    private MutableLiveData<DistResponse> DisticMutableLiveData;

    private MutableLiveData<BlockResponse> BlockMutableLiveData;
    private MutableLiveData<GPResponse> GPMutableLiveData;
    private MutableLiveData<VillageResponse> VillageMutableLiveData;

    private MutableLiveData<JsonObject> CasteMutableLiveData;
    private MutableLiveData<JsonObject> ReligionMutableLiveData;
    private MutableLiveData<JsonObject> EconomicMutableLiveData;
    private MutableLiveData<JsonObject> AnnualIncomeMutableLiveData;
    private MutableLiveData<JsonObject> MaritialStatusMutableLiveData;
    private MutableLiveData<JsonObject> EducationMutableLiveData;
    private MutableLiveData<JsonObject> OccupationMutableLiveData;
    private MutableLiveData<JsonObject> TypeDisabilityMutableLiveData;
    private MutableLiveData<SubDisabilityResponse> SubDisabilityMutableLiveData;
    private MutableLiveData<JsonObject> ProposeVisitMutableLiveData;
    private MutableLiveData<JsonObject> HoboliMutableLiveData;

    public MutableLiveData<String> getPageNavigate() {
        isprogress.setValue(10);
        if (pageNavigate == null) {
            pageNavigate = new MutableLiveData<>();
        }
        return pageNavigate;

    }

    public MutableLiveData<JsonObject> getStateMasterData() {
        //isprogress.setValue(10);
        if (StateMutableLiveData == null) {
            StateMutableLiveData = new MutableLiveData<>();
        }
        return StateMutableLiveData;
    }

    public MutableLiveData<DistResponse> getDistMasterData() {
        //isprogress.setValue(10);
        if (DisticMutableLiveData == null) {
            DisticMutableLiveData = new MutableLiveData<>();
        }
        return DisticMutableLiveData;
    }


    public MutableLiveData<BlockResponse> getBlockMasterData() {
        //isprogress.setValue(10);
        if (BlockMutableLiveData == null) {
            BlockMutableLiveData = new MutableLiveData<>();
        }
        return BlockMutableLiveData;
    }

    public MutableLiveData<GPResponse> getGPMasterData() {
        //isprogress.setValue(10);
        if (GPMutableLiveData == null) {
            GPMutableLiveData = new MutableLiveData<>();
        }
        return GPMutableLiveData;
    }

    public MutableLiveData<VillageResponse> getVillageMasterData() {
        //isprogress.setValue(10);
        if (VillageMutableLiveData == null) {
            VillageMutableLiveData = new MutableLiveData<>();
        }
        return VillageMutableLiveData;
    }

    public MutableLiveData<JsonObject> getCasteMasterData() {
        //isprogress.setValue(10);
        if (CasteMutableLiveData == null) {
            CasteMutableLiveData = new MutableLiveData<>();
        }
        return CasteMutableLiveData;
    }

    public MutableLiveData<JsonObject> getReligionMasterData() {
        //isprogress.setValue(10);
        if (ReligionMutableLiveData == null) {
            ReligionMutableLiveData = new MutableLiveData<>();
        }
        return ReligionMutableLiveData;
    }

    public MutableLiveData<JsonObject> getEconomicMasterData() {
        //isprogress.setValue(10);
        if (EconomicMutableLiveData == null) {
            EconomicMutableLiveData = new MutableLiveData<>();
        }
        return EconomicMutableLiveData;
    }

    public MutableLiveData<JsonObject> getAnualIncomeMasterData() {
        //isprogress.setValue(10);
        if (AnnualIncomeMutableLiveData == null) {
            AnnualIncomeMutableLiveData = new MutableLiveData<>();
        }
        return AnnualIncomeMutableLiveData;
    }

    public MutableLiveData<JsonObject> getmeritalStatusMasterData() {
        //isprogress.setValue(10);
        if (MaritialStatusMutableLiveData == null) {
            MaritialStatusMutableLiveData = new MutableLiveData<>();
        }
        return MaritialStatusMutableLiveData;
    }

    public MutableLiveData<JsonObject> getEducationMasterData() {
        //isprogress.setValue(10);
        if (EducationMutableLiveData == null) {
            EducationMutableLiveData = new MutableLiveData<>();
        }
        return EducationMutableLiveData;
    }

    public MutableLiveData<JsonObject> getOcupationMasterData() {
        //isprogress.setValue(10);
        if (OccupationMutableLiveData == null) {
            OccupationMutableLiveData = new MutableLiveData<>();
        }
        return OccupationMutableLiveData;
    }

    public MutableLiveData<JsonObject> gettypeDisMasterData() {
        //isprogress.setValue(10);
        if (TypeDisabilityMutableLiveData == null) {
            TypeDisabilityMutableLiveData = new MutableLiveData<>();
        }
        return TypeDisabilityMutableLiveData;
    }

    public MutableLiveData<SubDisabilityResponse> getSubDsiabilityMasterData() {

        if (SubDisabilityMutableLiveData == null) {
            SubDisabilityMutableLiveData = new MutableLiveData<>();
        }
        return SubDisabilityMutableLiveData;
    }

    public MutableLiveData<JsonObject> getProposeVisitMasterData() {

        if (ProposeVisitMutableLiveData == null) {
            ProposeVisitMutableLiveData = new MutableLiveData<>();
        }
        return ProposeVisitMutableLiveData;
    }

    public MutableLiveData<JsonObject> getHoboliMasterData() {

        if (HoboliMutableLiveData == null) {
            HoboliMutableLiveData = new MutableLiveData<>();
        }
        return HoboliMutableLiveData;
    }


    public MutableLiveData<BeneResponse> getBeneficiaryData() {
        isprogress.setValue(10);
        if (BeneficiaryMutableLiveData == null) {
            BeneficiaryMutableLiveData = new MutableLiveData<>();
        }
        return BeneficiaryMutableLiveData;
    }

    public MutableLiveData<ActionPlanResponse> getActionPlanData() {
        isprogress.setValue(10);
        if (ActionplanMutableLiveData == null) {
            ActionplanMutableLiveData = new MutableLiveData<>();
        }
        return ActionplanMutableLiveData;
    }

    public MutableLiveData<WorkPlanResponse> getWorkPlanData() {
        isprogress.setValue(10);
        if (WorkMutableLiveData == null) {
            WorkMutableLiveData = new MutableLiveData<>();
        }
        return WorkMutableLiveData;
    }

    public MutableLiveData<ActivityReportResponse> getActivityReportData() {
        isprogress.setValue(10);
        if (activityReportMutableLiveData == null) {
            activityReportMutableLiveData = new MutableLiveData<>();
        }
        return activityReportMutableLiveData;
    }

    public MutableLiveData<ServiceResponse> getServiceData() {
        isprogress.setValue(10);
        if (socialMutableLiveData == null) {
            socialMutableLiveData = new MutableLiveData<>();
        }
        return socialMutableLiveData;
    }

    //socialMutableLiveData


    //-------------------------------------
    // Benificiary Data
/*    public void callBeneFiciaryData() {
        isprogress.setValue(0);

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", CommonClass.USERiD);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getBenificiaryData(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<BeneResponse>() {
            @Override
            public void onResponse(Call<BeneResponse> call, Response<BeneResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                isprogress.setValue(10);
                if (response.body() != null) {
                    BeneficiaryMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<BeneResponse> call, Throwable t) {
                BeneficiaryMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }*/

    //-------------------------------------
    // ActionPlan Data
  /*  public void callActionPlanData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", CommonClass.USERiD);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getActionPlanData(mapData).enqueue(new Callback<ActionPlanResponse>() {
            @Override
            public void onResponse(Call<ActionPlanResponse> call, Response<ActionPlanResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                isprogress.setValue(10);
                if (response.body() != null) {
                    ActionplanMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ActionPlanResponse> call, Throwable t) {
                ActionplanMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }*/
    //--------------------------------

   /* // Activity Report
    public void callActivityPlanData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", CommonClass.USERiD);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getActivityReport(mapData).enqueue(new Callback<Example_ActivityReport>() {
            @Override
            public void onResponse(Call<Example_ActivityReport> call, Response<Example_ActivityReport> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                isprogress.setValue(10);
                if (response.body() != null) {
                    WorkMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<Example_ActivityReport> call, Throwable t) {
                ActionplanMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }*/
    //---------------------------------


    // Activity Report Attendance
    public void callActivityReportData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getActivityReportAttendenceData(CommonClass.APP_TOKEN).enqueue(new Callback<ActivityReportResponse>() {
            @Override
            public void onResponse(Call<ActivityReportResponse> call, Response<ActivityReportResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                isprogress.setValue(10);
                if (response.body() != null) {
                    activityReportMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<ActivityReportResponse> call, Throwable t) {
                activityReportMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }


    //---------------------------
/*    // ServiceData
    public void callServicetData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", CommonClass.USERiD);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getServiceapiData(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                isprogress.setValue(10);
                if (response.body() != null)
                {
                    socialMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                socialMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }*/


    //socialMutableLiveData


    //--------------------------------

    // State MAster
    public void callStateMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getStateMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    StateMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                StateMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }

    // Distic Master
/*    public void callDisticMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getDistMaster(CommonClass.APP_TOKEN).enqueue(new Callback<DistResponse>() {
            @Override
            public void onResponse(Call<DistResponse> call, Response<DistResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    DisticMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<DistResponse> call, Throwable t) {
                DisticMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //Block Master
    public void callBlockMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getBlockMaster(CommonClass.APP_TOKEN).enqueue(new Callback<BlockResponse>() {
            @Override
            public void onResponse(Call<BlockResponse> call, Response<BlockResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    BlockMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<BlockResponse> call, Throwable t) {
                BlockMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //GP Master
    public void callGPMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getGpMaster(CommonClass.APP_TOKEN).enqueue(new Callback<GPResponse>() {
            @Override
            public void onResponse(Call<GPResponse> call, Response<GPResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    GPMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<GPResponse> call, Throwable t) {
                GPMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //Village Master
    public void callVillageMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.getVillageMaster(CommonClass.APP_TOKEN).enqueue(new Callback<VillageResponse>() {
            @Override
            public void onResponse(Call<VillageResponse> call, Response<VillageResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    VillageMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<VillageResponse> call, Throwable t) {
                VillageMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }*/

    //Caste Master
    public void callCasteMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getCasteMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    CasteMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                CasteMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //Religion Master
    public void callReligionMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getReligionMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    ReligionMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ReligionMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //Economic Master
    public void callEconomicMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getEconomicMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    EconomicMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                EconomicMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //AnnualIncome Master
    public void callAnnualIncomeMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getAnualincomeMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    AnnualIncomeMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                AnnualIncomeMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }


    //MaratialStatus Master
    public void callMaritialStatusMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getMaritialStatusMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    MaritialStatusMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                MaritialStatusMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //Education Master
    public void callEducationMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getEducationMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    EducationMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                EducationMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //Ocupation Master
    public void callOcupationMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getOcupationMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    OccupationMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                OccupationMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //TypeDisability Master
    public void callTypeDisabilityMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getDisabilityMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    TypeDisabilityMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                TypeDisabilityMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //SubDisability Master
    public void callSubDisabilityMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getSubDisabilityMaster(CommonClass.APP_TOKEN).enqueue(new Callback<SubDisabilityResponse>() {
            @Override
            public void onResponse(Call<SubDisabilityResponse> call, Response<SubDisabilityResponse> response) {
                Log.d("TAG", "onResponse Sub Disability:: " + response.body());
                if (response.body() != null) {
                    SubDisabilityMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<SubDisabilityResponse> call, Throwable t) {
                SubDisabilityMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //PurposeVisit Master
    public void callPurposeVisitDisabilityMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getPurposeVisitMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    ProposeVisitMutableLiveData.setValue(response.body());
                    // isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProposeVisitMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }

    //Hoboli Master
    public void callHoboliMasterData() {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getHoboliMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse response Hoboli master:: " + response.body());
                if (response.body() != null) {
                    HoboliMutableLiveData.setValue(response.body());
                    isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                HoboliMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });
    }

    public void onlandingPageClick() {
        pageNavigate.setValue("landingpage");

    }

    public void onmasterDatacall() {
        pageNavigate.setValue("masterdata");

    }

    public void onBenificiaryDataCall() {
        pageNavigate.setValue("beneficiarydata");

    }

    public void onActionPlanDataCall() {
        pageNavigate.setValue("actionplan");

    }

}
