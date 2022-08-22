package com.domain.search.models

class Product(
    val name: String,
    val imageUrl: String,
    val price: Float,
    val info: List<String>,
    val reviewAverage: Float,
    val reviewCount: Long,
    val canDeliver2ndDay: Boolean
)