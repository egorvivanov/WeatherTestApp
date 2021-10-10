package com.egorvivanov.weathertestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeatherResponse(
    @SerialName("temp") val temperature: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("weather") val rainStateResponses: List<RainResponse>,
    @SerialName("dt") val time: Int
)