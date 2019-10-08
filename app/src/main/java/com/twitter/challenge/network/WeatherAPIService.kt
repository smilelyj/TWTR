package com.twitter.challenge.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://twitter-code-challenge.s3.amazonaws.com/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

/**
 * A public interface that exposes the [getWeatherResponse] and [getFutureWeather] method
 */
interface WeatherAPIService {
    /**
     * Returns a Coroutine [Deferred] [WeatherResponse] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "current.json" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("current.json")
    fun getWeatherResponse(): Deferred<WeatherResponse>

    @GET("future_{day}.json")
    fun getFutureWeather(@Path("day") day: Int): Deferred<WeatherResponse>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object WeatherApi {
    val retrofitService : WeatherAPIService by lazy { retrofit.create(WeatherAPIService::class.java) }
}