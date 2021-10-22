package com.egorvivanov.weathertestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.egorvivanov.weathertestapp.model.Weather
import com.egorvivanov.weathertestapp.repository.Repository

import kotlinx.coroutines.launch

class WeatherCurrentViewModel(private val repository: Repository) : ViewModel() {

    private val weatherCurrent: MutableLiveData<Weather> = MutableLiveData()

    val weather: MutableLiveData<Weather>
        get() = weatherCurrent

    fun loadWeather(latitude: Float, longitude: Float) {
        viewModelScope.launch {
            weatherCurrent.postValue(repository.loadWeather(latitude, longitude).currentWeather)
        }
    }
}