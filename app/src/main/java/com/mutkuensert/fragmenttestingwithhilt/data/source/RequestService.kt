package com.mutkuensert.fragmenttestingwithhilt.data.source

import com.mutkuensert.fragmenttestingwithhilt.util.BASE_URL
import com.mutkuensert.fragmenttestingwithhilt.data.ImagesModel
import com.mutkuensert.fragmenttestingwithhilt.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestService {

    @GET(BASE_URL) //https://square.github.io/retrofit/2.x/retrofit/index.html?retrofit2/Retrofit.Builder.html 3rd baseUrl title
    suspend fun searchImageRequest(
        @Query("key") key: String = API_KEY,
        @Query("q") search: String,
        @Query("page") page: Int = 1
    ): Response<ImagesModel>
}