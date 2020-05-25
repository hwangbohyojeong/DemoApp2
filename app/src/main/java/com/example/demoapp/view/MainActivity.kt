package com.example.demoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoapp.database.PhotoDAO
import com.example.demoapp.model.PhotoModel
import com.example.demoapp.view.PhotoAdapter
import com.example.demoapp.viewmodel.PhotoViewModel
import com.kc.unsplash.Unsplash
import com.kc.unsplash.Unsplash.OnPhotosLoadedListener
import com.kc.unsplash.api.Order
import com.kc.unsplash.models.Photo
import com.kc.unsplash.models.Urls
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val CLIENT_ID = "Ar8bmrXzjlmGQXuYnNHI3y8WhOSV97ZxtfHtbf2Edzw"
    private var unsplash: Unsplash? = null
    private lateinit var mPhotoViewModel: PhotoViewModel
    private lateinit var mPhotoAdater: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        unsplash = Unsplash(CLIENT_ID)

        initViewModel()

        main_recycler_view.layoutManager = GridLayoutManager(this, 2)
        mPhotoAdater = PhotoAdapter(this)
        main_recycler_view.adapter = mPhotoAdater
        unsplash!!.getPhotos(1, 30, Order.LATEST, object : OnPhotosLoadedListener {
            override fun onComplete(photos: List<Photo>) {

                mPhotoViewModel.deletePhoto()
                for (i in 0..29) {
                    Log.d("Photos", "" + photos.get(i).urls)

                    val photoModel = PhotoModel(null, photos.get(i).urls.raw, photos.get(i).urls.full, photos.get(i).urls.regular,
                        photos.get(i).urls.small, photos.get(i).urls.thumb, photos.get(i).createdAt)

                    mPhotoViewModel.insertPhoto(photoModel)

                }
            }

            override fun onError(error: String) {}
        })
    }

    private fun initViewModel() {
        mPhotoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(PhotoViewModel::class.java)
        mPhotoViewModel.getPhotoList().observe(this, Observer {
            mPhotoAdater.setListOfPhotos(it)
        })

    }

}