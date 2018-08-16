package com.zx.kotlin_android.domain

public interface Command<T>{
    fun execute(): T
}