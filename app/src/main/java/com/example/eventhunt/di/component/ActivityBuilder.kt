package com.example.eventhunt.di.component

import com.example.eventhunt.dashboard.views.DashBoardActivity
import com.example.eventhunt.dashboard.di.DashboardActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [DashboardActivityModule::class])
    internal abstract fun bindMainActivity(): DashBoardActivity


}