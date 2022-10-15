package com.example.foodreviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val delayToMain : Long = 1000

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    //splash screen delay to login activity
    override fun onResume() {
        super.onResume()
        Thread.sleep(delayToMain)
        startActivity(Intent(this@SplashActivity, LogInActivity::class.java))
        finish()
    }
}