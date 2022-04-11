package com.chester095.mvp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chester095.mvp.R
import com.chester095.mvp.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeOrange)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
