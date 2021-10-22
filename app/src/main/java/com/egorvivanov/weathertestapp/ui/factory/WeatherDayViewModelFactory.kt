package com.egorvivanov.weathertestapp.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.egorvivanov.weathertestapp.repository.Repository
import com.egorvivanov.weathertestapp.ui.viewmodel.WeatherDayViewModel

@Suppress("UNCHECKED_CAST")
class WeatherDayViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        WeatherDayViewModel(repository) as T
}