package com.ravish.hilt.coroutines.retrofit.example.data.network

import com.ravish.hilt.coroutines.retrofit.example.data.model.ApiResponse

interface ApiRepository {

    suspend fun getData(id: String): ApiResponse
}