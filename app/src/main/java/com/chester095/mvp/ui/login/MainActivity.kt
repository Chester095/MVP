package com.chester095.mvp.ui.login

import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chester095.mvp.R
import com.chester095.mvp.app
import com.chester095.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityMainBinding
    var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeOrange)
        setContentView(R.layout.activity_main)
        presenter = restorePresenter()
        Log.d("!!!", "onCreate()  Presenter= $presenter")
        presenter?.onAttach(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance(presenter),"LoginFragment").commit()
            Log.d("!!!", "supportFragmentManager  Presenter= $presenter")
        }
    }

    fun usePresenter() {
        Log.d("!!!", "usePresenter()  Presenter  = $presenter")
        val presenter1 = presenter
        Log.d("!!!", "usePresenter()  Presenter1 = $presenter1")
        if (presenter1 != null) presenter1.onLogin(
            LoginFragment().login(),
            LoginFragment().password()
        )
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        Log.d("!!!", "restorePresenter()  Presenter= $presenter")
        return presenter ?: LoginPresenter(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        Log.d("!!!", "onRetainCustomNonConfigurationInstance()  Presenter= $presenter")
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        navigateTo(FirstFragment())
    }

    @MainThread
    override fun setError(error: String) {
        LoginFragment().setError(error)
    }

    @MainThread
    override fun showProgress() {
        LoginFragment().showProgress()
    }

    @MainThread
    override fun hideProgress() {
        LoginFragment().hideProgress()
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.container, fragment).addToBackStack("").commit()
    }


}