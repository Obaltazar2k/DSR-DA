package com.stardust.proyectokotlin.services

import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.model.Education
import com.stardust.proyectokotlin.model.RetrofitInstance
import com.stardust.proyectokotlin.model.Section
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SectionConnectionManager {
    fun uploadSection(section: Section, success: (String) -> Unit, fail: (String) -> Unit){
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.addSection(section, Token.username, "Bearer ${Token.token}").enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val message = response.body()
                if (message != null) {
                    success("Sección agregada")
                } else {
                    fail("Error al agregar sección")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //error
                fail("Error al cargar los datos")
            }
        })
    }
}