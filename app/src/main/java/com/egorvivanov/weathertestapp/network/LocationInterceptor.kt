package com.egorvivanov.weathertestapp.network

import com.egorvivanov.weathertestapp.MainActivity
import com.egorvivanov.weathertestapp.util.constdata.Const
import okhttp3.Interceptor
import okhttp3.Response


// Данный Interceptor необходим для передачи географических
// координат (latitude, longitude) в теле запроса
class LocationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val current = MainActivity.sharedPreferences.getBoolean(Const.CURRENT, true)

        val urlNewBuilder = chain.request().url.newBuilder()

        val url = if (current) {
            val currentLatitude = MainActivity
                .sharedPreferences
                .getFloat(Const.CURRENT_LATITUDE, 1234F)
            val currentLongitude = MainActivity
                .sharedPreferences
                .getFloat(Const.CURRENT_LONGITUDE, 1234F)

            urlNewBuilder
                .addQueryParameter("lat", currentLatitude.toString())
                .addQueryParameter("lon", currentLongitude.toString())
                .build()
        } else {
            val latitude = MainActivity
                .sharedPreferences
                .getFloat(Const.LATITUDE, 1234F)
            val longitude = MainActivity
                .sharedPreferences
                .getFloat(Const.LONGITUDE, 1234F)

            urlNewBuilder
                .addQueryParameter("lat", latitude.toString())
                .addQueryParameter("lon", longitude.toString())
                .build()
        }

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}