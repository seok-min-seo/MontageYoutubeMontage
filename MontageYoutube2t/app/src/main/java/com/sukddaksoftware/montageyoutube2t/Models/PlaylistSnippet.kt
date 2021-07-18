package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.SerializedName

class PlaylistSnippet(
    @SerializedName("title")
    val title: String,

    @SerializedName("thumbnails")
    val thumnails : ThumbnailYT,

    )