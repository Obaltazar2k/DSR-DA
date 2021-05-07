package com.stardust.proyectokotlin.model

import android.os.Message
import android.provider.ContactsContract
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectionManager {
    private fun create(): Services {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl("https://androiddevcourse.000webhostapp.com")

        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))

        val retrofit = retrofitBuilder.build()
        return retrofit.create(Services::class.java)
    }

    fun loadLogin( email: String, password: String, success: (String) -> Unit, fail: (String) -> Unit) {
        val call = create().login(email, password)
        call.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                //success
                val user = response.body()
                if (user != null) {
                    success(user.name)
                } else {
                    fail("Error de autenticación")
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                //error
                fail("Error al cargar los datos")
            }
        })
    }

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

    fun uploadContact(contact: Contact, success: (String) -> Unit, fail: (String) -> Unit){
        val call = create().addContact(contact)
        call.enqueue(object : Callback<Message> {
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                //success
                val message = response.body()
                if (message != null) {
                    success("Contacto agregado")
                } else {
                    fail("Error al agregar contacto")
                }
            }

            override fun onFailure(call: Call<Message>, t: Throwable) {
                //error
                fail("Error al cargar los datos")
            }
        })
    }
}
