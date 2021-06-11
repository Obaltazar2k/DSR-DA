package com.stardust.proyectokotlin.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Certification

class CertificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //private val imgPhoto: ImageView = itemView.findViewById(R.id.itemContactPhoto)
    private val txtDate: TextView = itemView.findViewById(R.id.itemCertificationDate)
    private val txtUrl: TextView = itemView.findViewById(R.id.itemCertificationUrl)
    private val txtName: TextView = itemView.findViewById(R.id.itemCertificationTitle)
    //private val txtAvarage: TextView = itemView.findViewById(R.id.itemEducationAvarage)
    private val txtCompany: TextView = itemView.findViewById(R.id.itemCertificationCompany)

    fun bindData(certification: Certification, onSelected: (Certification) -> Unit){
        txtDate.text = certification.expedition_date + " - " + certification.expiration_date
        txtUrl.text = certification.credential_url
        txtName.text = certification.title
        //txtAvarage.text = certification.average
        txtCompany.text = certification.issuing_company

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