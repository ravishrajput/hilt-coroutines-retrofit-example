package com.ravish.hilt.coroutines.retrofit.example.data.network

import com.ravish.hilt.coroutines.retrofit.example.data.model.ApiResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiServices {

    @GET
    suspend fun getData(@Url url: String, @QueryMap queryMap: Map<String, String>): ApiResponse

    companion object Factory {
        fun create(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }
}