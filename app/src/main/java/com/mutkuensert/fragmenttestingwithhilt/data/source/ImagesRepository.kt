package com.mutkuensert.fragmenttestingwithhilt.data.source

import android.content.Context
import com.mutkuensert.fragmenttestingwithhilt.data.ImagesModel
import com.mutkuensert.fragmenttestingwithhilt.util.Resource
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

open class ImagesRepository @Inject constructor(private val requestService: RequestService){

    open suspend fun requestImages(search: String, page: Int): Resource<ImagesModel>{
        val response = requestService.searchImageRequest(
            search = search,
            page = page
        )
        if(response.isSuccessful){
            return Resource.success(response.body())
        }

        //ELSE
        return Resource.error("Error.", null)
    }

}