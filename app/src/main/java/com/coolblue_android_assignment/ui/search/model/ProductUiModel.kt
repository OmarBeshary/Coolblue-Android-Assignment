package com.coolblue_android_assignment.ui.search.model

data class ProductUiModel(
    val name: String,
    val imageUrl: String,
    val price: Float,
    val info: String,
    val reviewAverage: Float,
    val reviewCount: Long,
    val has2ndDayDelivery: Boolean
)