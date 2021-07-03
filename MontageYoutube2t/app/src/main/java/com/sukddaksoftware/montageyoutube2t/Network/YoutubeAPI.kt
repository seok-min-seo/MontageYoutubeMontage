package com.sukddaksoftware.montageyoutube2t.Network

import android.telecom.Call
import com.sukddaksoftware.montageyoutube2t.Models.ModelHome
import retrofit2.http.GET
import retrofit2.http.Url


class YoutubeAPI {

    companion object {
        private val BASE_URL : String = "https://www.googleapis.com/youtube/v3/"
        private val KEY : String = "key=AIzaSyDyb4PNXyFIpJ-1KzL6UZXL1ONB9qs8zCE"
        private val ChannelID : String = "&channelId=UC7zp0tyUpjR7IiAGb6BQlQQ"
        private val sch :String = "search?"
        private val maxResults :String = "&maxResults=10"
        private val order : String =  "&order=date"
        private val part : String = "&part=snippet"

        interface HomeVideo {
            @GET
            fun getYT(@Url url: String?): Call<ModelHome>
        }

    }



}