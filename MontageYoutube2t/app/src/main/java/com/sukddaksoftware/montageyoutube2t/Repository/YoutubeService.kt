package com.sukddaksoftware.montageyoutube2t.Repository


import retrofit2.Retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    companion object {



        const val BASE_URL = "https://www.googleapis.com/youtube/v3"

        private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        object Youtubeapi {
            val youtubeService : YoutubeService by lazy {
                retrofit.create(YoutubeService::class.java)
            }
        }
   }

    private fun getYoutubeProperties(){
        Youtubeapi.youtubeService
    }


}