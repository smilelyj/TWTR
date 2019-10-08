package com.twitter.challenge.homepage

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twitter.challenge.StandardDeviation.standardDeviation
import com.twitter.challenge.network.WeatherApi
import com.twitter.challenge.network.WeatherResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class WeatherViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _response = MutableLiveData<WeatherResponse>()

    val weatherResponse: LiveData<WeatherResponse>
        get() = _response

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var _standard_deviation : Float = 0.0f

    private val _std = MutableLiveData<Float>()
    val standardDeviation: LiveData<Float>
        get() = _std

    /**
     * Call getTWeather() on init so we can display result immediately.
     */
    init {
        getTWeather()
    }

    private fun getTWeather() {
        coroutineScope.launch {
            var getWeatherDeferred = WeatherApi.retrofitService.getWeatherResponse()
            try {
                _status.value = ApiStatus.LOADING
                val listResult = getWeatherDeferred.await()
                _status.value = ApiStatus.DONE
                _response.value = listResult

            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _response.value = null
            }
        }
    }

    fun getFutureTWeather() {
        coroutineScope.launch {
            try {
                val dataPoints: ArrayList<Float> =  ArrayList()
                for (i in 1..5) {
                    dataPoints.add(WeatherApi.retrofitService.getFutureWeather(i).await().weather.temp)
                }

                _standard_deviation = standardDeviation(dataPoints)

                _std.value = _standard_deviation

            } catch (exception: Exception) {
                Log.e(TAG, exception.message.toString())
            }
        }
    }

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}