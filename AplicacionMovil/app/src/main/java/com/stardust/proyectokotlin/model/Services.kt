package com.stardust.proyectokotlin.model

import android.os.Message
import retrofit2.Call
import retrofit2.http.*


interface Services {
    @FormUrlEncoded
    @POST("/2020/contacts/login.php")
    fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<User>

    @GET("/2020/contacts/list.php")
    fun requestContacts(): Call<ArrayList<Contact>>

    @POST("/2020/contacts/add_contact.php")
    fun addContact(@Body contact: Contact): Call<Message>
}