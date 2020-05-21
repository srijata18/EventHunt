package com.example.eventhunt.dashboard.dataRepositories

import android.util.Log
import com.example.eventhunt.dashboard.dataModel.EventDataModel
import com.example.eventhunt.networkClasses.retrofit.RequestUrl
import com.example.eventhunt.networkClasses.retrofit.RetrofitServiceAnnotator
import com.example.eventhunt.dashboard.useCase.IDataSource
import com.example.eventhunt.utils.AppInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class DashboardRemoteRepository (private val retrofitServiceAnnotator: RetrofitServiceAnnotator):
    IDataSource {

    override fun getVariantDetails(callBack: IDataSource.getDetails) {
        val url = String.format("${RequestUrl.BASE_URL}${RequestUrl.DATA_URL}",AppInitializer.location)
        Log.i("location::url",url)
        val call = retrofitServiceAnnotator.doGetRequest(url)
        call.enqueue(object : Callback<EventDataModel> {
            override fun onFailure(call: Call<EventDataModel>, t: Throwable) {
                if (t is HttpException)
                    callBack.onPostFailure(t.message(),t.code())
                else
                    t.message?.let { callBack.onPostFailure(it) }

            }
            override fun onResponse(call: Call<EventDataModel>, response: Response<EventDataModel>) {
                if(response.body() != null && response.body() is EventDataModel){
                    callBack.onPostSuccess(response.body() as EventDataModel)
                }else{
                    callBack.onPostFailure(response.message(),response.code())
                }
            }
        })
    }

}