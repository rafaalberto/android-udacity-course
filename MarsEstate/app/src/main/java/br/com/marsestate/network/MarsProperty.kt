package br.com.marsestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsProperty(
    val id: String,
    val type: String,
    val price: Double,
    @Json(name = "img_src") val imageSourceUrl: String
) : Parcelable {
    val isRental get() = type == "rent"
}