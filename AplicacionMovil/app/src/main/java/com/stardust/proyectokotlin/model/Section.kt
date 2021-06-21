package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Section {
    @Expose
    @SerializedName("section_id")
    lateinit var section_id: String
    @SerializedName("title")
    lateinit var title: String
    @SerializedName("description")
    lateinit var description: String
    //@SerializedName("media")
    //lateinit var media: ArrayList<Media>
}
