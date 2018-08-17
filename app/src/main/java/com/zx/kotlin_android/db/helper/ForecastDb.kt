package com.zx.kotlin_android.db.helper

import com.zx.kotlin_android.domain.ForecastDataMapper

class ForecastDb(val forecastDBHelper: ForecastDBHelper = ForecastDBHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper())