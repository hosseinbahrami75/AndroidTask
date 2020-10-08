package com.keyvan.android.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.keyvan.android.utils.Constants
import com.squareup.picasso.Picasso

/**
 * Created by HosseinBahrami
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(v: ImageView?, url: String?) {
        Picasso.get().load("${Constants.IMAGE_BASE_URL}$url").into(v)
    }
}