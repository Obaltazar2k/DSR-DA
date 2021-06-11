package com.stardust.proyectokotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Education
import com.stardust.proyectokotlin.model.LaboralExperience

class EducationAdapter(val educations: ArrayList<Education>): RecyclerView.Adapter<EducationViewHolder>() {

    private var selectedBlock: (Education) -> Unit = {}

    fun onLaboralExperienceSelected(selected: (Education) -> Unit){
        selectedBlock = selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_education, parent, false)

        return EducationViewHolder(view)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val education = educations[position]

        holder.bindData(education, selectedBlock)
    }

    override fun getItemCount(): Int {
        return educations.size
    }
}