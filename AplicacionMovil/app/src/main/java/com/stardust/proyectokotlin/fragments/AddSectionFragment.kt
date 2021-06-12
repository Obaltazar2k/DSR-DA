package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Certification
import com.stardust.proyectokotlin.model.Education
import com.stardust.proyectokotlin.model.LaboralExperience
import com.stardust.proyectokotlin.model.Section
import com.stardust.proyectokotlin.services.CertificationConnectionManager
import com.stardust.proyectokotlin.services.EducationConnectionManager
import com.stardust.proyectokotlin.services.LaboralExperienceConnectionManager
import com.stardust.proyectokotlin.services.SectionConnectionManager
import java.util.*


class AddSectionFragment : Fragment() {
    private lateinit var txtTitle: EditText
    private lateinit var txtDescription: EditText
    private lateinit var bttnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_section, container, false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtTitle = view!!.findViewById(R.id.itemSectionTitle)
        txtDescription = view!!.findViewById(R.id.itemSectionDescription)
        bttnAdd = view!!.findViewById(R.id.buttonUploadSection)

        bttnAdd.setOnClickListener() {
            val title = txtTitle.text.toString()
            val description = txtDescription.text.toString()

            if (description.isNotEmpty() && title.isNotEmpty())  {

                val section = Section()
                section.title = title
                section.description = description

                SectionConnectionManager.uploadSection(section, success = {
                    val homeFragment = HomeFragment()
                    homeFragment.arguments = requireActivity().intent.extras

                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.mainFrame, homeFragment)
                    //transaction.addToBackStack(null)
                    transaction.commit()
                    Toast.makeText(requireActivity(), it,Toast.LENGTH_SHORT).show()
                }, fail = {
                    Toast.makeText(requireActivity(), it,Toast.LENGTH_SHORT).show()
                })
            } else {
                Toast.makeText(requireActivity(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

}