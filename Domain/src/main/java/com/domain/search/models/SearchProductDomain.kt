package com.domain.search.models

data class SearchProductDomain(
    val products: List<Product>,
    val currentPage: Int,
    val pageSize: Int,
    val pageCount: Int
)