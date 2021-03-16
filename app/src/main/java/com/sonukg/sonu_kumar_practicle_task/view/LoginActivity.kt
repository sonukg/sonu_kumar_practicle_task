package com.sonukg.sonu_kumar_practicle_task.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.sonukg.sonu_kumar_practicle_task.MainActivity
import com.sonukg.sonu_kumar_practicle_task.R
import com.sonukg.sonu_kumar_practicle_task.`interface`.LoginResultCallbacks
import com.sonukg.sonu_kumar_practicle_task.databinding.ActivityLoginBinding
import com.sonukg.sonu_kumar_practicle_task.viewmodel.LoginActivityViewModel
import com.sonukg.sonu_kumar_practicle_task.viewmodel.factory.LoginViewModelFactory

class LoginActivity : AppCompatActivity(), LoginResultCallbacks {
    private var binding:ActivityLoginBinding?=null
    private var loginViewModel:LoginActivityViewModel?=null
    private var sharedPrefsHelper:SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel= ViewModelProviders.of(this, LoginViewModelFactory(this))
            .get(LoginActivityViewModel::class.java)
        binding?.setLifecycleOwner(this)
        binding?.viewModel=loginViewModel
        sharedPrefsHelper=getSharedPreferences("login", Context.MODE_PRIVATE)

    }
    override fun onSuccess(message: String) {
        val editor:SharedPreferences.Editor= sharedPrefsHelper!!.edit()
        editor.putString(EMAIL_KEY, binding!!.email.getText().toString())
        editor.putString(PASSWORD_KEY, binding!!.password.getText().toString())
        editor.putBoolean(IS_USER_LOGIN, true)
        editor.apply()

        val intent=Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    companion object{
        val IS_USER_LOGIN:String="IsUserLoggedIn"
        val EMAIL_KEY:String = "email_key"
        val PASSWORD_KEY:String = "password_key"
    }


}