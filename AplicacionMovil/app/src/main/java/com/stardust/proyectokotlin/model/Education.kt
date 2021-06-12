package com.stardust.proyectokotlin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Education {
    @Expose
    @SerializedName("education_id")
    var education_id: String? = ""
    @SerializedName("title")
    lateinit var title: String
    @SerializedName("end_date")
    lateinit var end_date: String
    @SerializedName("start_date")
    lateinit var start_date: String
    @SerializedName("university")
    lateinit var university: String
    @SerializedName("discipline")
    lateinit var discipline: String
    @SerializedName("description")
    lateinit var description: String
    @SerializedName("average")
    var average: Int? = 0
    //@SerializedName("current_job")
    //var current_job: Boolean = false
}
