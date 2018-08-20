package com.zx.kotlin_android.data

import com.zx.kotlin_android.domain.model.ForecastList

interface ForecastDataSource {
    fun requsestForecastByZipCode(zipCode: Long,date: Long): ForecastList?
}