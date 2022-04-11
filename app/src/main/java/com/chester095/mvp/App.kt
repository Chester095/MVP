package com.chester095.mvp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.chester095.mvp.data.LoginUsecaseImpl
import com.chester095.mvp.data.MockLoginApiImpl
import com.chester095.mvp.domain.LoginApi
import com.chester095.mvp.domain.LoginUsecase

class App : Application() {
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }