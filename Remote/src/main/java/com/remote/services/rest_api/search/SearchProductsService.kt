package com.remote.services.rest_api.search

import com.remote.EndPoints.searchProductsEndPoint
import com.remote.services.models.SearchProductsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchProductsService {

    @GET(searchProductsEndPoint)
    fun searchProducts(@Query("query") query: String): Single<SearchProductsResponse>

    @GET(searchProductsEndPoint)
    fun searchProductsByPage(
        @Query("query") query: String,
        @Query("page") page: String
    ): Single<SearchProductsResponse>
}