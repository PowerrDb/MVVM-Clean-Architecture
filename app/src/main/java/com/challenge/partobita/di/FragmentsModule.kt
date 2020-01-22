package com.challenge.partobita.di


import com.challenge.presentation.ui.infoFragment.InfoFragment
import com.challenge.presentation.ui.listCharacterFragment.ListCharcaterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun charactersPageFragment(): ListCharcaterFragment

    @ContributesAndroidInjector
    abstract fun detailPageFragment(): InfoFragment
}