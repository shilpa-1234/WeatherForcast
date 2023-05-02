package com.mobile.weatherforcast.model

import java.io.Serializable

data class ForecastDataModel(
  var forecastday:ArrayList<ForeCastDayModel>
): Serializable