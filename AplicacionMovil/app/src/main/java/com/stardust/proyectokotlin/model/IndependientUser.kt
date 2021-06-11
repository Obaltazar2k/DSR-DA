package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.ArrayTypeAdapter

class IndependientUser {
    @SerializedName("surnames")
    lateinit var surnames: String
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("ocupation")
    lateinit var ocupation: String
    @SerializedName("persoanl_description")
    lateinit var persoanlDescription: String
    @SerializedName("user")
    lateinit var user: User
    @SerializedName("laboral_experience")
    var laboral_experience: ArrayList<LaboralExperience>? = null
    @SerializedName("education")
    var education: ArrayList<Education>? = null
    @SerializedName("certification")
    var certification: ArrayList<Certification>? = null
    @SerializedName("section")
    var section: ArrayList<Section>? = null
}