package com.sukddaksoftware.montageyoutube2t.TempModels


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("videoId")
    val videoId: String
)