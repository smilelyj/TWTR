package com.twitter.challenge.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
        @Json(name = "temp") val temp: Float,
        @Json(name = "pressure") val pressure: Int,
        @Json(name = "humidity") val humidity: Int) : Parcelable

