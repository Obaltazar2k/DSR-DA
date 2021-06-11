package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.adapters.CertificationAdapter
import com.stardust.proyectokotlin.adapters.EducationAdapter
import com.stardust.proyectokotlin.adapters.LaboralExperienceAdapter
import com.stardust.proyectokotlin.adapters.SectionAdapter
import com.stardust.proyectokotlin.model.Certification
import com.stardust.proyectokotlin.model.Education
import com.stardust.proyectokotlin.model.LaboralExperience
import com.stardust.proyectokotlin.model.Section
import com.stardust.proyectokotlin.services.IndependientUserConnectionManager


class ProfileFragment : Fragment() {
    //private lateinit var imageView: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtWork: TextView
    private lateinit var txtLocation: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtDescription: TextView

    private lateinit var progressBar: ProgressBar
    private lateinit var nestedScrollView: NestedScrollView

    private lateinit var laboralExperienceRecyclerView: RecyclerView
    private lateinit var educationRecyclerView: RecyclerView
    private lateinit var certificationRecyclerView: RecyclerView
    private lateinit var sectionRecyclerView: RecyclerView

    private val laboralExperiences = ArrayList<LaboralExperience>()
    private val educations = ArrayList<Education>()
    private val certifications = ArrayList<Certification>()
    private val sections = ArrayList<Section>()

    private lateinit var laboralExperienceAdapter: LaboralExperienceAdapter
    private lateinit var educationAdapter: EducationAdapter
    private lateinit var certificatonAdapter: CertificationAdapter
    private lateinit var sectionAdapter: SectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        laboralExperienceRecyclerView = view.findViewById(R.id.laboralExperienceRecyclerView)
        educationRecyclerView = view.findViewById(R.id.educationRecyclerView)
        certificationRecyclerView = view.findViewById(R.id.certificationRecyclerView)
        sectionRecyclerView = view.findViewById(R.id.sectionRecylcerView)

        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar = view!!.findViewById(R.id.profileProgressBar)
        nestedScrollView = view!!.findViewById(R.id.nestedScrollView)

        laboralExperienceAdapter = LaboralExperienceAdapter(laboralExperiences)
        laboralExperienceRecyclerView.setHasFixedSize(true)
        val managerLaboralExperience = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        laboralExperienceRecyclerView.layoutManager = managerLaboralExperience
        laboralExperienceRecyclerView.adapter = laboralExperienceAdapter

        educationAdapter = EducationAdapter(educations)
        educationRecyclerView.setHasFixedSize(true)
        val managerEducation = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        educationRecyclerView.layoutManager = managerEducation
        educationRecyclerView.adapter = educationAdapter

        certificatonAdapter = CertificationAdapter(certifications)
        certificationRecyclerView.setHasFixedSize(true)
        val managerCertification = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        certificationRecyclerView.layoutManager = managerCertification
        certificationRecyclerView.adapter = certificatonAdapter

        sectionAdapter = SectionAdapter(sections)
        sectionRecyclerView.setHasFixedSize(true)
        val managerSection = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        sectionRecyclerView.layoutManager = managerSection
        sectionRecyclerView.adapter = sectionAdapter

        txtName = view!!.findViewById(R.id.itemProfileName)
        txtEmail = view!!.findViewById(R.id.itemProfileEmail)
        txtLocation = view!!.findViewById(R.id.itemProfileLocation)
        txtWork = view!!.findViewById(R.id.itemProfileJob)
        txtDescription = view!!.findViewById(R.id.itemProfileDescription)

        IndependientUserConnectionManager.getProfileInfo(Token.username, success = {
            txtName.text = it.name + " " + it.surnames
            txtEmail.text = it.user.email
            txtLocation.text = it.user.city + ", " + it.user.country
            txtWork.text = it.ocupation
            txtDescription.text = it.persoanlDescription

            laboralExperiences.addAll(it.laboral_experience!!)
            laboralExperienceAdapter.notifyDataSetChanged()

            educations.addAll(it.education!!)
            educationAdapter.notifyDataSetChanged()

            it.certification?.let { it1 -> certifications.addAll(it1) }
            certificatonAdapter.notifyDataSetChanged()

            it.section?.let { it1 -> sections.addAll(it1) }
            sectionAdapter.notifyDataSetChanged()

            nestedScrollView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE

            /*
            if (!independientUser.photo.isNullOrEmpty())
                Picasso.get().load(independientUser.photo).into(imageView)
             */

        }, fail = {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        })


    }
}