package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Media : Serializable{
    @Expose
    @SerializedName("mediaID")
    var mediaId: Int? = 0
    @Expose
    @SerializedName("file")
    var file: ByteArray? = null
}