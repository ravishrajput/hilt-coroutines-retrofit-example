package com.ravish.hilt.coroutines.retrofit.example.hilt

import com.ravish.hilt.coroutines.retrofit.example.api.FakeApiService
import com.ravish.hilt.coroutines.retrofit.example.data.network.ApiServices
import com.ravish.hilt.coroutines.retrofit.example.di.ApiServiceModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ApiServiceModule::class])
abstract class FakeApiServiceModule {

    @Binds
    @Singleton
    abstract fun providesApiServices(fakeApiService: FakeApiService): ApiServices
}