package com.zx.kotlin_android.db.helper

import android.database.sqlite.SQLiteDatabase
import com.zx.kotlin_android.app.App
import com.zx.kotlin_android.db.CityForecastTable
import com.zx.kotlin_android.db.DayForecastTable
import org.jetbrains.anko.db.*

class ForecastDBHelper(): ManagedSQLiteOpenHelper(App.instance, ForecastDBHelper.DB_NAME,null,ForecastDBHelper.DB_VERSION){
    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(CityForecastTable.NAME,true)
        p0.dropTable(DayForecastTable.NAME,true)
        onCreate(p0)
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.createTable(CityForecastTable.NAME,true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        p0.createTable(DayForecastTable.NAME,true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)
    }

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance: ForecastDBHelper by lazy { ForecastDBHelper() }
    }


}