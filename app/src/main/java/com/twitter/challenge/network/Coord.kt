package com.twitter.challenge.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coord(
        @Json(name = "lon") val lon: Float,
        @Json(name = "lat") val lat: Float) : Parcelable

