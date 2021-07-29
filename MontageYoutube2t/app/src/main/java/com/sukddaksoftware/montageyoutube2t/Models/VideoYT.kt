package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoYT(
    @SerializedName("id")
    val id: VideoID,

    @SerializedName("snippet")
    val Snippet: SnippetYT
)