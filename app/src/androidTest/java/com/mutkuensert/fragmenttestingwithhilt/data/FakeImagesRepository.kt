package com.mutkuensert.fragmenttestingwithhilt.data

import androidx.test.core.app.ApplicationProvider
import com.mutkuensert.fragmenttestingwithhilt.data.source.ImagesRepository
import com.mutkuensert.fragmenttestingwithhilt.util.Resource

class FakeImagesRepository: ImagesRepository(ApplicationProvider.getApplicationContext()) {
    private var lastId = 0

    override suspend fun requestImages(search: String, page: Int): Resource<ImagesModel> {

        val hits = mutableListOf<ImageHitsModel>()
        for(i in lastId..lastId+2){
            hits.add(
                ImageHitsModel(
                    id = i,
                    pageUrl = null,
                    type = null,
                    tags = null,
                    previewURL = null,
                    previewWidth = null,
                    previewHeight = null,
                    webFormatURL = null,
                    webFormatWidth = null,
                    webFormatHeight = null,
                    largeImageURL = i.toString(), //DiffUtil checks the contents.
                    fullHDURL = null,
                    imageURL = null,
                    imageWidth = null,
                    imageHeight = null,
                    imageSize = null,
                    views = null,
                    downloads = null,
                    likes = null,
                    comments = null,
                    userId = null,
                    user = null,
                    userImageURL = null,
                ))
        }
        lastId += 3
        val imagesModel = ImagesModel(null, null, hits)

        return Resource.success(imagesModel)
    }
}