package com.example.android.fliker.ui


import androidx.lifecycle.ViewModel
import com.example.android.fliker.domain.Photo
import com.example.android.fliker.networking.WebClient

class PhotosViewModel : ViewModel() {
    suspend fun fetchImages(searchTerm: String): List<Photo> {
        if (searchTerm.isBlank()) {
            return emptyList()
        }

        val searchResponse = WebClient.client.fetchImages(searchTerm)
        return searchResponse.photos.photo.map { photo ->
            Photo(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }


    }


}
