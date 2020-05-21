package com.example.eventhunt.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.eventhunt.view.base.BaseViewModel
import com.example.eventhunt.networkClasses.ErrorModel
import com.example.eventhunt.networkClasses.UseCase
import com.example.eventhunt.networkClasses.UseCaseHandler
import com.example.eventhunt.dashboard.dataModel.EventDataModel
import com.example.eventhunt.dashboard.useCase.DummyUseCase

class DashboardViewModel(
    override val useCaseHandler: UseCaseHandler,
    val dummyUseCase: DummyUseCase
) : BaseViewModel(useCaseHandler) {

    val receivedResponse = MutableLiveData<EventDataModel>()
    val errorModel = MutableLiveData<ErrorModel>()

    fun fetchDetails(){
        val requestValue = DummyUseCase.RequestValues()
        useCaseHandler.execute(
            dummyUseCase, requestValue,
            object : UseCase.UseCaseCallback<DummyUseCase.ResponseValue>{
                override fun onSuccess(response: DummyUseCase.ResponseValue) {
                    if (response.response is EventDataModel) {
                        receivedResponse.value = response.response
                    }
                }

                override fun onError(t: String, code: Int?) {
                    errorModel.value = ErrorModel(t,code)
                }
            }
        )
    }
}