package com.domain.search.use_cases

import com.domain.qualifires.Background
import com.domain.qualifires.Foreground
import com.domain.search.models.SearchProductDomain
import com.domain.search.repo.SearchProductsRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchProductsByPageUseCase @Inject constructor(
    private val searchProductsRepository: SearchProductsRepository,
    @Foreground private val foregroundScheduler: Scheduler,
    @Background private val backgroundScheduler: Scheduler
) {

    operator fun invoke(query: String, nextPage: String): Single<SearchProductDomain> {
        return searchProductsRepository
            .searchProductsByPage(query, nextPage)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }
}