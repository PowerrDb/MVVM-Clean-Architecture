package com.challenge.partobita.di


import com.challenge.presentation.ui.detainFragment.DetailPageFragment
import com.challenge.presentation.ui.listCharacterFragment.ListCharacterPageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun charactersPageFragment(): ListCharacterPageFragment

    @ContributesAndroidInjector
    abstract fun detailPageFragment(): DetailPageFragment
}