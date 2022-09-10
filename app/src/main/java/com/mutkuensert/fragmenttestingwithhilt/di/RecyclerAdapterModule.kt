package com.mutkuensert.fragmenttestingwithhilt.di

import com.mutkuensert.fragmenttestingwithhilt.ui.MyRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecyclerAdapterModule {

    @Singleton
    @Provides
    fun providesMyRecyclerAdapter(): MyRecyclerAdapter {
        return MyRecyclerAdapter()
    }
}