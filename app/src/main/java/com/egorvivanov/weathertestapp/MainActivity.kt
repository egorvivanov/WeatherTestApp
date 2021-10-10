package com.egorvivanov.weathertestapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.egorvivanov.weathertestapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter


    companion object {

        val weathers = arrayOf(
            "WeatherCurrent",
            "WeatherDaily",
            "WeatherWeekly"
        )

        lateinit var sharedPreferences: SharedPreferences
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Подключаем ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.slidingTabs

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = weathers[position]
        }.attach()



//
//
//
//        binding.slidingTabs.addTab(binding.slidingTabs.newTab().setText("One"))
//        binding.slidingTabs.addTab(binding.slidingTabs.newTab().setText("Two"))
//        binding.slidingTabs.addTab(binding.slidingTabs.newTab().setText("Three"))
//
//        binding.slidingTabs.tabGravity = TabLayout.GRAVITY_FILL


    }


}