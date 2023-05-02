package com.mobile.weatherforcast.model

import java.io.Serializable

data class ForeCastDayModel(
    var date:String,
    var date_epoch:Long,
    var day: DayModel,
    var astro: AstroModel,
    var hour:ArrayList<HourListModel>
): Serializable