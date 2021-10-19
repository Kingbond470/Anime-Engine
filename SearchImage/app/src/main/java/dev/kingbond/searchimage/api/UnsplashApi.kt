package dev.kingbond.searchimage.api

import dev.kingbond.searchimage.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    // creating static, so that it can be accessed everywhere
    companion object{
        const val BASE_URL="https://api.unsplash.com/"
        const val CLIENT_ID=BuildConfig.UNSPLASH_ACCESS_KEY
    }

    @Headers("Accept-Version:v1","Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") queery:String,
        @Query("page")page:Int,
        @Query("per_page") perPage:Int
    ):UnsplashResponse
    // handling thread using suspend - pause and resume so that it won't block main thread( continue it work and after getting res from here, it will update)
}