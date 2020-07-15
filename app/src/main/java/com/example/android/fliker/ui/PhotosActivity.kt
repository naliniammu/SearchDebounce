package com.example.android.fliker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.fliker.R
import kotlinx.android.synthetic.main.activity_photos.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PhotosActivity : AppCompatActivity() {
    private val photosViewModel: PhotosViewModel by viewModels()
    private val photosAdapter = PhotosAdapter()
    // High level definition
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        searchdata("all")

        searchBox.addTextChangedListener { editable ->
            searchdata(editable.toString())
        }
        photosRecyclerView.adapter = photosAdapter
        photosRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

    }

    private fun searchdata(searchParam:String){
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(0)
            val imagesList = photosViewModel.fetchImages(searchParam)
            with(photosAdapter) {
                photos.clear()
                photos.addAll(imagesList)
                notifyDataSetChanged()
            }

        }

    }
}
