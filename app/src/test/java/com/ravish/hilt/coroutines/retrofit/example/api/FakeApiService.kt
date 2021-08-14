package com.ravish.hilt.coroutines.retrofit.example.api

import com.ravish.hilt.coroutines.retrofit.example.JsonProvider
import com.ravish.hilt.coroutines.retrofit.example.data.model.User
import com.ravish.hilt.coroutines.retrofit.example.data.network.ApiServices
import java.lang.Exception
import javax.inject.Inject

class FakeApiService @Inject constructor() : ApiServices {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun getUser(): User {
        if (failUserApi) throw Exception("Api failed")
        val fakeResponse: User = JsonProvider.objectFromJsonFileWithType(USER_JSON)

        if (wrongResponse) return fakeResponse.apply {
            name = ""
        }

        return fakeResponse
    }

    companion object {
        private const val USER_JSON = "/json/user.json"
    }
}