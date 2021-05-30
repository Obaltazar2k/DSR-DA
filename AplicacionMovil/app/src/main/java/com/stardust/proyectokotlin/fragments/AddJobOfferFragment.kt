package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.isAnEmail
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.services.JobOfferConnectionManager

class AddJobOfferFragment : Fragment() {
    private lateinit var txtJob: EditText
    private lateinit var txtJobCategory: EditText
    private lateinit var txtLocation: EditText
    private lateinit var txtDescription: EditText
    //private lateinit var txtFoto: EditText
    private lateinit var bttnAdd: Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_job_offer, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtJob = view!!.findViewById(R.id.editTextJob)
        txtJobCategory = view!!.findViewById(R.id.editTextJobCategory)
        txtLocation = view!!.findViewById(R.id.editTextLocation)
        txtDescription = view!!.findViewById(R.id.editTextDescription)
        //txtFoto = view!!.findViewById(R.id.editTextPhoto)
        bttnAdd = view!!.findViewById(R.id.buttonUploadJobOffer)

        bttnAdd?.setOnClickListener() {
            val job = txtJob?.text.toString()
            val jobCategory = txtJobCategory?.text.toString()
            val location = txtLocation?.text.toString()
            val description = txtDescription?.text.toString()
            //val photo = txtFoto?.text.toString()
            if (job.isNotEmpty() && jobCategory.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty() /*&& photo.isNotEmpty()*/) {
                val jobOffer = JobOffer()
                jobOffer.job = job
                jobOffer.jobCategory = location
                jobOffer.location = jobCategory
                jobOffer.description = description
                jobOffer.media = emptyList()
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