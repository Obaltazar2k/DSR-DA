package com.stardust.proyectokotlin.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Certification
import com.stardust.proyectokotlin.model.Section

class SectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //private val imgPhoto: ImageView = itemView.findViewById(R.id.itemContactPhoto)
    //private val txtDate: TextView = itemView.findViewById(R.id.itemEducationDate)
    private val txtDescription: TextView = itemView.findViewById(R.id.itemSectionDescription)
    private val txtName: TextView = itemView.findViewById(R.id.itemSectionTitle)
    //private val txtAvarage: TextView = itemView.findViewById(R.id.itemEducationAvarage)
    //private val txtUniversity: TextView = itemView.findViewById(R.id.itemEducationUniversity)
    //private val txtDiscipline: TextView = itemView.findViewById(R.id.itemEducationDiscipline)

    fun bindData(section: Section, onSelected: (Section) -> Unit){
        txtName.text = section.title
        txtDescription.text = section.description


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