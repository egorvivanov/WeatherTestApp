package com.egorvivanov.weathertestapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.egorvivanov.weathertestapp.ui.WeatherCurrentFragment
import com.egorvivanov.weathertestapp.ui.WeatherDayFragment
import com.egorvivanov.weathertestapp.ui.WeatherWeekFragment


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val latitude: Float?,
    val longitude: Float?
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val latitudeToFragment = latitude ?: 0.0F
    private val longitudeToFragment = longitude ?: 0.0F

    companion object {
        private const val NUMBER_TABS = 3
    }

    override fun getItemCount(): Int {
        return NUMBER_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WeatherCurrentFragment.newInstance(latitudeToFragment, longitudeToFragment)
            1 -> WeatherDayFragment.newInstance(latitudeToFragment, longitudeToFragment)
            2 -> WeatherWeekFragment.newInstance(latitudeToFragment, longitudeToFragment)

            else -> WeatherCurrentFragment.newInstance(latitudeToFragment, longitudeToFragment)
        }
    }
}