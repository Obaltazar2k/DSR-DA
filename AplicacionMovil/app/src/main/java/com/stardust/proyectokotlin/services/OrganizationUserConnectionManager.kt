package com.stardust.proyectokotlin.services

import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.model.OrganizationUser
import com.stardust.proyectokotlin.model.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object OrganizationUserConnectionManager {
    fun getProfileInfo(user_id: String?, success: (OrganizationUser) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.requestOrganizationInfo(user_id, "Bearer "+ Token.token).enqueue(object :
            Callback<OrganizationUser> {
            override fun onResponse(call: Call<OrganizationUser>, response: Response<OrganizationUser>) {
                if( response.body() != null) {
                    success(response.body()!!)
                } else {
                    fail("No hay usuario que mostrar")
                }
            }

            override fun onFailure(call: Call<OrganizationUser>, t: Throwable) {
                fail("Error al cargar los datos")
            }
        })
    }
}