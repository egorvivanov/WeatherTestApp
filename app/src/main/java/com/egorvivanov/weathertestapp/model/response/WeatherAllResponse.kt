package com.egorvivanov.weathertestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeatherAllResponse(
    @SerialName("timezone_offset") val timeOffset: Int,
    @SerialName("current") val weatherResponse: WeatherResponse,
    @SerialName("hourly") val hourlyResponses: List<WeatherResponse>,
    @SerialName("daily") val dailyResponses: List<WeatherDailyResponse>
)