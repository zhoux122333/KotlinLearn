package com.zx.kotlin_android.domain

import com.zx.kotlin_android.domain.model.ForecastList
import com.zx.kotlin_android.http.ForecastRequest
import com.zx.kotlin_android.provider.ForecastProvider

class RequestForecastCommand(val zipCode: Long, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {
    companion object {
        val DAYS =  7
    }
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }

}