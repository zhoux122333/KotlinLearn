package com.zx.kotlin_android.http

import com.google.gson.Gson
import com.zx.kotlin_android.data.ForecastResult
import java.net.URL

public class ForecastRequest(val zipCode: String){
    companion object {
        private val APP_ID = "b6907d289e10d714a6e88b30761fae22"
        private val URL = "http://api.openweathermap.org/data/2.5/"+"forecast/daily?id=524901&lang=zh_cn"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult{
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr,ForecastResult::class.java)
    }
}