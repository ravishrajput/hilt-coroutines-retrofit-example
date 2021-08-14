package com.ravish.hilt.coroutines.retrofit.example.data.network

import com.ravish.hilt.coroutines.retrofit.example.data.model.User
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getUser(): Flow<User>
}