package com.sukddaksoftware.montageyoutube2t.Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("etag")
    @Expose
    val etag: String,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("kind")
    @Expose
    val kind: String,
    @SerializedName("snippet")
    @Expose
    val snippet: Snippet
)