package com.mobile.weatherforcast

import com.mobile.weatherforcast.network.ApiInterface

class WeatherRepository constructor(private val retrofitService: ApiInterface)  {
  fun forecast(query:String,days:String)=retrofitService.forecast(query, days)
}