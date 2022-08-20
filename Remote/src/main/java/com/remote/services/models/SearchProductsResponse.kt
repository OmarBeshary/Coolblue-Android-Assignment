package com.remote.services.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SearchProductsResponse(
    val products: List<Product>,
    val currentPage: Int,
    val pageSize: Int,
    val pageCount: Int
)