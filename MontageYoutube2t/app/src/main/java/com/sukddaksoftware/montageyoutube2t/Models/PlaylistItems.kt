package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.SerializedName

class PlaylistItems(
    @SerializedName("id")
    val id: String,
    @SerializedName("snippet")
     val snippet: PlaylistSnippet,
    @SerializedName("contentDetails")
    val contentDetails: PlaylistDetail,
)