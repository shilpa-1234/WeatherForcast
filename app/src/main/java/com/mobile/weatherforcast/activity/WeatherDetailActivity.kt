package com.mobile.weatherforcast.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.weatherforcast.R
import com.mobile.weatherforcast.adapter.HourListAdapter
import com.mobile.weatherforcast.databinding.ActivityWeatherDetailBinding
import com.mobile.weatherforcast.model.ForeCastDayModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherDetailBinding
    private lateinit var foreCastDayModel: ForeCastDayModel
    private lateinit var adapter: HourListAdapter
    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        binding = ActivityWeatherDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Weather Detail"
        val intent=intent
        foreCastDayModel=intent.getSerializableExtra("foreCastData") as ForeCastDayModel
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = inFormat.parse(foreCastDayModel.date)!!
        val outFormat = SimpleDateFormat("EEEE dd/MM/yyyy")
        val newDate: String = outFormat.format(date)
        binding.textDate.text = newDate
        Glide.with(this).load(foreCastDayModel.day.condition.icon.replace("//","https://").trim()).into(binding.imageView)
        adapter= HourListAdapter(foreCastDayModel.hour,this)
        binding.recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }
}