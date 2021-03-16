package com.sonukg.sonu_kumar_practicle_task.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sonukg.sonu_kumar_practicle_task.`interface`.LoginResultCallbacks
import com.sonukg.sonu_kumar_practicle_task.viewmodel.LoginActivityViewModel

class LoginViewModelFactory(private val listeners:LoginResultCallbacks):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginActivityViewModel(listeners) as T
    }
}