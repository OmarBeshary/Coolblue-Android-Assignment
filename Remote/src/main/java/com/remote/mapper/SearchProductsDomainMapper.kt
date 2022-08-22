package com.remote.mapper

import com.domain.search.models.Product
import com.domain.search.models.SearchProductDomain
import com.remote.services.models.ProductResponse
import com.remote.services.models.ProductSearchResponse

object SearchProductsDomainMapper {

    fun toProductSearchDomain(productSearchResponse: ProductSearchResponse): SearchProductDomain =
        with(productSearchResponse) {
            return@with SearchProductDomain(
                products = products.map(this@SearchProductsDomainMapper::toProductDomain),
                currentPage = currentPage,
                pageSize = pageSize,
                pageCount = pageCount
            )
        }

    fun toProductDomain(response: ProductResponse): Product = with(response) {
        return@with Product(
            name = productName,
            imageUrl = productImage,
            reviewAverage = reviewInformation.reviewSummary.reviewAverage,
            reviewCount = reviewInformation.reviewSummary.reviewCount,
            info = USPs,
            price = salesPriceIncVat.toFloat(),
            canDeliver2ndDay = nextDayDelivery
        )
    }
}