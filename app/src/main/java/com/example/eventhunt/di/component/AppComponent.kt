package com.example.eventhunt.di.component

import com.example.eventhunt.EventHuntApplication
import com.example.eventhunt.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
@Singleton
abstract class AppComponent : AndroidInjector<EventHuntApplication>{
    abstract override fun inject(instance: EventHuntApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: EventHuntApplication): Builder
        fun build(): AppComponent
    }
}