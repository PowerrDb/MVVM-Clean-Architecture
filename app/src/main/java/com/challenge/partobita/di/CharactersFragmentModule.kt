package com.challenge.partobita.di

import com.challenge.data.api.ListCharacterApi
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSource
import com.challenge.data.datasource.listCharacterFragment.ListCharacterApiDataSourceImpl
import com.challenge.data.repositoryImpl.listCharacterFragment.ListCharacterRepositoryImpl
import com.challenge.domain.repository.listCharacterFragment.ListCharacterRepository
import com.challenge.domain.usecase.listCharacterFragment.GetListCharacterUseCase
import com.challenge.domain.usecase.listCharacterFragment.ListCharacterUseCaseImpl

import dagger.Module
import dagger.Provides


@Module
class CharactersFragmentModule {


    @Provides
    //@PerFragment
    fun provideApiSource(api: ListCharacterApi): ListCharacterApiDataSource =
        ListCharacterApiDataSourceImpl(api)

    @Provides
    //@PerFragment
    fun provideRepository(
        apiSource: ListCharacterApiDataSource
    ): ListCharacterRepository {
        return ListCharacterRepositoryImpl(
            apiSource
        )
    }

    @Provides
    //@PerFragment
    fun provideGetCharactersUseCaseImpl(repository: ListCharacterRepository): GetListCharacterUseCase =
        ListCharacterUseCaseImpl(repository)



}
