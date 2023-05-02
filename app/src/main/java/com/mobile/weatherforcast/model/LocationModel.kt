package com.mobile.weatherforcast.model

import java.io.Serializable

data class LocationModel(
    var name:String,
    var region:String,
    var country:String,
    var lat:Double,
    var lon:Double,
    var tz_id:String,
    var localtime_epoch:Long,
    var localtime:String,
): Serializable