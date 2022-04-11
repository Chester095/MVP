package com.chester095.mvp.data

import android.os.Handler
import androidx.annotation.MainThread
import com.chester095.mvp.domain.LoginApi
import com.chester095.mvp.domain.LoginUsecase

class LoginUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUsecase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: (Int) -> Unit
    ) {
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}