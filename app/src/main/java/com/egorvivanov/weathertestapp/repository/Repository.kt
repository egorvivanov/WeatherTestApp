package com.egorvivanov.weathertestapp.repository

import com.egorvivanov.weathertestapp.model.WeatherAll

interface Repository {

    suspend fun loadWeather(latitude: Float, longitude: Float): WeatherAll

}