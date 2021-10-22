package com.egorvivanov.weathertestapp.ui.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.egorvivanov.weathertestapp.SingleLiveEvent
import com.egorvivanov.weathertestapp.model.Coordinates

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

import java.util.*

class CityViewModel(private val context: Context) : ViewModel() {


    // SingleLiveEvent data, с помощью которой широту и долготу
    // передаем в CityFragment
    private val coordinatesData = SingleLiveEvent<Coordinates>()

    fun getCoordinates(): LiveData<Coordinates> = coordinatesData


    private lateinit var locationManager: LocationManager
    private val geocoder = Geocoder(context, Locale.US)


    // Данный метод находит широту и долготу с помощью Geocoder
    // по названию города, введенного в EditText
    fun saveCityLocation(cityName: String) {

        viewModelScope.launch {
            val deferred = async(Dispatchers.IO) {
                geocoder.getFromLocationName(cityName, 1)
            }

            val list = deferred.await()

            if (list.isNotEmpty() && list[0].hasLatitude() && list[0].hasLongitude()) {
                val coordinates = Coordinates(
                    list[0].latitude.toFloat(),
                    list[0].longitude.toFloat()
                )
                coordinatesData.postValue(coordinates)
            }
        }
    }


    // Данный метод находит широту и долготу текущего местоположения устройства
    // с помощью LocationManager
    fun useCurrentLocationCoordinates() {
        if (context.applicationContext.checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager =
                context.applicationContext
                    .getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager

            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0F,
                locationListener
            )
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val coordinates = Coordinates(
                location.latitude.toFloat(),
                location.longitude.toFloat()
            )
            coordinatesData.postValue(coordinates)
        }
    }
}