package com.egorvivanov.weathertestapp.ui


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.egorvivanov.weathertestapp.R

import com.egorvivanov.weathertestapp.databinding.FragmentCityBinding
import com.egorvivanov.weathertestapp.ui.factory.CityViewModelFactory
import com.egorvivanov.weathertestapp.ui.viewmodel.CityViewModel

class CityFragment : Fragment() {

    private val cityViewModel: CityViewModel by viewModels {
        CityViewModelFactory(requireContext().applicationContext)
    }

    private var latitude = 0.0F
    private var longitude = 0.0F

    private lateinit var _binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCityBinding
            .inflate(inflater, container, false)
        val view = _binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btnFindCity.setOnClickListener {
            if (_binding.etCityInputName.text.isEmpty() ||
                _binding.etCityInputName.text == null
            ) {
                Toast.makeText(
                    context,
                    "Ошибка! Попробуйте другое название города",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                cityViewModel.saveCityLocation(
                    _binding.etCityInputName.text.toString()
                )

                cityViewModel.getLatitudeData().observe(this, Observer {
                    latitude = it
                })

                cityViewModel.getLongitudeData().observe(this, Observer {
                    longitude = it
                })

                parentFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    WeatherAllFragment.newInstance(latitude, longitude),
                    WeatherAllFragment::class.java.simpleName
                ).commit()
            }
        }


        _binding.btnCurrentCity.setOnClickListener {

            cityViewModel.useCurrentLocationCoordinates(context)

            cityViewModel.getLatitudeData().observe(this, Observer {
                latitude = it
            })

            cityViewModel.getLongitudeData().observe(this, Observer {
                longitude = it
            })

            parentFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                WeatherAllFragment.newInstance(latitude, longitude),
                WeatherAllFragment::class.java.simpleName
            ).commit()
        }
    }

    companion object {
        fun newInstance() = CityFragment()
    }
}