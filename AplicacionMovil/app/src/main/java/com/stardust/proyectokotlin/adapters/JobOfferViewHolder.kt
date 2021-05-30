package com.stardust.proyectokotlin.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.JobOffer

class JobOfferViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //private val imgPhoto: ImageView = itemView.findViewById(R.id.itemContactPhoto)
    private val txtJob: TextView = itemView.findViewById(R.id.itemJobOfferJob)
    private val txtJobCategory: TextView = itemView.findViewById(R.id.itemJobOfferJobCategory)
    private val txtLocation: TextView = itemView.findViewById(R.id.itemJobOfferLocation)
    private val txtDescription: TextView = itemView.findViewById(R.id.itemJobOfferDescription)

    fun bindData(jobOffer: JobOffer, onSelected: (JobOffer) -> Unit){
        txtJob.text = jobOffer.job
        txtJobCategory.text = jobOffer.jobCategory
        txtLocation.text = jobOffer.location
        txtDescription.text = jobOffer.description
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