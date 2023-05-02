package com.mobile.weatherforcast.model

import java.io.Serializable

data class ConditionModel(
    var text:String,
    var icon:String,
    var code:Long,
): Serializable