package com.stardust.proyectokotlin.services

import com.stardust.proyectokotlin.model.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneralUserConnectionManager {
    fun loadLogin( email: String, password: String, success: (String) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        //val signInInfo = SignInBody(email, password)
        retIn.login(email, password).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("loadLogin onFailure")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    success(response.body().toString())
                }
                else if (response.code() == 401) {
                    fail("Credenciales incorrectas")
                } else {
                    fail("Error de autenticación")
                }
            }
        })
    }
}