package com.chester095.mvp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chester095.mvp.R
import com.chester095.mvp.login.LoginFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyThemeOrange)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance()).commit()
        }
    }



}