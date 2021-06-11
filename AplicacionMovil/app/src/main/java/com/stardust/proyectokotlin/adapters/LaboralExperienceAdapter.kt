package com.stardust.proyectokotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.LaboralExperience

class LaboralExperienceAdapter(val laboralExperiences: ArrayList<LaboralExperience>): RecyclerView.Adapter<LaboralExperienceViewHolder>() {

    private var selectedBlock: (LaboralExperience) -> Unit = {}

    fun onLaboralExperienceSelected(selected: (LaboralExperience) -> Unit){
        selectedBlock = selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaboralExperienceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_laboral_experience, parent, false)

        return LaboralExperienceViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaboralExperienceViewHolder, position: Int) {
        val laboralExperience = laboralExperiences[position]

        holder.bindData(laboralExperience, selectedBlock)
    }

    override fun getItemCount(): Int {
        return laboralExperiences.size
    }
}