package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Certification {
    @Expose
    @SerializedName("credential_id")
    var credential_id: String? = ""
    @SerializedName("title")
    lateinit var title: String
    @SerializedName("expiration_date")
    lateinit var expiration_date: String
    @SerializedName("expedition_date")
    lateinit var expedition_date: String
    @SerializedName("discipline")
    lateinit var discipline: String
    @SerializedName("issuing_company")
    lateinit var issuing_company: String
    @SerializedName("credential_url")
    lateinit var credential_url: String
}
