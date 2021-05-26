package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class IndependientUser {
    @Expose
    @SerializedName("surnames")
    lateinit var surnames: String
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("ocupation")
    lateinit var ocupation: String
    @SerializedName("persoanlDescription")
    lateinit var persoanlDescription: String
    @SerializedName("user")
    lateinit var user: User
    @SerializedName("education")
    lateinit var education: List<Education>
    @SerializedName("laboralExperience")
    lateinit var laboralExperience: List<LaboralExperience>
    @SerializedName("certification")
    lateinit var certification: List<Certification>
    @SerializedName("section")
    lateinit var section: List<Section>
}