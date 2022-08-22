package com.coolblue_android_assignment.ui.search.model

class SearchProductScreen(
    val products: List<ProductUiModel>,
    val currentPage: Int,
    val pageSize: Int,
    val pageCount: Int
)