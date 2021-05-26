package com.stardust.proyectokotlin.model

import android.os.Message
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface Services {
    //@FormUrlEncoded
    //@POST("/2020/contacts/login.php")
    //fun login(
    //    @Field("email") email: String?,
    //    @Field("password") password: String?
    //): Call<User>

    @Headers("Content-Type:application/json")
    @GET("users/login")
    fun login(
        @Query("username") username: String?,
        @Query("password") password: String?
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("users/independient_user")
    fun registerIndependientUser(
        @Body independientUser: IndependientUser
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("users/organization_user")
    fun registerOrganizationUser(
        @Body organizationUser: OrganizationUser
    ): retrofit2.Call<ResponseBody>



    @GET("/2020/contacts/list.php")
    fun requestContacts(): Call<ArrayList<Contact>>

    @POST("/2020/contacts/add_contact.php")
    fun addContact(@Body contact: Contact): Call<Message>

}