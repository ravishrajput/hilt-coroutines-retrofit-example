package com.ravish.hilt.coroutines.retrofit.example.di

import com.ravish.hilt.coroutines.retrofit.example.data.network.ApiRepository
import com.ravish.hilt.coroutines.retrofit.example.data.network.ApiRepositoryImpl
import com.ravish.hilt.coroutines.retrofit.example.data.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class AppRepoModule {
    @Provides
    fun providesApiRepository(apiServices: ApiServices): ApiRepository =
        ApiRepositoryImpl(apiServices)
}

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    fun providesApiServices(retrofit: Retrofit): ApiServices = ApiServices.create(retrofit)
}