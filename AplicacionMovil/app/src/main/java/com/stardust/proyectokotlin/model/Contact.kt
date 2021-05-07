package com.stardust.proyectokotlin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Contact() : Serializable {
    @Expose
    @SerializedName("idContact")
    var idContact: Int = 0
    @Expose
    @SerializedName("name")
    var name: String = ""
    @Expose
    @SerializedName("work")
    var work: String = ""
    @Expose
    @SerializedName("phone")
    var phone: String = ""
    @Expose
    @SerializedName("email")
    var email: String = ""
    @Expose
    @SerializedName("photo")
    var photo: String = ""

    constructor(parcel: Parcel) : this() {
        idContact = parcel.readInt()
        name = parcel.readString().toString()
        work = parcel.readString().toString()
        phone = parcel.readString().toString()
        email = parcel.readString().toString()
        photo = parcel.readString().toString()
    }
}
