package com.mutkuensert.fragmenttestingwithhilt.data.source

import com.mutkuensert.fragmenttestingwithhilt.data.ImagesModel
import com.mutkuensert.fragmenttestingwithhilt.util.Resource

open class ImagesRepository(private val requestService: RequestService){

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