package com.sukddaksoftware.montageyoutube2t.Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Youtube(
    @SerializedName("etag")
    @Expose
    val etag: String,
    @SerializedName("items")
    @Expose
    val items: List<Item>,
    @SerializedName("kind")
    @Expose
    val kind: String,
    @SerializedName("pageInfo")
    @Expose
    val pageInfo: PageInfo
)