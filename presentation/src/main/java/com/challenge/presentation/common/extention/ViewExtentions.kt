package com.challenge.presentation.common.extention

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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
fun View.visible() {
    if (visibility == View.GONE || visibility == View.INVISIBLE)
        visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility == View.GONE || visibility == View.VISIBLE)
        visibility = View.INVISIBLE
}

fun View.gone() {
    if (visibility == View.VISIBLE || visibility == View.INVISIBLE)
        visibility = View.GONE
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}