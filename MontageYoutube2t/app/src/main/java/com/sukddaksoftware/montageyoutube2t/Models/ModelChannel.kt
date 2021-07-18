package com.sukddaksoftware.montageyoutube2t.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelChannel(
    @SerializedName("items")
    @Expose
    var items : List<ChannelList>


)