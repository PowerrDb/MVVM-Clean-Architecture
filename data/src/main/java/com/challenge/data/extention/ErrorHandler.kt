package com.challenge.data.extention

import android.util.Log



fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Log.e("erorr", e.toString()) }