package com.zx.kotlin_android.provider

import com.zx.kotlin_android.data.ForecastDataSource
import com.zx.kotlin_android.db.helper.ForecastDb
import com.zx.kotlin_android.domain.model.ForecastList
import com.zx.kotlin_android.extensions.firstResult
import com.zx.kotlin_android.server.ForecastServer

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES as List<ForecastDataSource>) {
    companion object {
        val DAY_IN_MILLTS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long,days: Int): ForecastList = sources.firstResult { requestSource(it,days,zipCode) }

    fun requestSource(sources: ForecastDataSource,days: Int,zipCode: Long):ForecastList?{
        val res = sources.requsestForecastByZipCode(zipCode,todayTimeSpan())
        return if (res !=null && res.size()>= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis()/ DAY_IN_MILLTS* DAY_IN_MILLTS
}