package com.chester095.mvp.domain

import androidx.annotation.MainThread

interface LoginUsecase {
    fun login(
        login: String,
        password: String,
        @MainThread callback: (Int) -> Unit
    )
}