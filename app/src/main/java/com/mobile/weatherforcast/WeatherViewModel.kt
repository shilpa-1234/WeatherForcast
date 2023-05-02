package com.mobile.weatherforcast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.weatherforcast.model.ForecastModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel constructor(private val repository: WeatherRepository) : ViewModel(){
    val weatherData = MutableLiveData<ForecastModel>()
    val errorMessage = MutableLiveData<String>()
    fun forecast(query:String,days:String) {
        val response = repository.forecast(query, days)
        response.enqueue(object : Callback<ForecastModel> {
            override fun onResponse(call: Call<ForecastModel>, response: Response<ForecastModel>) {
                if(response.isSuccessful) {
                    weatherData.postValue(response.body())
                }else{
                    errorMessage.postValue("Api Error "+response.raw())
                }
            }
            override fun onFailure(call: Call<ForecastModel>, t: Throwable) {
                errorMessage.postValue("Exception "+t.message)
            }
        })
    }

}