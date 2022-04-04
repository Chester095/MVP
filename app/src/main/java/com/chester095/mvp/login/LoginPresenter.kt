package com.chester095.mvp.login

import android.os.Handler
import android.os.Looper

class LoginPresenter : LoginContract.Presenter {

    private var dataLoginPassword: DataLoginPassword? = DataLoginPassword()
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: LoginContract.View) {
        testInitialDB()
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setError(errorText)
            view.hideProgress()
        }
    }

    private fun testInitialDB() {
        dataLoginPassword?.newLoginPassword("Mark", "dfgsdfgs")
        dataLoginPassword?.newLoginPassword("Jone", "123")
        dataLoginPassword?.newLoginPassword("Kingsmarauli", "qwe")

    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            uiHandler.post {
                when {
                    checkCredentials(login, password) == 1 -> {
                        view?.setSuccess()
                        isSuccess = true
                        errorText = ""
                    }
                    checkCredentials(login, password) == 2 -> {
                        view?.setError("Неверный пароль!!!")
                        isSuccess = false
                        errorText = "Неверный пароль!!!"
                    }
                    checkCredentials(login, password) == 3 -> {
                        view?.setError("Такого пользователя не существует!!!")
                        isSuccess = false
                        errorText = "Такого пользователя не существует!!!"
                    }
                }
            }
        }.start()
    }

    private fun checkCredentials(login: String, password: String): Int {
        return dataLoginPassword!!.checkLoginPassword(login, password)
    }

}