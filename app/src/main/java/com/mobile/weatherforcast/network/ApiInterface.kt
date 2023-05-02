package com.mobile.weatherforcast.network

import com.mobile.weatherforcast.model.ForecastModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("forecast.json")
    fun forecast(
        @Query("q") query:String,
        @Query("days") days:String
    ): Call<ForecastModel>
    companion object {
        private var retrofitService: ApiInterface? = null

        fun getInstance(): ApiInterface {
            if (retrofitService == null) {
                retrofitService = RetrofitInstance.getRetrofit()!!.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }
    }

}