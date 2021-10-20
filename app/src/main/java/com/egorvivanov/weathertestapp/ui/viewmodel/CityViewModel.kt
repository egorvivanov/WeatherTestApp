package com.egorvivanov.weathertestapp.ui.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

import android.location.Geocoder
import android.location.Location
import android.location.LocationManager

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

import com.egorvivanov.weathertestapp.SingleLiveEvent

class CityViewModel(context: Context) : ViewModel() {


    // SingleLiveEvent data, с помощью которой широту и долготу
    // передаем в CityFragment
    private val latitudeData = SingleLiveEvent<Float>()
    fun getLatitudeData(): SingleLiveEvent<Float> {
        return latitudeData
    }

    private val longitudeData = SingleLiveEvent<Float>()
    fun getLongitudeData(): SingleLiveEvent<Float> {
        return longitudeData
    }


    private lateinit var locationManager: LocationManager
    private val geocoder = Geocoder(context)

    fun saveCityLocation(cityName: String) {

        val list = geocoder.getFromLocationName(cityName, 1)

        if (list.isEmpty() || !list[0].hasLatitude() || !list[0].hasLongitude()) {
            return
        } else {
            latitudeData.postValue(list[0].latitude.toFloat())
            longitudeData.postValue(list[0].longitude.toFloat())
        }
    }


    fun useCurrentLocationCoordinates(context: Context?) {

        if (context?.checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager =
                context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager

            val providers = locationManager.getProviders(true)
            var bestLocation: Location? = null

            for (provider in providers) {
                val location = locationManager.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || location.accuracy < bestLocation.accuracy) {
                    bestLocation = location

                    latitudeData.postValue(bestLocation.latitude.toFloat())
                    longitudeData.postValue(bestLocation.longitude.toFloat())

                } else {
                    latitudeData.postValue(location.latitude.toFloat())
                    longitudeData.postValue(location.longitude.toFloat())
                }
            }
        }
    }
}