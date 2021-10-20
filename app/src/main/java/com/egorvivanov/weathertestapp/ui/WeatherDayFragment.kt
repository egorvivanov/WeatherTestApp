package com.egorvivanov.weathertestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.egorvivanov.weathertestapp.WeatherRecyclerAdapter
import com.egorvivanov.weathertestapp.databinding.FragmentDayWeatherBinding
import com.egorvivanov.weathertestapp.network.ApiUtils
import com.egorvivanov.weathertestapp.repository.Repository
import com.egorvivanov.weathertestapp.repository.RepositoryImp
import com.egorvivanov.weathertestapp.ui.factory.WeatherDayViewModelFactory
import com.egorvivanov.weathertestapp.ui.viewmodel.WeatherDayViewModel
import com.egorvivanov.weathertestapp.util.constdata.Const

class WeatherDayFragment : Fragment() {

    private lateinit var _binding: FragmentDayWeatherBinding
    private lateinit var weatherDayAdapter: WeatherRecyclerAdapter

    private lateinit var repository: Repository


    private val weatherDayViewModel: WeatherDayViewModel by viewModels {
        WeatherDayViewModelFactory(repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDayWeatherBinding
            .inflate(inflater, container, false)
        val view = _binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val latitude = arguments?.getFloat(Const.LATITUDE, 0.0F) ?: 0.0F
        val longitude = arguments?.getFloat(Const.LONGITUDE, 0.0F) ?: 0.0F

        repository = RepositoryImp(
            ApiUtils.getWeatherApi(),
            latitude,
            longitude
        )

        weatherDayViewModel.loadWeather()

        weatherDayAdapter = WeatherRecyclerAdapter()
        _binding.rvDaily.adapter = weatherDayAdapter
        _binding.rvDaily.setHasFixedSize(true)

        _binding.rvDaily.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        weatherDayViewModel.weatherDay.observe(this.viewLifecycleOwner) {
            weatherDayAdapter.submitList(it)
        }
    }


    companion object {
        fun newInstance(
            latitude: Float,
            longitude: Float
        ) = WeatherDayFragment().apply {
            arguments = Bundle().apply {
                putFloat(Const.LATITUDE, latitude)
                putFloat(Const.LONGITUDE, longitude)
            }
        }
    }
}