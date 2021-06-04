package com.example.lowescodechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lowescodechallenge.databinding.ActivityMainBinding
import com.example.lowescodechallenge.view.CityWeather
import com.example.lowescodechallenge.view.CityWeatherDetail
import com.example.lowescodechallenge.view.CityFragment

class MainActivity : AppCompatActivity(), CityFragment.ISearchCity,
    CityWeather.IDetailFragment {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inflateCitySearchFragment()
    }

    private fun openDetailFragment(position: Int){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CityWeatherDetail.newInstance(position))
            .addToBackStack(null)
            .commit()
    }

    private fun openWeatherFragment(cityName: String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CityWeather.newInstance(cityName))
            .commit()
    }

    private fun inflateCitySearchFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CityFragment())
            .commit()
    }

    override fun searchData(cityName: String) {
        openWeatherFragment(cityName)
    }

    override fun displayDetail(position: Int) {
        openDetailFragment(position)
    }

}