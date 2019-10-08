package com.twitter.challenge.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(
        @Json(name = "speed") val speed: Float,
        @Json(name = "deg") val deg: Int) : Parcelable

