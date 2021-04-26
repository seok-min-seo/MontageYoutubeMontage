package com.sukddaksoftware.montageyoutube2t.Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Standard(
    @SerializedName("height")
    @Expose
    val height: Int,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("width")
    @Expose
    val width: Int
)