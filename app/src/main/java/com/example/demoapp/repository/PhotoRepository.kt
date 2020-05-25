package com.example.demoapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.demoapp.database.PhotoDAO
import com.example.demoapp.database.PhotoDatabase
import com.example.demoapp.model.PhotoModel

class PhotoRepository(application: Application) {
    private var mPhotoDatabase: PhotoDatabase
    private var mPhotoDAO: PhotoDAO
    private var mPhotoItems: LiveData<List<PhotoModel>>

    init { mPhotoDatabase = PhotoDatabase.getInstance(application)
        mPhotoDAO = mPhotoDatabase.photoDao()
        mPhotoItems = mPhotoDAO.getPhotoList()
    }

    fun getPhotoList(): LiveData<List<PhotoModel>> {
        return mPhotoItems
    }

    fun deletePhoto() {
        Thread(Runnable {
            mPhotoDAO.deletePhoto()
        }).start()
    }

    fun insertPhoto(photoModel: PhotoModel) {
        Thread(Runnable {
            mPhotoDAO.insertPhoto(photoModel)
        }).start()
    }
}
