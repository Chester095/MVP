package com.chester095.mvp.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.chester095.mvp.R
import com.chester095.mvp.app
import com.chester095.mvp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityLoginBinding
    var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeOrange)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = restorePresenter()
        presenter?.onAttach(this)

        initSetOnClickListener()
    }

    private fun initSetOnClickListener() {
        initLoginButtonSetOnClickListener()
        initSignupButtonSetOnClickListener()
        initPasswordRecoveryButtonSetOnClickListener()
    }

    private fun initLoginButtonSetOnClickListener() {
        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun initSignupButtonSetOnClickListener() {
        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun initPasswordRecoveryButtonSetOnClickListener() {
        binding.passwordRecoveryButton.setOnClickListener {
            TODO("запуск активити восстановления пароля")
        }
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        startActivity(Intent(this, FirstActivity::class.java))
    }

    @MainThread
    override fun setError(error: String) {
        Toast.makeText(this, "ERROR $error", Toast.LENGTH_SHORT).show()
        hideKeyboard(this)
    }

    @MainThread
    override fun showProgress() {
        binding.loginActivityImageView.isVisible = true
        binding.loginActivityImageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.loginActivityImageView.load(R.drawable.progress_animation)
    }

    @MainThread
    override fun hideProgress() {
        binding.notRememberPasswordLayout.isVisible = true
        binding.loginActivityImageView.isVisible = false
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}