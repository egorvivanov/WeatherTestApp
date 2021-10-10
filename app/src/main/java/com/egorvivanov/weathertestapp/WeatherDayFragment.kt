package com.egorvivanov.weathertestapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.egorvivanov.weathertestapp.databinding.FragmentDayWeatherBinding

class WeatherDayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDayWeatherBinding
            .inflate(inflater, container, false)
        val view = binding.root

        return view
    }
}