package com.chester095.mvp.data

import com.chester095.mvp.domain.LoginApi

class MockLoginApiImpl : LoginApi {
    private val dataMockLogin: MutableMap<String, String> = mutableMapOf()

    override fun login(login: String, password: String): Int {
        if (checkLogin(login)) {
            if (dataMockLogin[login].equals(password)) return 1
            else return 2
        } else {
            return 3
        }
    }

    override fun changePassword(login: String, password: String): Boolean {
        dataMockLogin += login to password
        return true
    }

    override fun register(login: String, password: String, email: String): Boolean {
        dataMockLogin[login] = password
        return login.isNotEmpty()
    }

    override fun logout(): Boolean {
        Thread.sleep(2_000)
        return true
    }

    override fun forgotPassword(login: String): Boolean {
        Thread.sleep(1_000)
        return false
    }

    private fun checkLogin(login: String): Boolean {
        return dataMockLogin.containsKey(login)
    }
}