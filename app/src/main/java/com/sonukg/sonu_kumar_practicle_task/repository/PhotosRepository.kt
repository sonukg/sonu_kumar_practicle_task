package com.sonukg.sonu_kumar_practicle_task.repository

import com.sonukg.sonu_kumar_practicle_task.serviceapi.ServiceApi

class PhotosRepository(private val api: ServiceApi) {
    fun getAllPhotoData()=api.getAllPhotosData()
}