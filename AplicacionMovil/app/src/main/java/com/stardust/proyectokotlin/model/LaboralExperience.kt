package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LaboralExperience {
    @Expose
    @SerializedName("laboral_experience_id")
    var laboral_experience_id: String? = ""
    @SerializedName("job_title")
    lateinit var job_title: String
    @SerializedName("end_date")
    lateinit var end_date: String
    @SerializedName("start_date")
    lateinit var start_date: String
    @SerializedName("company_name")
    lateinit var company_name: String
    @SerializedName("sector")
    lateinit var sector: String
    @SerializedName("job_category")
    lateinit var job_category: String
    @SerializedName("location")
    lateinit var location: String
    //@SerializedName("current_job")
    //var current_job: Boolean? = false
}