package com.stardust.proyectokotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.JobOffer

class JobOfferAdapter(val jobOffers: ArrayList<JobOffer>, val kindOf: Int?): RecyclerView.Adapter<JobOfferViewHolder>() {

    private var selectedBlock: (JobOffer) -> Unit = {}

    fun onJobOfferSelected(selected: (JobOffer) -> Unit){
        selectedBlock = selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOfferViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_job_offer, parent, false)

        return JobOfferViewHolder(view, kindOf)
    }

    override fun onBindViewHolder(holder: JobOfferViewHolder, position: Int) {
        val jobOffer = jobOffers[position]

        holder.bindData(jobOffer, selectedBlock)
    }

    override fun getItemCount(): Int {
        return jobOffers.size
    }
}