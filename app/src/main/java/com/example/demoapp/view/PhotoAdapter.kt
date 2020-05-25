package com.example.demoapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.model.PhotoModel
import com.kc.unsplash.models.Photo
import com.kc.unsplash.models.Urls
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*
import java.util.*

class PhotoAdapter constructor(context: Context) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    var mListOfPhotos = mutableListOf<PhotoModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            mLayoutInflater.inflate(
                R.layout.item_photo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        // item
        val photo = mListOfPhotos?.get(position)
        holder.bind(photo)

        // loading the photo
        Picasso.get().load(photo.small)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mListOfPhotos!!.size
    }

    fun setListOfPhotos(listOfPhotos: List<PhotoModel>) {
        if (listOfPhotos != null) {
            mListOfPhotos.clear()
            mListOfPhotos.addAll(listOfPhotos)
            notifyDataSetChanged()
        }
    }

    /**
     * UnsplashPhoto view holder.
     */
    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(photoModel: PhotoModel) {

        }

        val imageView: ImageView = view.item_photo_iv
    }
}