package com.mobile.weatherforcast.model

import java.io.Serializable

data class AstroModel(
 var sunrise:String,
 var sunset:String,
 var moonrise:String,
 var moonset:String,
 var moon_phase:String,
 var moon_illumination:String,
 var is_moon_up:Int,
 var is_sun_up:Int
): Serializable