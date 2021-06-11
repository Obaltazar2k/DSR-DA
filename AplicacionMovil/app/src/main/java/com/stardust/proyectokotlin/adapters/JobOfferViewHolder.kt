package com.stardust.proyectokotlin.adapters

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.services.JobOfferConnectionManager

class JobOfferViewHolder(itemView: View, private val kindOf: Int?): RecyclerView.ViewHolder(itemView) {

    //private val imgPhoto: ImageView = itemView.findViewById(R.id.itemContactPhoto)
    private val txtJob: TextView = itemView.findViewById(R.id.itemJobOfferTitle)
    private val txtJobCategory: TextView = itemView.findViewById(R.id.itemJobOfferCategory)
    private val txtUsername: TextView = itemView.findViewById(R.id.itemJobOfferName)
    private val txtLocation: TextView = itemView.findViewById(R.id.itemJobOfferLocation)
    private val txtDescription: TextView = itemView.findViewById(R.id.itemJobOfferDescription)
    private val bttnAplicantes: Button = itemView.findViewById(R.id.itemJobOfferButton)

    fun bindData(jobOffer: JobOffer, onSelected: (JobOffer) -> Unit){
        txtJob.text = jobOffer.job
        txtJobCategory.text = jobOffer.jobCategory
        txtUsername.text = jobOffer.username
        txtLocation.text = jobOffer.location
        txtDescription.text = jobOffer.description

        if(Token.kindOf == "ORG" && kindOf == 1) {
            bttnAplicantes.visibility = View.GONE
        }
        if (kindOf!! == 2) {
            bttnAplicantes.text = "Consultar aplicantes"
        }

        bttnAplicantes.setOnClickListener {
            JobOfferConnectionManager.addAplication(jobOffer.username!!, jobOffer.jobOfferId, success =  {
                Toast.makeText(itemView.context, it, Toast.LENGTH_LONG).show()
            }, fail = {
                Toast.makeText(itemView.context, it, Toast.LENGTH_SHORT).show()
            })
        }

        /*
        if(!contact.photo.isNullOrEmpty())
            Picasso.get().load(contact.photo).into(imgPhoto)
        */
        /*
        itemView.setOnClickListener {
            onSelected(jobOffer)
        }
         */
    }
}