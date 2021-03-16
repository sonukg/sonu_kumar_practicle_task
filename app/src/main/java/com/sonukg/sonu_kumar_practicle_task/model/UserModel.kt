package com.sonukg.sonu_kumar_practicle_task.model

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable

data class UserModel(
    private var email: String, private var password: String,
): BaseObservable(){

    fun isDataValid():Int{
        if (TextUtils.isEmpty(getEmail()))
            return 0
        else (getEmail().equals("mailto:hello@yopmail.com") && getPassword().equals("Password@123"))
            return 1
    }
    fun getEmail():String{
        return email
    }

    fun getPassword():String{
        return password
    }

    fun setEmail(email: String){
        this.email=email
    }

    fun setPassword(password: String){
        this.password=password
    }
}