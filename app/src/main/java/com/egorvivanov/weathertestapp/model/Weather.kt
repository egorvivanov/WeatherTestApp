package com.egorvivanov.weathertestapp.model


data class Weather(
    val time: Int,
    val timeString: String?,
    val temperature: Double,
    val temperatureAdditional: Double? = null,
    val humidity: Int,
    val rainState: String,
    val rainImage: String
)