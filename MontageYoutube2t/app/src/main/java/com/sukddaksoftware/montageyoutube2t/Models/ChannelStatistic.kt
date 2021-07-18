package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChannelStatistic(
    @SerializedName("viewCount")
    @Expose
    val viewCount: String,

    @SerializedName("subscriberCount")
    @Expose
    val subscriberCount : String
    ,
    @SerializedName("videoCount")
    @Expose
    val videoCount  : String
    )




