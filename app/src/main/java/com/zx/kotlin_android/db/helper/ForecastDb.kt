package com.zx.kotlin_android.db.helper

import com.zx.kotlin_android.data.ForecastDataSource
import com.zx.kotlin_android.db.CityForecastTable
import com.zx.kotlin_android.db.DayForecastTable
import com.zx.kotlin_android.domain.model.CityForecast
import com.zx.kotlin_android.domain.model.DayForecast
import com.zx.kotlin_android.domain.model.ForecastList
import com.zx.kotlin_android.extensions.dbClear
import com.zx.kotlin_android.extensions.parseList
import com.zx.kotlin_android.extensions.parseOpt
import com.zx.kotlin_android.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class ForecastDb(val forecastDBHelper: ForecastDBHelper = ForecastDBHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()): ForecastDataSource {
    override fun requsestForecastByZipCode(zipCode: Long, date: Long) = forecastDBHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        city?.let { dataMapper.covertToDomain(it) }
    }

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDBHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ?" + "AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        if (city != null) dataMapper.covertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDBHelper.use {
        dbClear(CityForecastTable.NAME)
        dbClear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME,*it.map.toVarargArray()) }
        }
    }
}