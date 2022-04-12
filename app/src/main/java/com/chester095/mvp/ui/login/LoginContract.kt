package com.chester095.mvp.ui.login

import androidx.annotation.MainThread
import com.chester095.mvp.utils.Publisher

class LoginContract {

    interface ViewModel {
        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onCredentialsChanged()
    }

}