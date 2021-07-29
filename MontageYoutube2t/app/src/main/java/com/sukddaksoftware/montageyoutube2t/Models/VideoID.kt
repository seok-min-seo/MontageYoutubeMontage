package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

class VideoID(
    @SerializedName("videoId")
    @Expose
    var videoId : String

)