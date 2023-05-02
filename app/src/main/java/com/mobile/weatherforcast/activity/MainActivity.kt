package com.mobile.weatherforcast.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.weatherforcast.WeatherRepository
import com.mobile.weatherforcast.WeatherViewModel
import com.mobile.weatherforcast.WeatherViewModelFactory
import com.mobile.weatherforcast.adapter.DayClick
import com.mobile.weatherforcast.adapter.DaysListAdapter
import com.mobile.weatherforcast.databinding.ActivityMainBinding
import com.mobile.weatherforcast.model.ForeCastDayModel
import com.mobile.weatherforcast.network.ApiInterface

class MainActivity : AppCompatActivity(),DayClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var daysListAdapter: DaysListAdapter
    private lateinit var viewModel: WeatherViewModel
    private val retrofitService = ApiInterface.getInstance()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Days List"
        viewModel = ViewModelProvider(this, WeatherViewModelFactory(WeatherRepository(retrofitService)))[WeatherViewModel::class.java]
        viewModel.forecast("Jaipur","7")
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        viewModel.weatherData.observe(this) {
            daysListAdapter = DaysListAdapter(it.forecast.forecastday, this,this)
            binding.recyclerView.adapter = daysListAdapter
            daysListAdapter.notifyDataSetChanged()
        }
    }

    override fun onClick(position: Int, foreCastDayModel: ForeCastDayModel) {
       val intent=Intent(this@MainActivity,WeatherDetailActivity::class.java)
       intent.putExtra("foreCastData",foreCastDayModel)
       startActivity(intent)
    }
}