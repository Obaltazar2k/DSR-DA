package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @Expose
    @SerializedName("name")
    var name: String = ""
}