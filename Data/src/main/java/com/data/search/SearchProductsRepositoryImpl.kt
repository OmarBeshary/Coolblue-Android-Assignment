package com.data.search

import com.domain.search.models.SearchProductDomain
import com.domain.search.repo.SearchProductsRepository
import com.remote.mapper.SearchProductsDomainMapper
import com.remote.services.rest_api.search.SearchProductsService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchProductsRepositoryImpl @Inject constructor(private val searchProductsService: SearchProductsService) :
    SearchProductsRepository {

    override fun searchProducts(query: String): Single<SearchProductDomain> =
        searchProductsService
            .searchProducts(query)
            .map { response -> SearchProductsDomainMapper.toProductSearchDomain(response) }

    override fun searchProductsByPage(
        query: String,
        page: String
    ): Single<SearchProductDomain> =
        searchProductsService
            .searchProductsByPage(query, page)
            .map { response -> SearchProductsDomainMapper.toProductSearchDomain(response) }

}