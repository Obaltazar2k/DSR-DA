package com.stardust.proyectokotlin.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.LaboralExperience
import com.stardust.proyectokotlin.services.LaboralExperienceConnectionManager
import java.util.*


class AddCertificationFragment : Fragment() {
    private lateinit var spinnerJobCategory: Spinner
    private lateinit var spinnerKindOfJob: Spinner
    private lateinit var txtTitle: EditText
    private lateinit var txtOrganization: EditText
    private lateinit var txtCountry: EditText
    private lateinit var txtCity: EditText
    private lateinit var txtStartDate: EditText
    private lateinit var txtEndDate: EditText
    private lateinit var bttnAdd: Button
    private lateinit var bttnStartDate: Button
    private lateinit var bttnEndDate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_laboral_experience, container, false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        spinnerJobCategory = view!!.findViewById(R.id.spinnerJobCategory)
        spinnerKindOfJob = view!!.findViewById(R.id.spinnerKindOfJob)
        txtTitle = view!!.findViewById(R.id.itemLaboralExperienceName)
        txtOrganization = view!!.findViewById(R.id.itemLaboralExperienceOrganization)
        txtCountry = view!!.findViewById(R.id.itemlaboralExperienceCountry)
        txtCity = view!!.findViewById(R.id.itemlaboralExperienceCity)
        txtStartDate = view!!.findViewById(R.id.itemLaboralExperienceStartDate)
        txtEndDate = view!!.findViewById(R.id.itemLaboralExperienceEndDate)
        bttnAdd = view!!.findViewById(R.id.buttonUploadLaboralExperience)

        val kindOfJob = arrayOf<String?>(
            "Agricultura, plantaciones, u otros sectores rurales",
            "Alimentacion, bebidas, tabaco",
            "Comercio",
            "Construccion",
            "Educiacion",
            "Fabricacion de material de transporte",
            "Funcion publica",
            "Hoteleria, restauración, turismo",
            "Industrias quimicas",
            "Ingenieria mecanica y electica",
            "Medios de comunicacion, cultura, graficos",
            "Mineria",
            "Petroleo y produccion de gases, refinacion de petroleo",
            "Produccion de materiales basicos",
            "Servicios de correo y telecomunicaciones",
            "Servicios de salud",
            "Servicios financieros, servicios profesionales",
            "Servicios publicos",
            "Silvicultura, madera, celulosa, papel",
            "Textiles, vestido, cuero, calzado",
            "Transporte",
            "Transporte maritimo")
        val arrayAdapter = ArrayAdapter<Any?>(this.requireContext(), R.layout.item2, kindOfJob)
        arrayAdapter.setDropDownViewResource(R.layout.item2)
        spinnerJobCategory.adapter = arrayAdapter

        val workSector = arrayOf<String?>(
            "CARPINTERO",
            "CERRAJERO",
            "MECANICO",
            "OBRERO",
            "FONTANERO",
            "SOLDADOR",
            "ARTISTA",
            "SASTRE",
            "AGRIGULTOR",
            "COCINERO",
            "REPARTIDOR",
            "SEGURIDAD",
            "ESTILISTA",
            "EXTERMINADOR",
            "CAMARERO",
            "CONDUCTOR",
            "ELECTRICISTA",
            "FOTOGRAFO",
            "CASERO",
            "JARDINERO",
            "VENDEDOR",
            "DENTISTA",
            "ENFERMERO",
            "DOCTOR",
            "EMPRESARIO",
            "DEPORTISTA",
            "ADMINISTRADOR",
            "SECRETARIO",
            "SOLDADO",
            "CIENTIFICO",
            "PROFESOR",
            "POLICIA",
            "GERENTE",
            "BOMBERO",
            "INGENIERO",
            "ARQUITECTO",
            "PERIODISTA",
            "BIBLIOTECARIO",
            "ABOGADO",
            "OTRO")
        val arrayAdapter2 = ArrayAdapter<Any?>(this.requireContext(), R.layout.item2, workSector)
        arrayAdapter2.setDropDownViewResource(R.layout.item2)
        spinnerKindOfJob.adapter = arrayAdapter

        bttnAdd.setOnClickListener() {
            val jobCategory = spinnerJobCategory.selectedItem.toString()
            val kindOfJob = spinnerKindOfJob.selectedItem.toString()
            val title = txtTitle.text.toString()
            val organization = txtOrganization.text.toString()
            val country = txtCountry.text.toString()
            val city = txtCity.text.toString()
            val startDate = txtStartDate.text.toString()
            val endDate = txtEndDate.text.toString()

            if (jobCategory.isNotEmpty() && kindOfJob.isNotEmpty() && title.isNotEmpty() && organization.isNotEmpty() && country.isNotEmpty() &&
                city.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty()) {
                val laboralExperience = LaboralExperience()
                laboralExperience.job_category = jobCategory.toUpperCase(Locale.ROOT)
                laboralExperience.sector = kindOfJob.toUpperCase(Locale.ROOT)
                laboralExperience.job_title = title
                laboralExperience.company_name = organization
                laboralExperience.location = "$city, $country"
                laboralExperience.start_date = startDate
                laboralExperience.end_date = endDate

                LaboralExperienceConnectionManager.uploadLaboralExperience(laboralExperience, success = {
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