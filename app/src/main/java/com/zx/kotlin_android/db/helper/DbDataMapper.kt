package com.zx.kotlin_android.db.helper

import com.zx.kotlin_android.domain.model.CityForecast
import com.zx.kotlin_android.domain.model.DayForecast
import com.zx.kotlin_android.domain.model.Forecast
import com.zx.kotlin_android.domain.model.ForecastList

class DbDataMapper {
    fun convertFromDomain(forecastList: ForecastList) = with(forecastList){
        val daily = dailyForecast.map { covertDayFormDomin(id,it) }
        CityForecast(id,city,country,daily)
    }

    private fun covertDayFormDomin(id: Long, it: Forecast) = with(it) {
        DayForecast(date,description,high,low,icon,id)
    }
    fun covertToDomain(forecast: CityForecast) = with(forecast){
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id,city,country,daily)
    }

    private fun convertDayToDomain(dayForecast: DayForecast)= with(dayForecast) {
        Forecast(_id,date,description,high,low,iconUrl)

    }
}
