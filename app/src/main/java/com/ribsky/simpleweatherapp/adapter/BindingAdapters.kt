package com.ribsky.simpleweatherapp.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.ribsky.simpleweatherapp.R

class BindingAdapters {

    companion object {

        @JvmStatic
        @BindingAdapter("android:src")
        fun loadImage(view: ImageView, drawable: Int) {
            view.load(drawable)
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun loadImageUrl(view: ImageView, drawable: String) {
            view.load(drawable) {
                placeholder(R.drawable.ic_cloud)
                crossfade(true)
                error(R.drawable.ic_cloud)
            }
        }

    }
}