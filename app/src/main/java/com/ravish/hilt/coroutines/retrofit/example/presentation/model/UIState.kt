package com.ravish.hilt.coroutines.retrofit.example.presentation.model

sealed class UIState<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T?) : UIState<T>(data)
    class Loading<T> : UIState<T>()
    class Error<T>(message: String?) : UIState<T>(error = message)
}