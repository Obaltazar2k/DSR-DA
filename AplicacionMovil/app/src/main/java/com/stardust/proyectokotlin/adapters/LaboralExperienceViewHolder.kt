package com.stardust.proyectokotlin.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.LaboralExperience

class LaboralExperienceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //private val imgPhoto: ImageView = itemView.findViewById(R.id.itemContactPhoto)
    private val txtDate: TextView = itemView.findViewById(R.id.itemLaboralExperienceDate)
    private val txtJobCategory: TextView = itemView.findViewById(R.id.itemLaboralExperienceCategory)
    private val txtName: TextView = itemView.findViewById(R.id.itemLaboralExperienceName)
    private val txtLocation: TextView = itemView.findViewById(R.id.itemLaboralExperienceLocation)
    private val txtOrganization: TextView = itemView.findViewById(R.id.itemLaboralExperienceOrganization)
    private val txtSector: TextView = itemView.findViewById(R.id.itemLaboralExperienceSector)

    fun bindData(laboralExperience: LaboralExperience, onSelected: (LaboralExperience) -> Unit){
        txtDate.text = laboralExperience.start_date + " - " + laboralExperience.end_date
        txtJobCategory.text = laboralExperience.job_category
        txtName.text = laboralExperience.job_title
        txtLocation.text = laboralExperience.location
        txtOrganization.text = laboralExperience.company_name
        txtSector.text = laboralExperience.sector


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