package com.zx.kotlin_android.test

import android.database.sqlite.SQLiteDatabase

class Test1(){
    fun <T> use(f: SQLiteDatabase.() -> T): T? {
        return null
    }
}