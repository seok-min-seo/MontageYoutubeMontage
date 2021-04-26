package com.sukddaksoftware.montageyoutube2t.Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default")
    @Expose
    val default: Default,
    @SerializedName("high")
    @Expose
    val high: High,
    @SerializedName("maxres")
    @Expose
    val maxres: Maxres,
    @SerializedName("medium")
    @Expose
    val medium: Medium,
    @SerializedName("standard")
    @Expose
    val standard: Standard
)