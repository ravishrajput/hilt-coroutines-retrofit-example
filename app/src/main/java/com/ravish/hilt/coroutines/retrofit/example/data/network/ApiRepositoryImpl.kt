package com.ravish.hilt.coroutines.retrofit.example.data.network

import com.ravish.hilt.coroutines.retrofit.example.data.model.ApiResponse

class ApiRepositoryImpl(private val services: ApiServices) : ApiRepository {

    override suspend fun getData(id: String): ApiResponse {
        val queryMap = hashMapOf<String, String>()
        queryMap["id"] = id
        return services.getData("URL", queryMap)
    }
}