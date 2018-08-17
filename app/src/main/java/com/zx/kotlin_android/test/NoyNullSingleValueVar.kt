package com.zx.kotlin_android.test

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private class NoyNullSingleValueVar<T>(): ReadWriteProperty<Any?,T>{
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value?: throw IllegalStateException("name"+"not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("name already initialized")
    }


}
object DelegatestExt{
    fun <T> NotNullSingleValue():
            ReadWriteProperty<Any?,T> = NoyNullSingleValueVar()
}