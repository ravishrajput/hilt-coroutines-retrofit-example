package com.ravish.hilt.coroutines.retrofit.example

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonProvider {

    inline fun <reified U> objectFromJsonFileWithType(filePath: String): U =
        Gson().fromJson(fileAsString(filePath), object : TypeToken<U>() {}.type)

    fun fileAsString(filePath: String): String = this::class.java
        .getResourceAsStream(filePath)!!
        .bufferedReader()
        .use { it.readText() }
}