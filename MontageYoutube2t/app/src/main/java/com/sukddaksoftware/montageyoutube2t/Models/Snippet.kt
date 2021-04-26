package com.sukddaksoftware.montageyoutube2t.Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("channelId")
    @Expose
    val channelId: String,
    @SerializedName("channelTitle")
    @Expose
    val channelTitle: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("localized")
    @Expose
    val localized: Localized,
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String,
    @SerializedName("thumbnails")
    @Expose
    val thumbnails: Thumbnails,
    @SerializedName("title")
    @Expose
    val title: String
)