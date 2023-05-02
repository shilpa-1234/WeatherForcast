package com.mobile.weatherforcast.model

import java.io.Serializable

data class ForecastModel(
    var location: LocationModel,
    var current: CurrentModel,
    var forecast: ForecastDataModel,
): Serializable

