package com.stardust.proyectokotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Certification

class CertificationAdapter(val certifications: ArrayList<Certification>): RecyclerView.Adapter<CertificationViewHolder>() {

    private var selectedBlock: (Certification) -> Unit = {}

    fun onLaboralExperienceSelected(selected: (Certification) -> Unit){
        selectedBlock = selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_certification, parent, false)

        return CertificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: CertificationViewHolder, position: Int) {
        val certification = certifications[position]

        holder.bindData(certification, selectedBlock)
    }

    override fun getItemCount(): Int {
        return certifications.size
    }
}