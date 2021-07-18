package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChannelList(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("snippet")
    @Expose
    val snippet: SnippetYT,

    @SerializedName("statistics")
    @Expose
    val statistic: ChannelStatistic,

    @SerializedName("brandingSettings")
    @Expose
    val channelBranding : ChannelBranding
) {

}
