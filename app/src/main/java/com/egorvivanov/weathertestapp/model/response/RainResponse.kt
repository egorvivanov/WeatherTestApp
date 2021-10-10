package com.egorvivanov.weathertestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RainResponse(
    @SerialName("main") val rainState: String,
    @SerialName("icon") val icon: String
)