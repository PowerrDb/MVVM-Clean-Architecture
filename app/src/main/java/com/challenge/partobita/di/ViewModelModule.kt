package com.challenge.partobita.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.challenge.partobita.di.qualifier.ViewModelKey
import com.challenge.presentation.ui.SharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module()
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    fun bindRecieptViewModel(viewModel: SharedViewModel): ViewModel
}