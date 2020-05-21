package com.example.eventhunt.networkClasses.retrofit

import com.example.eventhunt.dashboard.dataModel.EventDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServiceAnnotator {

    @GET
    fun doGetRequest(@Url url : String) : Call<EventDataModel>

}