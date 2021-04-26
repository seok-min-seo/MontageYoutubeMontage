package com.sukddaksoftware.montageyoutube2t.Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Localized(
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("title")
    @Expose
    val title: String
)