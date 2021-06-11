package com.stardust.proyectokotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class JobOffer : Serializable {
    @Expose
    @SerializedName("job")
    var job: String? = "job"
    @Expose
    @SerializedName("description")
    var description: String? = "description"
    @Expose
    @SerializedName("location")
    var location: String? = "location"
    @Expose
    @SerializedName("jobCategory")
    var jobCategory: String? = "jobCategory"
    @Expose
    @SerializedName("jobOfferId")
    var jobOfferId: Int? = 0
    @Expose
    @SerializedName("username")
    var username: String? = "username"
    //@Expose
    //@SerializedName("media")
    //var media: List<Media>? = null
}