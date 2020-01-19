package com.challenge.partobita

import android.content.Context
import androidx.multidex.MultiDex
import com.challenge.partobita.di.DaggerAppComponent
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


open class App : DaggerApplication() {


    private lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun onCreate() {
        super.onCreate()
        Hawk.init(applicationContext).build()
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)


    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}