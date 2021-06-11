package com.stardust.proyectokotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Section

class SectionAdapter(val sections: ArrayList<Section>): RecyclerView.Adapter<SectionViewHolder>() {

    private var selectedBlock: (Section) -> Unit = {}

    fun onLaboralExperienceSelected(selected: (Section) -> Unit){
        selectedBlock = selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_section, parent, false)

        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]

        holder.bindData(section, selectedBlock)
    }

    override fun getItemCount(): Int {
        return sections.size
    }
}