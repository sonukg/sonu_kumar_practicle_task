package com.sonukg.sonu_kumar_practicle_task.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sonukg.sonu_kumar_practicle_task.MainActivity
import com.sonukg.sonu_kumar_practicle_task.view.LoginActivity.Companion.IS_USER_LOGIN


class SplashActivity : AppCompatActivity() {
    private var sharedPreferences:SharedPreferences?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isUserLoggedIn()){
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
    fun isUserLoggedIn(): Boolean {
        sharedPreferences=getSharedPreferences("login", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean(IS_USER_LOGIN, false)
    }
}