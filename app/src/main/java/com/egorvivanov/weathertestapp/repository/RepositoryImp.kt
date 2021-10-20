package com.egorvivanov.weathertestapp.repository

import com.egorvivanov.weathertestapp.model.Weather
import com.egorvivanov.weathertestapp.model.WeatherAll
import com.egorvivanov.weathertestapp.network.ApiUtils
import com.egorvivanov.weathertestapp.network.api.OpenWeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImp(
    private val api: OpenWeatherApi,
    private val latitude: Float,
    private val longitude: Float
) : Repository {


    override suspend fun loadWeather(): WeatherAll {

        return withContext(Dispatchers.IO) {

            var offset: Int

            val currentWeather =
                api.loadWeather(latitude.toString(), longitude.toString()).weatherResponse.let {
                    offset = (it.time + api.loadWeather(
                        latitude.toString(),
                        longitude.toString()
                    ).timeOffset) / (3600 * 24)

                    Weather(
                        it.time + api.loadWeather(
                            latitude.toString(),
                            longitude.toString()
                        ).timeOffset,
                        null,
                        it.temperature - 273,
                        null,
                        it.humidity,
                        it.rainStateResponses[0].rainState,
                        it.rainStateResponses[0].icon
                    )
                }

            val weatherDay = api.loadWeather(
                latitude.toString(),
                longitude.toString()
            ).hourlyResponses.map { weatherHourlyResponse ->
                val time = weatherHourlyResponse.time + api.loadWeather(
                    latitude.toString(),
                    longitude.toString()
                ).timeOffset

                Weather(
                    time,
                    String.format("%1$02d:%2$02d", time / 3600 % 24, time / 60 % 60),
                    weatherHourlyResponse.temperature - 273,
                    null,
                    weatherHourlyResponse.humidity,
                    weatherHourlyResponse.rainStateResponses[0].rainState,
                    weatherHourlyResponse.rainStateResponses[0].icon
                )
            }

            val weatherWeek = api.loadWeather(
                latitude.toString(),
                longitude.toString()
            ).dailyResponses.map { weatherDailyResponse ->
                val time = weatherDailyResponse.time + api.loadWeather(
                    latitude.toString(),
                    longitude.toString()
                ).timeOffset

                Weather(
                    time,
                    weekList[(time / (3600 * 24) - offset) % 7],
                    weatherDailyResponse.temperature.min - 273,
                    weatherDailyResponse.temperature.max - 273,
                    weatherDailyResponse.humidity,
                    weatherDailyResponse.rainStateResponses[0].rainState,
                    weatherDailyResponse.rainStateResponses[0].icon
                )
            }

            WeatherAll(currentWeather, weatherDay, weatherWeek)
        }
    }


    companion object {
        val weekList = arrayOf(
            "Сегодня",
            "Завтра",
            "Через 2 дня",
            "Через 3 дня",
            "Через 4 дня",
            "Через 5 дней",
            "Через 6 дней"
        )
    }
}