package com.egorvivanov.weathertestapp.network

import com.egorvivanov.weathertestapp.util.constdata.Config
import okhttp3.Interceptor
import okhttp3.Response


// Данный Interceptor необходим для передачи уникального API key
// для доступа к методам API сайта https://openweathermap.org/api
class AuthorizationKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("appid", Config.API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}