package com.stardust.proyectokotlin.services

import com.stardust.proyectokotlin.Token.token
import com.stardust.proyectokotlin.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object JobOfferConnectionManager {
    fun loadJobOffers(page: Int?, success: (ArrayList<JobOffer>) -> Unit, fail: (String) -> Unit) {
        //val call = ConnectionManager.create().requestJobOffers(page)
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.requestJobOffers(page, "Bearer "+token).enqueue(object : Callback<ArrayList<JobOffer>> {
        //call.enqueue(object : Callback<List<JobOffer>> {
            override fun onResponse(call: Call<ArrayList<JobOffer>>, response: Response<ArrayList<JobOffer>>) {
                val array = response.body()
                if(array != null) {
                    success(array)
                } else {
                    fail("No hay datos")
                }
            }

            override fun onFailure(call: Call<ArrayList<JobOffer>>, t: Throwable) {
                fail("Error al cargar los datos")
            }
        })
    }

    fun uploadJobOffer(jobOffer: JobOffer, success: (String) -> Unit, fail: (String) -> Unit){
        val retIn = RetrofitInstance.getRetrofitInstance().create(Services::class.java)
        retIn.addJobOffer(jobOffer, "Bearer "+token).enqueue(object : Callback<ResponseBody> {
        //val call = ConnectionManager.create().addContact(contact)
        //call.enqueue(object : Callback<Message> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val message = response.body()
                if (message != null) {
                    success("JobOffer agregada")
                } else {
                    fail("Error al agregar contacto")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //error
                fail("Error al cargar los datos")
            }
        })
    }
}