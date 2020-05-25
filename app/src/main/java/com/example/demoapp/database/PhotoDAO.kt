package com.example.demoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.demoapp.model.PhotoModel

@Dao
interface PhotoDAO {
    @Query("SELECT * from Photo ORDER BY createdDate ASC")
    fun getPhotoList(): LiveData<List<PhotoModel>>

    @Insert
    fun insertPhoto(photoModel: PhotoModel)

    @Query("DELETE FROM Photo")
    fun deletePhoto()
}
