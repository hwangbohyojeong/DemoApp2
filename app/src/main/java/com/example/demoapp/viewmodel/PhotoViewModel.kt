package com.example.demoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.demoapp.model.PhotoModel
import com.example.demoapp.repository.PhotoRepository

class PhotoViewModel(application: Application): AndroidViewModel(application) {
    private val mPhotoRepository: PhotoRepository
    private var mPhotoItems: LiveData<List<PhotoModel>>

    init {
        mPhotoRepository = PhotoRepository(application)
        mPhotoItems = mPhotoRepository.getPhotoList()
    }

    fun insertPhoto(photoModel: PhotoModel) {
        mPhotoRepository.insertPhoto(photoModel)
    }

    fun deletePhoto() {
        mPhotoRepository.deletePhoto()
    }

    fun getPhotoList(): LiveData<List<PhotoModel>> {
        return mPhotoItems
    }
}
