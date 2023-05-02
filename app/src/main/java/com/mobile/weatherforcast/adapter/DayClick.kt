package com.mobile.weatherforcast.adapter

import com.mobile.weatherforcast.model.ForeCastDayModel

interface DayClick{
    fun onClick(position: Int,foreCastDayModel: ForeCastDayModel)
}