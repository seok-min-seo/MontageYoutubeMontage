package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThumbnailYT(
    @SerializedName("medium")
    var medium: MediumThumb
) {


    class MediumThumb(
        @SerializedName("url")
        @Expose
        var url: String
    )


}
