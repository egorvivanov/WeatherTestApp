package com.egorvivanov.weathertestapp.network.api

import com.egorvivanov.weathertestapp.model.response.WeatherAllResponse
import com.egorvivanov.weathertestapp.util.constdata.ApiConst
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET(ApiConst.URL_ENDPOINT)
    suspend fun loadWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): WeatherAllResponse
}