package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @Expose
    @SerializedName("userID")
    var userId: String? = ""
    @SerializedName("email")
    lateinit var email: String
    @SerializedName("password")
    lateinit var password: String
    @SerializedName("city")
    lateinit var city: String
    @SerializedName("country")
    lateinit var country: String
    @SerializedName("userStatus")
    var userStatus: String = "active"
    @SerializedName("profilePhoto")
    lateinit var profilePhoto: Media
}