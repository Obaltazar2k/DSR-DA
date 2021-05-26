package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrganizationUser {
    @Expose
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("about")
    lateinit var about: String
    @SerializedName("zip_code")
    var zipCode: Int = 0
    @SerializedName("contact_name")
    lateinit var contactName: String
    @SerializedName("contact_phone")
    lateinit var contactPhone: String
    @SerializedName("contact_email")
    lateinit var contactEmail: String
    @SerializedName("web_site")
    lateinit var webSite: String
    @SerializedName("work_sector")
    lateinit var workSector: String
    @SerializedName("user")
    lateinit var user: User
}
