package com.chester095.mvp.domain.entities

data class UserProfile(
    val id: String,
    val login: String,
    val email: String,
    val avatarUrl: String,
    val pin: Int
)