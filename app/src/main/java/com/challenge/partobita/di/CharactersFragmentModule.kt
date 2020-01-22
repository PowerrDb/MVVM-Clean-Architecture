package com.challenge.partobita.di

import com.challenge.data.api.MApi
import com.challenge.data.datasource.ListCharacterApiDataSource
import com.challenge.data.datasource.ApiDataSourceImpl
import com.challenge.data.repositoryImpl.RepositoryImpl
import com.challenge.domain.repository.Repository
import com.challenge.domain.usecase.GetUseCase
import com.challenge.domain.usecase.UseCaseImpl

import dagger.Module
import dagger.Provides


@Module
class CharactersFragmentModule {


    @Provides
    //@PerFragment
    fun provideApiSource(api: MApi): ListCharacterApiDataSource =
        ApiDataSourceImpl(api)

    @Provides
    //@PerFragment
    fun provideRepository(
        apiSource: ListCharacterApiDataSource
    ): Repository {
        return RepositoryImpl(
            apiSource
        )
    }

    @Provides
    //@PerFragment
    fun provideGetCharactersUseCaseImpl(repository: Repository): GetUseCase =
        UseCaseImpl(repository)



}
