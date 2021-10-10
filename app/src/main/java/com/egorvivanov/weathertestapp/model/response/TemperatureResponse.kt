package com.egorvivanov.weathertestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TemperatureResponse(
    @SerialName("min") val min: Double,
    @SerialName("max") val max: Double
)