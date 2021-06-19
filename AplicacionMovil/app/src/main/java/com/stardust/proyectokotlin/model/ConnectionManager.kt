package com.stardust.proyectokotlin.model

import com.google.gson.GsonBuilder
import com.stardust.proyectokotlin.services.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ConnectionManager {
    private fun create(): Services {
        val retrofitBuilder = Retrofit.Builder()
        //retrofitBuilder.baseUrl("https://androiddevcourse.000webhostapp.com")
        retrofitBuilder.baseUrl("http://104.248.226.225:8080/ricardorzan/Employex/1.0.0/")

        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))

        val retrofit = retrofitBuilder.build()
        return retrofit.create(Services::class.java)
    }
/*
    fun loadContacts(success: (ArrayList<Contact>) -> Unit, fail: (String) -> Unit){
        val call = create().requestContacts()
        call.enqueue(object: Callback<ArrayList<Contact>> {
            override fun onResponse(
                call: Call<ArrayList<Contact>>,
                response: Response<ArrayList<Contact>>
            ) {
                //Success
                val array = response.body()
                if(array != null) {
                    success(array)
                } else {
                    fail("No hay datos")
                }
            }

            override fun onFailure(call: Call<ArrayList<Contact>>, t: Throwable) {
                //Error
                fail("Error al cargar los datos.")
            }

        })
    }
*/

    fun registerIndependientUser(independientUser: IndependientUser, success: (String) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.registerIndependientUser(independientUser).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("Error al cargar los datos")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
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
                if (response.code() == 200) {
                    success("Usuario registrado con éxito")
                } else {
                    fail("Error de registro")
                }
            }
        })
    }
}
