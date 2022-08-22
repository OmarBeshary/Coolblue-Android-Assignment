package com.coolblue_android_assignment.di

import android.app.Application
import com.context.ContextModule
import com.coolblue_android_assignment.App
import com.coolblue_android_assignment.di.module.FragmentsModule
import com.coolblue_android_assignment.di.module.SchedulersModule
import com.coolblue_android_assignment.di.module.ViewModelsModule
import com.data.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ContextModule::class, RepositoryModule::class, FragmentsModule::class, SchedulersModule::class, ViewModelsModule::class])
interface AppComponent {

    fun inject(application: App)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}