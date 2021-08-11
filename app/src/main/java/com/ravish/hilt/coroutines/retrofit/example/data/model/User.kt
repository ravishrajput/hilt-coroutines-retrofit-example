package com.ravish.hilt.coroutines.retrofit.example.data.model

data class User(
    val avatar_url: String,
    val followers: Int,
    val id: Int,
    val location: String,
    val name: String,
    val public_repos: Int,
    val url: String
)