package com.twitter.challenge.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse (
        @field:Json(name = "coord")
        var coord: Coord,
        @field:Json(name = "weather")
        var weather: Weather,
        @field:Json(name = "wind")
        var wind: Wind,
        @field:Json(name = "rain")
        var rain: Rain,
        @field:Json(name = "clouds")
        var clouds: Clouds,
        @Json(name = "name")
        var name :String) : Parcelable