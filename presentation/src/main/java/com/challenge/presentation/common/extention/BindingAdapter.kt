package com.challenge.presentation.common.extention

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .asBitmap()
            .circleCrop()
            .load("https://play.hen-dev.ir/$imageUrl")
            .into(view)
    }
}