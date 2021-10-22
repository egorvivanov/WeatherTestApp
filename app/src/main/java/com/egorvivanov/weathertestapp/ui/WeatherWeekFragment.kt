package com.egorvivanov.weathertestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.egorvivanov.weathertestapp.WeatherRecyclerAdapter
import com.egorvivanov.weathertestapp.databinding.FragmentWeekWeatherBinding
import com.egorvivanov.weathertestapp.network.ApiUtils
import com.egorvivanov.weathertestapp.repository.Repository
import com.egorvivanov.weathertestapp.repository.RepositoryImp
import com.egorvivanov.weathertestapp.ui.factory.WeatherWeekViewModelFactory
import com.egorvivanov.weathertestapp.ui.viewmodel.WeatherWeekViewModel
import com.egorvivanov.weathertestapp.util.constdata.Const

class WeatherWeekFragment : Fragment() {

    private lateinit var _binding: FragmentWeekWeatherBinding
    private lateinit var weatherWeekAdapter: WeatherRecyclerAdapter

    private lateinit var repository: Repository

    private val weatherWeekViewModel: WeatherWeekViewModel by viewModels {
        WeatherWeekViewModelFactory(repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWeekWeatherBinding
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
        )

        weatherWeekViewModel.loadWeather(latitude, longitude)

        weatherWeekAdapter = WeatherRecyclerAdapter()
        _binding.rvWeekly.adapter = weatherWeekAdapter
        _binding.rvWeekly.setHasFixedSize(true)

        _binding.rvWeekly.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        weatherWeekViewModel.weatherWeek.observe(this.viewLifecycleOwner) {
            weatherWeekAdapter.submitList(it)
        }
    }


    companion object {
        fun newInstance(
            latitude: Float,
            longitude: Float
        ) = WeatherWeekFragment().apply {
            arguments = Bundle().apply {
                putFloat(Const.LATITUDE, latitude)
                putFloat(Const.LONGITUDE, longitude)
            }
        }
    }
}