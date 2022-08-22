package com.remote.services.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ProductResponse(
    val productId: Long,
    val productName: String,
    val productImage: String,
    val reviewInformation: ReviewInformation,
    val USPs: List<String>,
    val nextDayDelivery: Boolean,
    val salesPriceIncVat: Double
)