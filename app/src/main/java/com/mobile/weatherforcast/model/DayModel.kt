package com.mobile.weatherforcast.model

import java.io.Serializable

data class DayModel(
    var maxtemp_c:Double,
    var maxtemp_f:Double,
    var mintemp_c:Double,
    var mintemp_f:Double,
    var avgtemp_c:Double,
    var avgtemp_f:Double,
    var maxwind_mph:Double,
    var maxwind_kph:Double,
    var totalprecip_mm:Double,
    var totalprecip_in:Double,
    var totalsnow_cm:Double,
    var avgvis_km:Double,
    var avgvis_miles:Double,
    var avghumidity:Double,
    var daily_will_it_rain:Any,
    var daily_chance_of_rain:Any,
    var daily_will_it_snow:Any,
    var daily_chance_of_snow:Any,
    var condition: ConditionModel,
    var uv:Double,
): Serializable