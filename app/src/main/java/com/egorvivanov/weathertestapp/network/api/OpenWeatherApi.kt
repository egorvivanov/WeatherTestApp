package com.egorvivanov.weathertestapp.network.api

import com.egorvivanov.weathertestapp.model.response.WeatherAllResponse
import com.egorvivanov.weathertestapp.util.constdata.ApiConst
import retrofit2.http.GET

interface OpenWeatherApi {
    @GET(ApiConst.URL_ENDPOINT)
    suspend fun loadWeather(): WeatherAllResponse
}