package com.stardust.proyectokotlin.services

import com.stardust.proyectokotlin.Encrypt
import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.model.IndependientUser
import com.stardust.proyectokotlin.model.OrganizationUser
import com.stardust.proyectokotlin.model.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneralUserConnectionManager {
    fun loadLogin( email: String, password: String, success: (String) -> Unit, fail: (String) -> Unit, extra: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.login(email, Encrypt.hash(password)).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("loadLogin onFailure")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) =
                if (response.code() == 200) {
                    var members = response.body()!!.string().split("/")
                    Token.kindOf = members[0]
                    success(members[1])
                }
                else if (response.code() == 401) {
                    fail("Credenciales incorrectas")
                }
                else if (response.code() == 423) {
                    extra("No autenticado")
                } else {
                    fail("Error de autenticación")
                }
        })
    }

    fun registerIndependientUser(independientUser: IndependientUser, success: (String) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.registerIndependientUser(independientUser).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("Error al cargar los datos")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    success("Usuario registrado con éxito")
                } else {
                    fail("Error de registro")
                }
            }
        })
    }

    fun registerOrganizationUser(organizationUser: OrganizationUser, success: (String) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.registerOrganizationUser(organizationUser).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("Error al cargar los datos")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    success("Usuario registrado con éxito")
                } else {
                    fail("Error de registro")
                }
            }
        })
    }

    fun generateNewToken( email: String, success: (String) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.generateNewToken(email, "estimado usuario").enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("generateNewToken onFailure")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) =
                if (response.code() == 200) {
                    success("Se ha enviado un nuevo código de verificación")
                } else {
                    fail("Error")
                }
        })
    }

    fun validateUser( email: String, token: String, success: (String) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        //val signInInfo = SignInBody(email, password)
        retIn.validateUser(email, token).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("validateUser onFailure")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) =
                if (response.code() == 200) {
                    success("La cuenta ha sido verificada con éxito")
                } else if (response.code() == 404) {
                    fail("Código incorrecto")
                } else {
                    fail("Error")
                }
        })
    }
}