package com.chester095.mvp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chester095.mvp.R
import com.chester095.mvp.databinding.ActivityLoginBinding
import com.chester095.mvp.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeOrange)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSetOnClickListener()
    }


    private fun initSetOnClickListener() {
        initRegistrationButtonSetOnClickListener()
        initHaveAccountButtonSetOnClickListener()
    }

    private fun initRegistrationButtonSetOnClickListener() {
        binding.registrationButton.setOnClickListener { registration() }
    }

    private fun initHaveAccountButtonSetOnClickListener() {
        binding.registrationButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java)) }
    }

     private fun registration() {
        if (checkingLogin() && checkingPasswords()) {
            savingCredentials()
            startActivity(Intent(this, FirstActivity::class.java))
        }
    }

    private fun checkingLogin(): Boolean {
        return if (binding.passwordEditText == binding.passwordConfirmEditText) {
            true
        } else {
            Toast.makeText(this, "Такой логин уже существует", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun checkingPasswords(): Boolean {
        return if (binding.passwordEditText == binding.passwordConfirmEditText) {
            true
        } else {
            Toast.makeText(this, "Пароли должны совпадать", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun savingCredentials() {
        TODO("сохранение данных")
    }

}