package com.example.eventhunt.di.module

import com.example.eventhunt.networkClasses.UseCaseHandler
import com.example.eventhunt.networkClasses.retrofit.RetrofitClient
import com.example.eventhunt.networkClasses.retrofit.RetrofitServiceAnnotator
import com.example.eventhunt.dashboard.dataRepositories.DashboardDataRepository
import com.example.eventhunt.dashboard.dataRepositories.DashboardLocalRepository
import com.example.eventhunt.dashboard.dataRepositories.DashboardRemoteRepository
import com.example.eventhunt.dashboard.useCase.DummyUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    internal fun provideUseCaseHandler() = UseCaseHandler.getInstance()

    @Provides
    @Singleton
    internal fun provideRetrofitServiceAnnotator(): RetrofitServiceAnnotator = RetrofitClient.create()

    @Provides
    internal fun provideUseCase(dataRepository: DashboardDataRepository): DummyUseCase =
        DummyUseCase(dataRepository)

    @Provides
    internal fun provideRemoteData(retrofitServiceAnnotator: RetrofitServiceAnnotator) : DashboardRemoteRepository =
        DashboardRemoteRepository(
            retrofitServiceAnnotator
        )

    @Provides
    internal fun provideLocalData() : DashboardLocalRepository =
        DashboardLocalRepository()

    @Provides
    internal fun provideDataRepository(remoteRepository: DashboardRemoteRepository,
                                       localRepository: DashboardLocalRepository
    ) : DashboardDataRepository =
        DashboardDataRepository(
            remoteRepository,
            localRepository
        )

}