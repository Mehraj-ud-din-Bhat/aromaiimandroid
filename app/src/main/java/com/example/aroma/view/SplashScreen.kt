package com.example.aroma.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.aroma.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        redirectUser()
    }

    private fun redirectUser() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, 2000)
    }

//    override fun attachBaseContext(newBase: Context?) {
//
//        //super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
//
//    }
}