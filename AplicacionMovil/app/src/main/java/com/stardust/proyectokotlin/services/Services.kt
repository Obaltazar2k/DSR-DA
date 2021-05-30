package com.stardust.proyectokotlin.services

import com.stardust.proyectokotlin.model.IndependientUser
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.model.OrganizationUser
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

    //@Headers("Content-Type:application/json")
    @GET("users/login")
    fun login(
        @Query("username") username: String?,
        @Query("password") password: String?
    ): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("users/independient_user")
    fun registerIndependientUser(
        @Body independientUser: IndependientUser
    ): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("users/organization_user")
    fun registerOrganizationUser(
        @Body organizationUser: OrganizationUser
    ): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @GET("job_offers")
    fun requestJobOffers(
        @Query("page") page: Int?,
        @Header("Authorization") authHeader: String?
    ): Call<ArrayList<JobOffer>>

    @POST("job_offers")
    fun addJobOffer(
        @Body jobOffer: JobOffer,
        @Header("Authorization") authHeader: String?
    ): Call<ResponseBody>

/*
    @GET("/2020/contacts/list.php")
    fun requestContacts(): Call<ArrayList<Contact>>
*/
/*
    @POST("/2020/contacts/add_contact.php")
    fun addContact(@Body contact: Contact): Call<Message>
*/
}