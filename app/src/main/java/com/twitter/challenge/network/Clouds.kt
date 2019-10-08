package com.twitter.challenge.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clouds(
        @Json(name = "cloudiness") val cloudiness: Int) : Parcelable