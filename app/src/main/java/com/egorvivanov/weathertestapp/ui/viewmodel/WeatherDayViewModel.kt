package com.egorvivanov.weathertestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.egorvivanov.weathertestapp.model.Weather
import com.egorvivanov.weathertestapp.repository.Repository

import kotlinx.coroutines.launch

class WeatherDayViewModel(private val repository: Repository) : ViewModel() {

    private val weatherDayList: MutableLiveData<List<Weather>> = MutableLiveData()

    val weatherDay: MutableLiveData<List<Weather>>
        get() = weatherDayList


    fun loadWeather(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            weatherDayList.postValue(repository.loadWeather(latitude, longitude).hourlyWeather)
        }
    }
}

