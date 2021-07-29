package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelPlaylist(
    @SerializedName("nextPageToken")
    @Expose
    val nextPageToken: String,

    @SerializedName("items")
    @Expose
    val items: List<PlaylistItems>

) {


}