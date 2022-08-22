package com.remote.services.rest_api

import com.remote.retrofit.RetrofitModule
import com.remote.services.rest_api.search.SearchProductsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [RetrofitModule::class])
class ServicesModule {

    @Provides
    @Singleton
    fun provideSearchProductsService(retrofit: Retrofit): SearchProductsService =
        retrofit.create(SearchProductsService::class.java)

}