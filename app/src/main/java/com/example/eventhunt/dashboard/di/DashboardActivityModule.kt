package com.example.eventhunt.dashboard.di

import com.example.eventhunt.networkClasses.UseCaseHandler
import com.example.eventhunt.dashboard.useCase.DummyUseCase
import com.example.eventhunt.dashboard.viewModel.DashboardViewModel
import dagger.Module
import dagger.Provides

@Module
class DashboardActivityModule {

    @Provides
    fun provideMainViewModel(useCaseHandler: UseCaseHandler, dummyUseCase: DummyUseCase): DashboardViewModel {
        return DashboardViewModel(
            useCaseHandler,
            dummyUseCase
        )
    }

}