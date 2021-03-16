package com.sonukg.sonu_kumar_practicle_task.viewmodel

import androidx.lifecycle.ViewModel
import com.sonukg.sonu_kumar_practicle_task.repository.PhotosRepository

class MainViewModel(private val photosRepository: PhotosRepository): ViewModel() {
    fun getAllPhotosData()=photosRepository.getAllPhotoData()
}