package com.chester095.mvp.ui.login

import com.chester095.mvp.data.MockLoginApiImpl
import com.chester095.mvp.domain.LoginUsecase

class LoginPresenter(
    private val loginUsecase: LoginUsecase
) : LoginContract.Presenter {

    private var mockLoginApiImpl: MockLoginApiImpl? = MockLoginApiImpl()
    private var view: LoginContract.View? = null
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: LoginContract.View) {
        testInitialDB()
        this.view = view
    }

    private fun testInitialDB() {
        mockLoginApiImpl?.register("Mark", "dfgsdfgs", "123@mail.ru")
        mockLoginApiImpl?.register("Jone", "123", "234@mail.ru")
        mockLoginApiImpl?.register("Kingsmarauli", "qwe", "345@mail.ru")
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        loginUsecase.login(login, password) { result ->
            when (result) {
                1 -> {
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                }
                2 -> {
                    view?.setError("Неверный пароль!!!")
                    isSuccess = false
                    errorText = "Неверный пароль!!!"
                }
                3 -> {
                    view?.setError("Такого пользователя не существует!!!")
                    isSuccess = false
                    errorText = "Такого пользователя не существует!!!"
                }
            }
        }
        view?.hideProgress()
    }

}