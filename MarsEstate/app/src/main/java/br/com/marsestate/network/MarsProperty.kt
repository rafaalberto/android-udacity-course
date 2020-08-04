package br.com.marsestate.network

import com.squareup.moshi.Json

data class MarsProperty(
    val id: String,
    val type: String,
    val price: Double,
    @Json(name = "img_src") val imageSourceUrl: String
)