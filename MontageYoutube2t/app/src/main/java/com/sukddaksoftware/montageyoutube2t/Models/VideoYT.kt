package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoYT(
    @SerializedName("id")
    val id: VideoYT,

    @SerializedName("snippet")
    val Snippet: SnippetYT
)