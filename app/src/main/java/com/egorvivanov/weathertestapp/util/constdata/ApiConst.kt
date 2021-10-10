package com.egorvivanov.weathertestapp.util.constdata

import okhttp3.MediaType.Companion.toMediaType

object ApiConst {

    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val URL_ENDPOINT = "onecall"

    val CONTENT_TYPE = "application/json".toMediaType()

}