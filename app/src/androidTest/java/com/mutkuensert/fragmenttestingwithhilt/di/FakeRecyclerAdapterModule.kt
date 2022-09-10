package com.mutkuensert.fragmenttestingwithhilt.di

import com.mutkuensert.fragmenttestingwithhilt.ui.FakeRecyclerAdapter
import com.mutkuensert.fragmenttestingwithhilt.ui.MyRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RecyclerAdapterModule::class]
)
object FakeRecyclerAdapterModule {

    @Singleton
    @Provides
    fun providesFakeRecyclerAdapter(): MyRecyclerAdapter{
        return FakeRecyclerAdapter()
    }
}