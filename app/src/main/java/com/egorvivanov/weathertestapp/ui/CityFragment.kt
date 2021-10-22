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

        cityViewModel.getCoordinates().observe(viewLifecycleOwner, Observer {

            parentFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                WeatherAllFragment.newInstance(it.latitude, it.longitude),
                WeatherAllFragment::class.java.simpleName
            ).commit()
        })

        _binding.btnCurrentCity.setOnClickListener {
            cityViewModel.useCurrentLocationCoordinates()
        }


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
            }
        }
    }

    companion object {
        fun newInstance() = CityFragment()
    }
}