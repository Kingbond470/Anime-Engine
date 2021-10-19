package dev.kingbond.searchimage.api

import dev.kingbond.searchimage.data.UnsplashPhoto

data class UnsplashResponse(
    val results:List<UnsplashPhoto>
)