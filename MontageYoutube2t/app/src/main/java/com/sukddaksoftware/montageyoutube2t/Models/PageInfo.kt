package com.sukddaksoftware.montageyoutube2t.Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("resultsPerPage")
    @Expose
    val resultsPerPage: Int,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int
)