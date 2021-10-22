package com.egorvivanov.weathertestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.egorvivanov.weathertestapp.R
import com.egorvivanov.weathertestapp.ViewPagerAdapter
import com.egorvivanov.weathertestapp.databinding.FragmentAllWeatherBinding
import com.egorvivanov.weathertestapp.util.constdata.Const
import com.google.android.material.tabs.TabLayoutMediator

class WeatherAllFragment : Fragment() {

    private lateinit var _binding: FragmentAllWeatherBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAllWeatherBinding.inflate(inflater, container, false)
        val view = _binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(
            parentFragmentManager,
            lifecycle,
            arguments?.getFloat(Const.LATITUDE, 0.0F),
            arguments?.getFloat(Const.LONGITUDE, 0.0F)
        )

        val viewPager = _binding.viewPager
        val tabLayout = _binding.slidingTabs

        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = weathers[position]
        }.attach()


        _binding.btnChangeCity.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                CityFragment.newInstance(),
                CityFragment::class.java.simpleName
            ).commit()
        }

    }


    companion object {

        fun newInstance(
            latitude: Float,
            longitude: Float
        ) = WeatherAllFragment().apply {
            arguments = Bundle().apply {
                putFloat(Const.LATITUDE, latitude)
                putFloat(Const.LONGITUDE, longitude)
            }
        }


        val weathers = arrayOf(
            "WeatherCurrent",
            "WeatherDaily",
            "WeatherWeekly"
        )
    }
}