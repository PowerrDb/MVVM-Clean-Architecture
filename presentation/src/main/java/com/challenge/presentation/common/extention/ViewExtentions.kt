package com.challenge.presentation.common.extention

import android.view.View
import android.view.ViewGroup


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