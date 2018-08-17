package com.zx.kotlin_android.app

import android.app.Application
import com.zx.kotlin_android.test.DelegatestExt
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        var instance: App by DelegatestExt.NotNullSingleValue()
//        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}