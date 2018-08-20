package com.zx.kotlin_android.test

import android.database.sqlite.SQLiteDatabase

class Test1(){
    fun <T> use(f: SQLiteDatabase.() -> T): T? {
        val a: Int? =null
        a.toString()

        return null
    }
}