package com.example.android.fliker.networking

import com.example.android.fliker.data.PhotosSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&per_page=2&page=1&api_key=062a6c0c49e4de1d78497d13a7dbb360")
    suspend fun fetchImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse
}
