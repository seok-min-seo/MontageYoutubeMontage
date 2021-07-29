package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SnippetYT(
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("thumbnails")
    @Expose
    val thumbnails: ThumbnailYT

)