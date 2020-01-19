package com.challenge.partobita.di

import com.challenge.partobita.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        AndroidSupportInjectionModule::class,
        MainModule::class,
        ViewModelModule::class,
        CharactersFragmentModule::class

    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}


