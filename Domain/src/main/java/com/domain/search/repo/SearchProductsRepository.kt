package com.domain.search.repo

import com.domain.search.models.SearchProductDomain
import io.reactivex.rxjava3.core.Single

interface SearchProductsRepository {

    fun searchProducts(query: String): Single<SearchProductDomain>

    fun searchProductsByPage(query: String, page: String): Single<SearchProductDomain>
}