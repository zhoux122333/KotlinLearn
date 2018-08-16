package com.zx.kotlin_android.domain

import com.zx.kotlin_android.data.Forecast
import com.zx.kotlin_android.domain.model.ForecastList
import com.zx.kotlin_android.data.ForecastResult
import java.text.DateFormat
import java.util.*
import com.zx.kotlin_android.domain.model.Forecast as ModelForecast

class ForecastDataMapper {
    fun converFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastListToDomain(it) }
    }
    private fun convertForecastListToDomain(forecast: Forecast):ModelForecast{
        return ModelForecast(convertDate(forecast.dt),forecast.weather[0].description,forecast.temp.max.toInt(),
                forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date*1000)

    }

    private fun generateIconUrl(icon : String): String
    = "http://openweathermap.org/img/w/$icon.png"


}
