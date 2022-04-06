package com.chester095.mvp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chester095.mvp.R
import com.chester095.mvp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() , LoginContract.Registration {
    companion object {
    }

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetOnClickListener()
    }

    private fun initSetOnClickListener() {
        binding.registrationButton.setOnClickListener { registration() }
        binding.haveAccountButton.setOnClickListener { navigateTo(LoginFragment()) }
    }

    override fun registration() {
        if (checkingLogin() && checkingPasswords()) {
            savingCredentials()
            navigateTo(FirstFragment())
        }
    }

    private fun checkingLogin(): Boolean {
        return if (binding.passwordEditText == binding.passwordConfirmEditText) {
            true
        } else {
            Toast.makeText(requireContext(), "Такой логин уже существует", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun checkingPasswords(): Boolean {
        return if (binding.passwordEditText == binding.passwordConfirmEditText) {
            true
        } else {
            Toast.makeText(requireContext(), "Пароли должны совпадать", Toast.LENGTH_SHORT).show()
            false
        }
    }

    override fun savingCredentials() {
        TODO("Наверное тут надо использовать RegistrationPresenter. Жду команды проверяющего.")
    }


    private fun navigateTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.container, fragment).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}