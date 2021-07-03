package com.sukddaksoftware.montageyoutube2t.Repository


import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("youtube/v3/search")
    suspend fun getYoutubeSearch(
        @Query("key") key : String = "AIzaSyDyb4PNXyFIpJ-1KzL6UZXL1ONB9qs8zCE",
        @Query("channelId") channelId : String = "UC7zp0tyUpjR7IiAGb6BQlQQ",
        @Query("maxResults") maxResults : Int,
        @Query("order") order : String,
        @Query("part") part : String
    ) : Youtube



}

