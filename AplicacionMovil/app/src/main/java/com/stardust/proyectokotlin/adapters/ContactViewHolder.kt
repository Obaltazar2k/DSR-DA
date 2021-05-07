package com.stardust.proyectokotlin.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Contact

class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val imgPhoto: ImageView = itemView.findViewById(R.id.itemContactPhoto)
    private val txtName: TextView = itemView.findViewById(R.id.itemContactName)
    private val txtWork: TextView = itemView.findViewById(R.id.itemContactWork)
    private val txtPhone: TextView = itemView.findViewById(R.id.itemContactPhone)
    private val txtEmail: TextView = itemView.findViewById(R.id.itemContactEmail)

    fun bindData(contact: Contact, onSelected: (Contact) -> Unit){
        txtName.text = contact.name
        txtWork.text = contact.work
        txtPhone.text = contact.phone
        txtEmail.text = contact.email
        if(!contact.photo.isNullOrEmpty())
            Picasso.get().load(contact.photo).into(imgPhoto)

        itemView.setOnClickListener {
            onSelected(contact)
        }

    }
}