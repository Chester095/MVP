package com.chester095.mvp.ui.login

import com.chester095.mvp.domain.LoginUsecase
import com.chester095.mvp.utils.Publisher

class LoginViewModel(
    private val loginUsecase: LoginUsecase
) : LoginContract.ViewModel {
    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher(true)

    override fun onLogin(login: String, password: String) {
        shouldShowProgress.post(true)
        loginUsecase.login(login, password) { result ->
            shouldShowProgress.post(false)
            when (result) {
                1 -> {
                    isSuccess.post(true)
                    errorText.post("")
                }
                2 -> {
                    isSuccess.post(false)
                    errorText.post("Неверный пароль!!!")
                }
                3 -> {
                    isSuccess.post(false)
                    errorText.post("Такого пользователя не существует!!!")
                }
            }
        }
    }

    override fun onCredentialsChanged() {
        TODO("Not yet implemented")
    }

}