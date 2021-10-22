package com.egorvivanov.weathertestapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

import com.egorvivanov.weathertestapp.databinding.ActivityMainBinding
import com.egorvivanov.weathertestapp.ui.CityFragment


class MainActivity : AppCompatActivity() {


    companion object {
        private const val LOCATION_CODE = 1122
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Подключаем ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Проверка, есть ли разрешение на использование доступа к геолокации.
        // Если разрешения нет - запрашиваем разрешение с уникальным LOCATION_CODE,
        // переходим на экран с отображением ошибки и сообщением пользователю.
        // Если разрешение есть - тогда переходим на экран выбора города.
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Нет разрешения, запрашиваем
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_CODE
            )
        } else {
            // Разрешение получено
            startCityFragment()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // Разрешение есть, запускаем фрагмент с выбором города
                    startCityFragment()
                } else {
                    Log.d(
                        MainActivity::class.java.simpleName,
                        "Необходимо разрешение для продолжения работы"
                    )
                }
            }
        }
    }


    private fun startCityFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            CityFragment.newInstance(),
            CityFragment::class.java.simpleName
        ).commit()
    }
}