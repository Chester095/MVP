package com.chester095.mvp.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
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

class LoginFragment : Fragment() {
    companion object {
        fun newInstance(presenter: LoginContract.Presenter?): LoginFragment = LoginFragment()
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        Log.d("!!!", "onViewCreated  bundle= "+ bundle)
        val myValue = bundle!!.getBundle("LoginFragment")
        Log.d("!!!", "onViewCreated  Presenter= "+ MainActivity().presenter)
        Log.d("!!!", "onViewCreated  myValue= "+ myValue)
        initSetOnClickListener()
    }

    private fun initSetOnClickListener() {
        binding.loginButton.setOnClickListener { MainActivity().usePresenter() }
        binding.signupButton.setOnClickListener { navigateTo(RegistrationFragment()) }
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

    fun login(): String {
       return binding.loginEditText.text.toString()
    }

    fun password(): String {
        return binding.passwordEditText.text.toString()
    }

    fun setError(error: String) {
        Toast.makeText(requireContext(), "ERROR $error", Toast.LENGTH_SHORT).show()
        hideKeyboard()
    }

    fun showProgress() {
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

    fun hideProgress() {
        binding.notRememberPasswordLayout.isVisible = true
        binding.loginFragmentImageView.isVisible = false
    }

}