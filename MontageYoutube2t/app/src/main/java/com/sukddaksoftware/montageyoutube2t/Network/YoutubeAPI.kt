package com.sukddaksoftware.montageyoutube2t.Network


import com.sukddaksoftware.montageyoutube2t.Models.ModelChannel
import com.sukddaksoftware.montageyoutube2t.Models.ModelHome
import com.sukddaksoftware.montageyoutube2t.Models.ModelPlaylist
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


class YoutubeAPI {

    //https://www.googleapis.com/youtube/v3/
    // channels?
    // key=AIzaSyDyb4PNXyFIpJ-1KzL6UZXL1ONB9qs8zCE
    // &id=UC7zp0tyUpjR7IiAGb6BQlQQ
    // &part=snippet,statistics,brandingSettings


    companion object {
        val BASE_URL : String = "https://www.googleapis.com/youtube/v3/"
        val ONLY_CHANNEL_ID = "UC7zp0tyUpjR7IiAGb6BQlQQ"
        val KEY : String = "key=AIzaSyDyb4PNXyFIpJ-1KzL6UZXL1ONB9qs8zCE"
        val ChannelID : String = "&channelId=${ONLY_CHANNEL_ID}"
        val sch :String = "search?"
        val maxResults :String = "&maxResults=10"
        val maxResults30 : String = "&maxResults=30"
        val order : String =  "&order=date"
        val part : String = "&part=snippet"

        val playlist : String ="playlists?"
        val part_playlist : String ="&part=snippet,contentDetails"

        val query : String = "&q="
        val type : String = "&type=video"

        val CH : String = "channels?"
        val IDC : String = "&id=${ONLY_CHANNEL_ID}"
        val CH_PART : String ="&part=snippet,statistics,brandingSettings"

        interface HomeVideo {
            @GET
            fun getYT(@Url url: String?): retrofit2.Call<ModelHome>

        }

        interface PlaylistVideo {
            @GET
            fun getYT(@Url url: String) : retrofit2.Call<ModelPlaylist>
        }

        interface  ChannelInfo {
            @GET
            fun getYT(@Url url: String) : retrofit2.Call<ModelChannel>
        }

        private var homeVideo : HomeVideo? = null
        private var playlistVideo : PlaylistVideo? = null
        private var chnnelInfo : ChannelInfo? = null

        fun getHomeVideo(): HomeVideo? {
            if (homeVideo == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


                homeVideo = retrofit.create(HomeVideo::class.java)

            }
            return homeVideo
        }
        fun getPlaylistVideo(): PlaylistVideo? {
            if (playlistVideo == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


                playlistVideo = retrofit.create(PlaylistVideo::class.java)

            }
            return playlistVideo
        }
        fun getChannelInfo(): ChannelInfo? {
            if (chnnelInfo == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


                chnnelInfo = retrofit.create(ChannelInfo::class.java)

            }
            return chnnelInfo
        }
    }



}