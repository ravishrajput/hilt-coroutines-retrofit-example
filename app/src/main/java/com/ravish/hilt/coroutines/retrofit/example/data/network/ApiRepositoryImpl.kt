package com.ravish.hilt.coroutines.retrofit.example.data.network

import com.ravish.hilt.coroutines.retrofit.example.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(private val services: ApiServices) : ApiRepository {

    override suspend fun getUser(): Flow<User> = flow {
        emit(services.getUser())
    }
}