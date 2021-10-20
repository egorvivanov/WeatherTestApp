package com.egorvivanov.weathertestapp.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egorvivanov.weathertestapp.repository.Repository
import com.egorvivanov.weathertestapp.ui.viewmodel.WeatherCurrentViewModel

@Suppress("UNCHECKED_CAST")
class WeatherCurrentViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        WeatherCurrentViewModel(repository) as T
}