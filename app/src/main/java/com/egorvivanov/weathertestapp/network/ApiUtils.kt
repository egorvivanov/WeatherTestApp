package com.egorvivanov.weathertestapp.network

import com.egorvivanov.weathertestapp.network.api.OpenWeatherApi
import com.egorvivanov.weathertestapp.network.interceptors.AuthorizationKeyInterceptor
import com.egorvivanov.weathertestapp.network.interceptors.ExcludeDataInterceptor
import com.egorvivanov.weathertestapp.util.constdata.ApiConst
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


object ApiUtils {

    private fun getJson(): Json {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return json
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(ExcludeDataInterceptor())
            .addInterceptor(AuthorizationKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    fun getWeatherApi(): OpenWeatherApi {
        return Retrofit.Builder()
            .baseUrl(ApiConst.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(getJson().asConverterFactory(ApiConst.CONTENT_TYPE))
            .build()
            .create(OpenWeatherApi::class.java)
    }
}