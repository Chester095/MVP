package com.chester095.mvp.login

class DataLoginPassword {
    private val dataLoginPassword: MutableMap<String, String> = mutableMapOf()


    fun newLoginPassword(login: String, password: String) {
        dataLoginPassword[login] = password
    }

    fun changeLoginPassword(login: String, password: String) {
        dataLoginPassword += login to password
    }

    public fun checkLoginPassword(login: String, password: String): Int {
        if (checkLogin(login)) {
            if (dataLoginPassword[login].equals(password)) return 1
            else return 2
        } else {
            return 3
        }
    }

    private fun checkLogin(login: String): Boolean {
        return dataLoginPassword.containsKey(login)
    }


}



