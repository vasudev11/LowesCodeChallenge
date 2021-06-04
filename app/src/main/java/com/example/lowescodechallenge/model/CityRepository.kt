package com.example.lowescodechallenge.model

import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun getWeather(cityName: String): Flow<CurrentWeather>
    suspend fun getWeatherC(cityName: String): CurrentWeather
}