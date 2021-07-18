package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelPlaylist(
    @SerializedName("items")
    @Expose
    val items: List<PlaylistItems>

) {


}