package com.egorvivanov.weathertestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import coil.load

import com.egorvivanov.weathertestapp.R
import com.egorvivanov.weathertestapp.databinding.FragmentCurrentWeatherBinding
import com.egorvivanov.weathertestapp.network.ApiUtils
import com.egorvivanov.weathertestapp.repository.Repository
import com.egorvivanov.weathertestapp.repository.RepositoryImp
import com.egorvivanov.weathertestapp.ui.factory.WeatherCurrentViewModelFactory
import com.egorvivanov.weathertestapp.ui.viewmodel.WeatherCurrentViewModel
import com.egorvivanov.weathertestapp.util.constdata.Const

class WeatherCurrentFragment : Fragment() {

    private lateinit var _binding: FragmentCurrentWeatherBinding

    private lateinit var repository: Repository

    private val currentWeatherViewModel: WeatherCurrentViewModel by viewModels {
        WeatherCurrentViewModelFactory(repository)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCurrentWeatherBinding
            .inflate(inflater, container, false)
        val view = _binding.root

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val latitude = arguments?.getFloat(Const.LATITUDE, 0.0F) ?: 0.0F
        val longitude = arguments?.getFloat(Const.LONGITUDE, 0.0F) ?: 0.0F

        repository = RepositoryImp(
            ApiUtils.getWeatherApi()
        )

        currentWeatherViewModel.loadWeather(latitude, longitude)

        currentWeatherViewModel.weather.observe(this.viewLifecycleOwner) {
            _binding.tvHumidity.text =
                requireContext().getString(R.string.number_percentage, it.humidity)

            _binding.tvTemperature.text =
                requireContext().getString(R.string.number_degrees, it.temperature)

            _binding.ivRainState.load(
                requireContext().getString(R.string.load_image, it.rainImage)
            )

            val time = it.time % (24 * 60 * 60)
            _binding.tvTime.text = requireContext().getString(
                R.string.time_string,
                time / 3600,
                time / 60 % 60,
                time % 60
            )
        }
    }


    companion object {
        fun newInstance(
            latitude: Float,
            longitude: Float
        ) = WeatherCurrentFragment().apply {
            arguments = Bundle().apply {
                putFloat(Const.LATITUDE, latitude)
                putFloat(Const.LONGITUDE, longitude)
            }
        }
    }
}