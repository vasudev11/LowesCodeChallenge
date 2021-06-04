package com.example.lowescodechallenge.viewmodel

import androidx.lifecycle.*
import com.example.lowescodechallenge.model.CurrentWeather
import com.example.lowescodechallenge.model.CityRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class WeatherViewModel(private val cityRepository: CityRepository) : ViewModel() {

    class WeatherViewModelProvider(private val cityRepository: CityRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(cityRepository) as T
        }
    }

    private val mutableLiveData = MutableLiveData<CurrentWeather>()

    val livedata: LiveData<CurrentWeather> = mutableLiveData

    fun getCityForecastFlow(cityName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val flow = cityRepository.getWeather(cityName)

            withContext(Dispatchers.Main) {
                flow.collect{
                    mutableLiveData.value = it
                }
            }
        }
    }
}