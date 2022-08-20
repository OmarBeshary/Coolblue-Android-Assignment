package com.remote.retrofit


import com.remote.moshi.MoshiModule
import com.remote.network_client.OkHttpClientModule
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(
    includes = [
        OkHttpClientModule::class,
        MoshiModule::class
    ]
)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        moshiParser: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://bdk0sta2n0.execute-api.eu-west-1.amazonaws.com/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshiParser))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
}