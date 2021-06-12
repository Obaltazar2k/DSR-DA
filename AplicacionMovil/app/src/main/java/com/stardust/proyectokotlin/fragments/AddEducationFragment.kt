package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.Education
import com.stardust.proyectokotlin.model.LaboralExperience
import com.stardust.proyectokotlin.services.EducationConnectionManager
import com.stardust.proyectokotlin.services.LaboralExperienceConnectionManager
import java.util.*


class AddEducationFragment : Fragment() {
    private lateinit var txtTitle: EditText
    private lateinit var txtUniversity: EditText
    private lateinit var txtDiscipline: EditText
    private lateinit var txtAverage: EditText
    private lateinit var txtDescription: EditText
    private lateinit var txtStartDate: EditText
    private lateinit var txtEndDate: EditText
    private lateinit var bttnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_education, container, false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtTitle = view!!.findViewById(R.id.itemEducationTitle)
        txtUniversity = view!!.findViewById(R.id.itemEducationUniversity)
        txtDiscipline = view!!.findViewById(R.id.itemEducationDiscipline)
        txtAverage = view!!.findViewById(R.id.itemEducationAvarage)
        txtDescription = view!!.findViewById(R.id.itemEducationDescription)
        txtStartDate = view!!.findViewById(R.id.itemEducationStartDate)
        txtEndDate = view!!.findViewById(R.id.itemEducationEndDate)
        bttnAdd = view!!.findViewById(R.id.buttonUploadEducation)

        txtStartDate.setOnClickListener() {
            val newFragment =
                DatePickerFragment.newInstance { datePicker, year, month, day -> // +1 because January is zero
                    val selectedDate = (month + 1).toString() + "/" + day.toString() + "/" + year
                    txtStartDate.setText(selectedDate)
                }
            newFragment.show(requireActivity().supportFragmentManager, "datePicker")
        }

        txtEndDate.setOnClickListener() {
            val newFragment =
                DatePickerFragment.newInstance { datePicker, year, month, day -> // +1 because January is zero
                    val selectedDate = (month + 1).toString() + "/" + day.toString() + "/" + year
                    txtEndDate.setText(selectedDate)
                }
            newFragment.show(requireActivity().supportFragmentManager, "datePicker")
        }

        bttnAdd.setOnClickListener() {
            val title = txtTitle.text.toString()
            val university = txtUniversity.text.toString()
            val discipline = txtDiscipline.text.toString()
            val average = txtAverage.text.toString()
            val description = txtDescription.text.toString()
            val startDate = txtStartDate.text.toString()
            val endDate = txtEndDate.text.toString()

            if (university.isNotEmpty() && discipline.isNotEmpty() && title.isNotEmpty() &&
                average.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty() &&
                description.isNotEmpty() )  {

                try {
                    average.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(requireActivity(), "Escribe un promedio valido", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val education = Education()
                education.title = title
                education.university = university
                education.discipline = discipline
                education.average = average.toInt()
                education.description = description
                education.start_date = startDate
                education.end_date = endDate

                EducationConnectionManager.uploadEducation(education, success = {
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