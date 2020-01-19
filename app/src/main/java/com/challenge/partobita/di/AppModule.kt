package com.challenge.partobita.di

import android.content.Context
import android.content.res.Resources
import com.challenge.partobita.App
import com.challenge.cafebazaar.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class AppModule {
    @ApplicationContext
    @Binds
    abstract fun provideApplicationContext(myApplication: App): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideAppResources(context: Context): Resources {
            return context.resources
        }
    }
}
