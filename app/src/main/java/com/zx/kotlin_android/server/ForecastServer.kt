package com.zx.kotlin_android.server

import com.zx.kotlin_android.data.ForecastDataSource
import com.zx.kotlin_android.db.helper.ForecastDb
import com.zx.kotlin_android.domain.ForecastDataMapper
import com.zx.kotlin_android.domain.model.ForecastList
import com.zx.kotlin_android.http.ForecastRequest

class ForecastServer(val dataMapper: ForecastDataMapper = ForecastDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource {
    override fun requsestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastRequest(zipCode).execute()
        val converted = dataMapper.converFromDataModel(zipCode,result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode,date)

    }
}