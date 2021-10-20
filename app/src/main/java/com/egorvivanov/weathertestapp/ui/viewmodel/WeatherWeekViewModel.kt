package com.egorvivanov.weathertestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egorvivanov.weathertestapp.model.Weather
import com.egorvivanov.weathertestapp.repository.Repository
import kotlinx.coroutines.launch

class WeatherWeekViewModel(private val repository: Repository) : ViewModel() {

    private val weatherWeekList: MutableLiveData<List<Weather>> = MutableLiveData()

    val weatherWeek: MutableLiveData<List<Weather>>
        get() = weatherWeekList

    fun loadWeather() {
        viewModelScope.launch {
            weatherWeekList.postValue(repository.loadWeather().dailyWeather)
        }
    }
}