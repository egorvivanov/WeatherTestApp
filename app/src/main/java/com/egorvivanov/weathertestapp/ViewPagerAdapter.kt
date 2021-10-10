package com.egorvivanov.weathertestapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {


    companion object {
        private const val NUMBER_TABS = 3
    }

    override fun getItemCount(): Int {
        return NUMBER_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WeatherCurrentFragment()
            1 -> WeatherDayFragment()
            else -> WeatherWeekFragment()
        }
    }
}