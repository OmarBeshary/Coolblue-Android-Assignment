package com.data.di.module

import com.data.search.SearchProductsRepositoryImpl
import com.domain.search.repo.SearchProductsRepository
import com.remote.services.rest_api.ServicesModule
import com.remote.services.rest_api.search.SearchProductsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServicesModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun providesSearchProductsRepository(
        searchProductsService: SearchProductsService
    ): SearchProductsRepository =
        SearchProductsRepositoryImpl(searchProductsService)

}