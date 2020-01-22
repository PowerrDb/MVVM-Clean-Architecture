package com.challenge.presentation.common


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class OperationLiveData<T>(private val operation: OperationLiveData<T>.() -> Unit) :
    MutableLiveData<T>() {
    private var operated = AtomicBoolean(false)
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        operate()
    }

    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(observer)
        operate()
    }

    private fun operate() {
        if (value != null && operated.get()) {
            return
        }
        operation()
        operated.set(true)
    }
}