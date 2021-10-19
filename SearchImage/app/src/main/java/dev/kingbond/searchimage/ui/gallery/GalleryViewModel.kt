package dev.kingbond.searchimage.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dev.kingbond.searchimage.data.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor
    (private val unsplashRepository: UnsplashRepository) : ViewModel() {
}