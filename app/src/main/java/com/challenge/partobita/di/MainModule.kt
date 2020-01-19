package com.challenge.partobita.di

import com.challenge.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [FragmentsModule::class])
abstract class MainModule {


    @ContributesAndroidInjector
    abstract fun get(): MainActivity

}