package com.stardust.proyectokotlin.services

import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.model.LaboralExperience
import com.stardust.proyectokotlin.model.OrganizationUser
import com.stardust.proyectokotlin.model.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LaboralExperienceConnectionManager {
    fun uploadLaboralExperience(laboralExperience: LaboralExperience, success: (String) -> Unit, fail: (String) -> Unit){
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.addLaboralExperience(laboralExperience, Token.username, "Bearer ${Token.token}").enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val message = response.body()
                if (message != null) {
                    success("Experiencia laboral agregada")
                } else {
                    fail("Error al agregar experiencia laboral")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //error
                fail("Error al cargar los datos")
            }
        })
    }
}