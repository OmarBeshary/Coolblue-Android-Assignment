package com.coolblue_android_assignment.di.module

import com.domain.qualifires.Background
import com.domain.qualifires.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Provides
    @Singleton
    @Foreground
    fun providesForegroundScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    @Background
    fun providesBackgroundScheduler(): Scheduler {
        return Schedulers.io()
    }
}