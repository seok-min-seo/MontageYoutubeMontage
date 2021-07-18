package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThumbnailYT(
    @SerializedName("medium")
    var medium: MediumThumb
) {



    fun ThumbnailYT(){

    }
    fun ThumbnailYT(medium : MediumThumb) {
        this.medium = medium
    }



    class  MediumThumb( @SerializedName("url")
                        @Expose
                        var url : String) {


        fun MediumThumb() {

        }

        fun MediumThumb(url : String) {
            this.url = url
        }


    }



}
