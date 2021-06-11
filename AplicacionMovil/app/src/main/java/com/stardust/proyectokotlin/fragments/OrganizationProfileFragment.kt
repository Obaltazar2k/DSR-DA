package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
import com.stardust.proyectokotlin.services.OrganizationUserConnectionManager


class OrganizationProfileFragment : Fragment() {
    //private lateinit var imageView: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtSector: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtUrl: TextView
    private lateinit var txtContactName: TextView
    private lateinit var txtContactEmail: TextView
    private lateinit var txtContactPhone: TextView
    private lateinit var txtLocation: TextView
    private lateinit var txtDescription: TextView

    private lateinit var progressBar: ProgressBar
    private lateinit var nestedScrollView: NestedScrollView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_organization_profile, container, false)
        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar = view!!.findViewById(R.id.organizationProfileProgressBar)
        nestedScrollView = view!!.findViewById(R.id.itemOrganizationLinearLayout)

        txtName = view!!.findViewById(R.id.itemOrganizationProfileName)
        txtSector = view!!.findViewById(R.id.itemOrganizationProfileSector)
        txtEmail = view!!.findViewById(R.id.itemOrganizationEmail)
        txtUrl = view!!.findViewById(R.id.itemOrganizationUrl)
        txtContactName = view!!.findViewById(R.id.itemOrganizationContactName)
        txtContactEmail = view!!.findViewById(R.id.itemOrganizationContactEmail)
        txtContactPhone = view!!.findViewById(R.id.itemOrganizationContactPhone)
        txtLocation = view!!.findViewById(R.id.itemOrganizationLocation)
        txtDescription = view!!.findViewById(R.id.itemOrganizationDescription)

        OrganizationUserConnectionManager.getProfileInfo(Token.username, success = {
            txtName.text = it.name
            txtSector.text = it.workSector
            txtEmail.text = it.user.email
            txtUrl.text = it.webSite
            txtContactName.text = it.contactName
            txtContactEmail.text = it.contactEmail
            txtContactPhone.text = it.contactPhone
            txtLocation.text = it.user.city + ", " + it.user.country + ", CP: " + it.zipCode
            txtDescription.text = it.about


            nestedScrollView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE

            /*
            if (!independientUser.photo.isNullOrEmpty())
                Picasso.get().load(independientUser.photo).into(imageView)
             */

        }, fail = {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            progressBar.visibility = View.GONE
        })


    }
}