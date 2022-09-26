package com.mutkuensert.fragmenttestingwithhilt.di

import com.mutkuensert.fragmenttestingwithhilt.data.FakeImagesRepository
import com.mutkuensert.fragmenttestingwithhilt.data.source.ImagesRepository
import com.mutkuensert.fragmenttestingwithhilt.data.source.RequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object FakeImagesRepositoryModule {

    @Singleton
    @Provides
    fun providesFakeImagesRepository(requestService: RequestService): ImagesRepository{
        return FakeImagesRepository(requestService)
    }
}