package com.zx.kotlin_android.domain

import com.zx.kotlin_android.domain.model.ForecastList
import com.zx.kotlin_android.http.ForecastRequest

class RequestForecastCommand(val zipCode: String): Command<ForecastList>{
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().converFromDataModel(forecastRequest.execute())
    }

}