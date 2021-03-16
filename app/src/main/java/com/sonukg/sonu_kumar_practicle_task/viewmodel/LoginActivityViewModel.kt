package com.sonukg.sonu_kumar_practicle_task.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.sonukg.sonu_kumar_practicle_task.`interface`.LoginResultCallbacks
import com.sonukg.sonu_kumar_practicle_task.model.UserModel

class LoginActivityViewModel(private val listeners: LoginResultCallbacks):ViewModel() {
    private val userModel:UserModel

    init {
        this.userModel=UserModel("mailto:hello@yopmail.com", "Password@123")
    }
    val emailTextWatcher:TextWatcher
    get() = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                userModel.setEmail(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {

        }

    }
    val passwordTextWatcher:TextWatcher
    get() = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            userModel.setPassword(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {

        }

    }
    fun onLoginClicked(view: View){

        val loginCode:Int=userModel.isDataValid()
        if (loginCode==0)
            listeners.onError("Email must not be null")
        else
            listeners.onSuccess("Welcome Login successful")
    }

}