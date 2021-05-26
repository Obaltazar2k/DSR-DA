package com.stardust.proyectokotlin.model

import android.os.Message
import com.google.gson.GsonBuilder
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
        retrofitBuilder.baseUrl("http://192.168.100.8:8080/ricardorzan/Employex/1.0.0/")

        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))

        val retrofit = retrofitBuilder.build()
        return retrofit.create(Services::class.java)
    }

    fun loadLogin( email: String, password: String, success: (String) -> Unit, fail: (String) -> Unit) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        //val signInInfo = SignInBody(email, password)
        retIn.login(email, password).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                fail("Error al cargar los datos")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    success("Autenticado")
                }
                if (response.code() == 401) {
                    fail("Credenciales incorrectas")
                } else {
                    fail("Error de autenticación")
                }
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

    fun RegisterIndependientUser(independientUser: IndependientUser, success: (String) -> Unit, fail: (String) -> Unit) {
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

    fun RegisterOrganizationUser(organizationUser: OrganizationUser, success: (String) -> Unit, fail: (String) -> Unit) {
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
