package com.example.lowescodechallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lowescodechallenge.databinding.CityForecastDetailFragmentBinding
import com.example.lowescodechallenge.model.CityRepository
import com.example.lowescodechallenge.model.CityRepositoryImpl
import com.example.lowescodechallenge.model.WeatherData
import com.example.lowescodechallenge.viewmodel.WeatherViewModel
import kotlin.math.roundToInt

class CityWeatherDetail: Fragment() {

    companion object{
        const val KEY_CITY_LIST_POSITION = "CityForecastDetails_KEY_CITY_LIST_POSITION"

        fun newInstance(position: Int) =
            CityWeatherDetail().apply {
                arguments = Bundle().apply {
                    putInt(KEY_CITY_LIST_POSITION, position)
                }
            }
    }

    //@Inject
    val cityRepository: CityRepository by lazy { CityRepositoryImpl() }
    val viewmodelProvider: WeatherViewModel.WeatherViewModelProvider by lazy {
        WeatherViewModel.WeatherViewModelProvider(cityRepository)
    }

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(requireActivity(), viewmodelProvider)[WeatherViewModel::class.java]
    }

    private lateinit var binding: CityForecastDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = CityForecastDetailFragmentBinding.inflate(inflater)

            viewModel.livedata.observe(viewLifecycleOwner){dataSet->
                arguments?.let { bundle->

                    updateUI(dataSet.list[bundle.getInt(KEY_CITY_LIST_POSITION)])
                }
            }

        return binding.root
    }

    private fun updateUI(weatherData: WeatherData) {
        binding.tvFeelsLikeNumberDetail.text = weatherData.main.feels_like.roundToInt().toString()
        binding.tvTempNumberDetail.text = weatherData.main.temp.roundToInt().toString()
        binding.tvWeatherStatusDetail.text = weatherData.weather[0].main
        binding.tvWeatherStatusDescriptionDetail.text = weatherData.weather[0].description
    }

}