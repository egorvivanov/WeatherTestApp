package com.egorvivanov.weathertestapp.model


data class WeatherAll(
    val currentWeather: Weather,
    val hourlyWeather: List<Weather>,
    val dailyWeather: List<Weather>
)