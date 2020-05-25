package com.example.demoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kc.unsplash.models.Urls

@Entity(tableName = "Photo")
data class PhotoModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "raw")
    var raw: String,

    @ColumnInfo(name = "full")
    var full: String,

    @ColumnInfo(name = "regular")
    var regular: String,

    @ColumnInfo(name = "small")
    var small: String,

    @ColumnInfo(name = "thumb")
    var thumb: String,

    @ColumnInfo(name = "createdDate")
    var createdDate: String
) {
    constructor(): this(null, "", "", "", "", "", "")

}
