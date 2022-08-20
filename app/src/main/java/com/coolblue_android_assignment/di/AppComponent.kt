package com.coolblue_android_assignment.di

import android.app.Application
import com.context.ContextModule
import com.coolblue_android_assignment.App
import com.coolblue_android_assignment.di.module.FragmentsModule
import com.coolblue_android_assignment.di.module.SchedulersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ContextModule::class, SchedulersModule::class, FragmentsModule::class])
interface AppComponent {

    fun inject(application: App)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}