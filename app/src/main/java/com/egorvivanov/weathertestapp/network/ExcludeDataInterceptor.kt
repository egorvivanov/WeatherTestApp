package com.egorvivanov.weathertestapp.network

import okhttp3.Interceptor
import okhttp3.Response


// Данный Interceptor необходим для передачи дополнительных параметров в теле запроса
// для исключения из ответа определенных данных
class ExcludeDataInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("exclude", "minutely,allerts")
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}