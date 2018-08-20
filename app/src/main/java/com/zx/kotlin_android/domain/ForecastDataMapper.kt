package com.zx.kotlin_android.domain

import com.zx.kotlin_android.data.Forecast
import com.zx.kotlin_android.domain.model.ForecastList
import com.zx.kotlin_android.data.ForecastResult
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.zx.kotlin_android.domain.model.Forecast as ModelForecast

class ForecastDataMapper {
    fun converFromDataModel(zipCode: Long,forecast: ForecastResult): ForecastList {
        return ForecastList(zipCode, forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
//        return list.map { convertForecastListToDomain(it) }
        return list.mapIndexed { index, forecast ->
            val dt = Calendar.getInstance().timeInMillis+ TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastListToDomain(forecast.copy(dt = dt))
        }
    }
    private fun convertForecastListToDomain(forecast: Forecast) = with(forecast){
      ModelForecast(-1,dt,forecast.weather[0].description,forecast.temp.max.toInt(),
                forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): Long{
//        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(date)
        return dt

    }

    private fun generateIconUrl(icon : String): String
    = "http://openweathermap.org/img/w/$icon.png"


}
