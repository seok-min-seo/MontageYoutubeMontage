package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoID(
    @SerializedName("videoId")
    @Expose
    val videoId : String

)