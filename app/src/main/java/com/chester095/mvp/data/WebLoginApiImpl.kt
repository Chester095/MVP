package com.chester095.mvp.data

import com.chester095.mvp.domain.LoginApi

class WebLoginApiImpl : LoginApi {
    fun lol() {
        // todo
    }

    override fun login(login: String, password: String): Int {
        // todo make request
        TODO("Not yet implemented")
    }

    override fun changePassword(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(login: String, password: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}