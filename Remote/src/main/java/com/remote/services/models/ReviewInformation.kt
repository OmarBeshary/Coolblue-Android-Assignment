package com.remote.services.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewInformation(val reviewSummary: ReviewSummary)

@JsonClass(generateAdapter = true)
data class ReviewSummary(val reviewAverage: Float, val reviewCount: Long)