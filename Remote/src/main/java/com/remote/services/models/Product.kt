package com.remote.services.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Product(
    val productId: Long,
    val productName: String,
    val productImage: String,
    val reviewInformation: ReviewInformation
)