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
import com.stardust.proyectokotlin.services.CertificationConnectionManager
import com.stardust.proyectokotlin.services.EducationConnectionManager
import com.stardust.proyectokotlin.services.LaboralExperienceConnectionManager
import java.util.*


class AddCertificationFragment : Fragment() {
    private lateinit var txtTitle: EditText
    private lateinit var txtCompany: EditText
    private lateinit var txtUrl: EditText
    private lateinit var txtStartDate: EditText
    private lateinit var txtEndDate: EditText
    private lateinit var bttnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_certification, container, false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtTitle = view!!.findViewById(R.id.itemCertificationTitle)
        txtCompany = view!!.findViewById(R.id.itemCertificationCompany)
        txtUrl = view!!.findViewById(R.id.itemCertificationUrl)
        txtStartDate = view!!.findViewById(R.id.itemCertificationExpeditionDate)
        txtEndDate = view!!.findViewById(R.id.itemCertificationExpirationDate)
        bttnAdd = view!!.findViewById(R.id.buttonUploadCertification)

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
            val company = txtCompany.text.toString()
            val url = txtUrl.text.toString()
            val startDate = txtStartDate.text.toString()
            val endDate = txtEndDate.text.toString()

            if (company.isNotEmpty() && url.isNotEmpty() && title.isNotEmpty() &&
                startDate.isNotEmpty() && endDate.isNotEmpty())  {

                val certification = Certification()
                certification.title = title
                certification.issuing_company = company
                certification.credential_url = url
                certification.expedition_date = startDate
                certification.expiration_date = endDate

                CertificationConnectionManager.uploadCertification(certification, success = {
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