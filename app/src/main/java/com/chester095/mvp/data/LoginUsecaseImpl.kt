package com.chester095.mvp.data

import androidx.annotation.MainThread
import com.chester095.mvp.domain.LoginApi
import com.chester095.mvp.domain.LoginUsecase

class LoginUsecaseImpl(
    private val api: LoginApi
) : LoginUsecase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: (Int) -> Unit
    ) {
        Thread {
            val result = api.login(login, password)
            callback(result)
        }.start()
    }
}