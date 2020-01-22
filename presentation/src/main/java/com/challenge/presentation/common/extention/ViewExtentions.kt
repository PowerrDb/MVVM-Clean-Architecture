package com.challenge.presentation.common.extention

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject




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

val subject = PublishSubject.create<String>()
fun EditText.toObservable() :Observable<String> {
    var init = false
   addTextChangedListener(object : TextWatcher {

       override fun afterTextChanged(p0: Editable?) {

           Log.e("----","afterTextChanged ${p0.toString()} ")
       }

       override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           Log.e("----","beforeTextChanged ${p0.toString()}")

       }

       override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           Log.e("----","onTextChanged ${p0.toString()}")

          if (init)
           subject.onNext(p0.toString())
           init = true
       }

   })
    return subject
}