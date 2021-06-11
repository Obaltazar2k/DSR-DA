package com.stardust.proyectokotlin.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Education

class EducationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //private val imgPhoto: ImageView = itemView.findViewById(R.id.itemContactPhoto)
    private val txtDate: TextView = itemView.findViewById(R.id.itemEducationDate)
    private val txtDescription: TextView = itemView.findViewById(R.id.itemEducationDescription)
    private val txtName: TextView = itemView.findViewById(R.id.itemEducationTitle)
    private val txtAvarage: TextView = itemView.findViewById(R.id.itemEducationAvarage)
    private val txtUniversity: TextView = itemView.findViewById(R.id.itemEducationUniversity)
    private val txtDiscipline: TextView = itemView.findViewById(R.id.itemEducationDiscipline)

    fun bindData(education: Education, onSelected: (Education) -> Unit){
        txtDate.text = education.start_date + " - " + education.end_date
        txtDescription.text = education.description
        txtName.text = education.title
        txtAvarage.text = education.average.toString()
        txtUniversity.text = education.university
        txtDiscipline.text = education.discipline


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