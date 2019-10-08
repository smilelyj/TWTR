package com.twitter.challenge.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rain(
        @Json(name = "3h") val threeh: Int) : Parcelable

