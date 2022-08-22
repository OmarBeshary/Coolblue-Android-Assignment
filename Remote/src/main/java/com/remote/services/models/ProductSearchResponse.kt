package com.remote.services.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductSearchResponse(
    val products: List<ProductResponse>,
    val currentPage: Int,
    val pageSize: Int,
    val pageCount: Int
)