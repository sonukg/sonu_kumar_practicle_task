package com.sonukg.sonu_kumar_practicle_task.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sonukg.sonu_kumar_practicle_task.repository.PhotosRepository
import com.sonukg.sonu_kumar_practicle_task.viewmodel.MainViewModel

class MainViewModelFactory(private val photosRepository: PhotosRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(photosRepository) as T
    }
}