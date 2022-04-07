package com.chester095.mvp.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.chester095.mvp.R
import com.chester095.mvp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() , LoginContract.View {
    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

    private var presenter: LoginContract.Presenter? = null
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetOnClickListener()
    }

    private fun initSetOnClickListener() {
        binding.loginButton.setOnClickListener {usePresenter()}
        binding.signupButton.setOnClickListener { navigateTo(RegistrationFragment()) }
    }

    private fun usePresenter() {
        presenter = restorePresenter()
        presenter?.onAttach(this)
        presenter?.onLogin(
            binding.loginEditText.text.toString(),
            binding.passwordEditText.text.toString()
        )
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = setRetainInstance(true) as? LoginPresenter
        return presenter ?: LoginPresenter(app.loginUsecase)
    }

    private fun navigateTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.container, fragment).addToBackStack("").commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setSuccess() {
        navigateTo(FirstFragment())
    }

    override fun setError(error: String) {
        Toast.makeText(requireContext(), "ERROR $error", Toast.LENGTH_SHORT).show()
        hideKeyboard()
    }

    override fun showProgress() {
        binding.loginFragmentImageView.isVisible = true
        binding.loginFragmentImageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.loginFragmentImageView.load(R.drawable.progress_animation)
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun hideProgress() {
        binding.notRememberPasswordLayout.isVisible = true
        binding.loginFragmentImageView.isVisible = false
    }

}