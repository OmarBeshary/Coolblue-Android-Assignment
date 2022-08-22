package com.coolblue_android_assignment.ui.search.mapper

import com.coolblue_android_assignment.ui.search.model.ProductUiModel
import com.coolblue_android_assignment.ui.search.model.SearchProductScreen
import com.domain.search.models.Product
import com.domain.search.models.SearchProductDomain

object SearchProductsPresentationMapper {

    fun toSearchProductsPresentation(searchProductDomain: SearchProductDomain): SearchProductScreen =
        with(searchProductDomain) {
            return@with SearchProductScreen(
                products = products.map(this@SearchProductsPresentationMapper::toProductUiModel),
                currentPage = currentPage,
                pageSize = pageSize,
                pageCount = pageCount
            )
        }

    fun toProductUiModel(product: Product): ProductUiModel = with(product) {
        return@with ProductUiModel(
            name = name,
            imageUrl = imageUrl,
            reviewAverage = reviewAverage / 2f,
            reviewCount = reviewCount,
            info = info.joinToString(separator = "\n"),
            price = price,
            has2ndDayDelivery = canDeliver2ndDay
        )
    }
}