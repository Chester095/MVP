package com.chester095.mvp.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.chester095.mvp.R
import com.chester095.mvp.app
import com.chester095.mvp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var viewModel: LoginContract.ViewModel? = null
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeOrange)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = restoreViewModel()

        initSetOnClickListener()
        initViewModelSubscribe()
    }

    private fun initViewModelSubscribe() {
        initShouldShowProgress()
        initIsSuccess()
        initErrorText()
    }

    private fun initShouldShowProgress() {
        viewModel?.shouldShowProgress?.subscribe(handler) { shouldShow ->
            if (shouldShow == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }
    }

    private fun initIsSuccess() {
        viewModel?.isSuccess?.subscribe(handler) {
            if (it == true) {
                setSuccess()
            }
        }
    }

    private fun initErrorText() {
        viewModel?.errorText?.subscribe(handler) {
            it?.let {
                val success = viewModel?.isSuccess?.value
                if (success == false) {
                    setError(it)
                }
            }
        }
    }

    private fun initSetOnClickListener() {
        initLoginButtonSetOnClickListener()
        initSignupButtonSetOnClickListener()
        initPasswordRecoveryButtonSetOnClickListener()
    }

    private fun initLoginButtonSetOnClickListener() {
        binding.loginButton.setOnClickListener {
            viewModel?.onLogin(
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

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.isSuccess?.unsubscribeAll()
        viewModel?.errorText?.unsubscribeAll()
        viewModel?.shouldShowProgress?.unsubscribeAll()
    }

    private fun restoreViewModel(): LoginViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel
        return viewModel ?: LoginViewModel(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun setSuccess() {
        startActivity(Intent(this, FirstActivity::class.java))
    }

    private fun setError(error: String) {
        Toast.makeText(this, "ERROR $error", Toast.LENGTH_SHORT).show()
        hideKeyboard(this)
    }

    private fun showProgress() {
        binding.loginActivityImageView.isVisible = true
        binding.loginActivityImageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.loginActivityImageView.load(R.drawable.progress_animation)
    }

    private fun hideProgress() {
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