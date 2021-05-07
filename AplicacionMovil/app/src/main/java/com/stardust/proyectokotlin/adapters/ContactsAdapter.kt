package com.stardust.proyectokotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Contact

class ContactsAdapter(val contacts: ArrayList<Contact>): RecyclerView.Adapter<ContactViewHolder>() {

    private var selectedBlock: (Contact) -> Unit = {}

    fun onContactSelected(selected: (Contact) -> Unit){
        selectedBlock = selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_contact, parent, false)

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]

        holder.bindData(contact, selectedBlock)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}