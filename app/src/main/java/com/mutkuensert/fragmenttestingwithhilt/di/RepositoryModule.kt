package com.mutkuensert.fragmenttestingwithhilt.di

import com.mutkuensert.fragmenttestingwithhilt.data.source.ImagesRepository
import com.mutkuensert.fragmenttestingwithhilt.data.source.RequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesImagesRepository(requestService: RequestService): ImagesRepository{
        return ImagesRepository(requestService)
    }
}