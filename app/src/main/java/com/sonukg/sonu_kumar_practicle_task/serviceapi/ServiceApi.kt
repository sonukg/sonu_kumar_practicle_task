package com.sonukg.sonu_kumar_practicle_task.serviceapi

import com.sonukg.sonu_kumar_practicle_task.model.PhotosModel
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceApi {
    @GET("photos")
    fun getAllPhotosData(): Single<List<PhotosModel>>
}