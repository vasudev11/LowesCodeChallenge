package com.example.lowescodechallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowescodechallenge.databinding.ForecastItemLayoutBinding
import com.example.lowescodechallenge.model.WeatherData
import kotlin.math.roundToInt

class CityWeatherAdapter(private var dataSet: List<WeatherData>? = null,
                         private val callback: (Int) -> Unit): RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder>() {

    class CityWeatherViewHolder(private val binding: ForecastItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun onBind(forecastItem: WeatherData, callback: (Int)-> Unit){
                binding.root.setOnClickListener { callback(adapterPosition) }

                binding.weatherCondition.text = forecastItem.weather[0].main
                binding.weatherTemp.text = "Temp: " + forecastItem.main.temp.roundToInt().toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CityWeatherViewHolder(ForecastItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))

    override fun onBindViewHolder(holder: CityWeatherViewHolder, position: Int) {
        dataSet?.let{
            holder.onBind(it[position], callback)
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0

    fun updateDataSet(dataSet: List<WeatherData>?){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}