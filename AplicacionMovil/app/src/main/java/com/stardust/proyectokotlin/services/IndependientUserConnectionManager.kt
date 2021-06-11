package com.stardust.proyectokotlin.services

import android.util.Log
import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.model.IndependientUser
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.model.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object IndependientUserConnectionManager {
    fun getProfileInfo(user_id: String?, success: (IndependientUser) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.requestIndependientInfo(user_id, "Bearer "+ Token.token).enqueue(object :
            Callback<IndependientUser> {
            override fun onResponse(call: Call<IndependientUser>, response: Response<IndependientUser>) {
                if( response.body() != null) {
                    success(response.body()!!)
                } else {
                    fail("No hay usuario que mostrar")
                }
            }

            override fun onFailure(call: Call<IndependientUser>, t: Throwable) {
                fail("Error al cargar los datos")
            }
        })
    }
}
