package com.egorvivanov.weathertestapp.ui.factory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egorvivanov.weathertestapp.repository.Repository
import com.egorvivanov.weathertestapp.ui.viewmodel.CityViewModel

@Suppress("UNCHECKED_CAST")
class CityViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CityViewModel(context) as T
}