package com.ravish.hilt.coroutines.retrofit.example.data.model

data class User(
    val avatar_url: String,
    var followers: Int,
    val id: Int,
    var location: String,
    var name: String,
    val public_repos: Int,
    val url: String
)