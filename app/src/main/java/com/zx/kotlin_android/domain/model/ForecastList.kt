package com.zx.kotlin_android.domain.model


data class ForecastList(val id: Long,val city: String, val country: String,
                        val dailyForecast: List<Forecast>) {
    operator fun get(position: Int) = dailyForecast[position]
    fun size() = dailyForecast.size
}
