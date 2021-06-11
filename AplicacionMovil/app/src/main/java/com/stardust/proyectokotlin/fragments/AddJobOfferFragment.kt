package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.services.JobOfferConnectionManager

class AddJobOfferFragment : Fragment() {
    private lateinit var spinnerJobCategory: Spinner
    private lateinit var txtJob: EditText
    private lateinit var txtLocation: EditText
    private lateinit var txtDescription: EditText
    //private lateinit var txtFoto: EditText
    private lateinit var bttnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_job_offer, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        spinnerJobCategory = view!!.findViewById(R.id.spinnerJobCategory)
        txtJob = view!!.findViewById(R.id.editTextJob)
        txtLocation = view!!.findViewById(R.id.editTextLocation)
        txtDescription = view!!.findViewById(R.id.editTextDescription)
        //txtFoto = view!!.findViewById(R.id.editTextPhoto)
        bttnAdd = view!!.findViewById(R.id.buttonUploadJobOffer)

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
        val arrayAdapter = ArrayAdapter<Any?>(this.requireContext(), R.layout.item2, workSector)
        arrayAdapter.setDropDownViewResource(R.layout.item2)
        spinnerJobCategory.adapter = arrayAdapter

        bttnAdd?.setOnClickListener() {
            val job = txtJob?.text.toString()
            val jobCategory = spinnerJobCategory.selectedItem.toString()
            val location = txtLocation?.text.toString()
            val description = txtDescription?.text.toString()
            //val photo = txtFoto?.text.toString()
            if (job.isNotEmpty() && jobCategory.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty() /*&& photo.isNotEmpty()*/) {
                val jobOffer = JobOffer()
                jobOffer.job = job
                jobOffer.jobCategory = jobCategory.toUpperCase()
                jobOffer.location = location
                jobOffer.description = description
                //jobOffer.media = emptyList()
                //jobOffer.photo = photo
                JobOfferConnectionManager.uploadJobOffer(jobOffer, success = {
                    val jobOfferListFragment = JobOfferListFragment()
                    jobOfferListFragment.arguments = requireActivity().intent.extras

                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.mainFrame, jobOfferListFragment)
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