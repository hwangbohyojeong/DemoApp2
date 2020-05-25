package com.example.demoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapp.model.PhotoModel

@Database(entities = [PhotoModel::class], version = 1)

abstract class PhotoDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDAO
    companion object {
        @Volatile private var INSTANCE: PhotoDatabase? = null
        fun getInstance(context: Context): PhotoDatabase = INSTANCE ?:
            synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PhotoDatabase::class.java, "Photo.db").build()
    }
}
