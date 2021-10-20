package com.egorvivanov.weathertestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egorvivanov.weathertestapp.MainActivity
import com.egorvivanov.weathertestapp.model.Weather
import com.egorvivanov.weathertestapp.repository.Repository
import com.egorvivanov.weathertestapp.util.constdata.Const
import kotlinx.coroutines.launch

class WeatherCurrentViewModel(private val repository: Repository) : ViewModel() {

    private val weatherCurrent: MutableLiveData<Weather> = MutableLiveData()
    private val locationNameString: MutableLiveData<String> = MutableLiveData()
    private val locationCoordinatesString: MutableLiveData<String> = MutableLiveData()


    val weather: MutableLiveData<Weather>
        get() = weatherCurrent

    val locationName: MutableLiveData<String>
        get() = locationNameString

    val locationCoordinates: MutableLiveData<String>
        get() = locationCoordinatesString


    fun loadWeather() {
        viewModelScope.launch {
            weatherCurrent.postValue(repository.loadWeather().currentWeather)
        }
    }
}