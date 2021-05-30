package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.isAnEmail
import com.stardust.proyectokotlin.model.ConnectionManager
import com.stardust.proyectokotlin.model.OrganizationUser
import com.stardust.proyectokotlin.model.User

class OrganizationSignupFragment(private val user: User) : Fragment() {

    private lateinit var spinnerWorkSector: Spinner
    private lateinit var bttnRegisterOrganization: Button
    private lateinit var txtOrganizationName: EditText
    private lateinit var txtAbout: EditText
    private lateinit var txtZipCode: EditText
    private lateinit var txtContactName: EditText
    private lateinit var txtContactPhone: EditText
    private lateinit var txtContactEmail: EditText
    private lateinit var txtWebSite: EditText
    private lateinit var bttnBack: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organization_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        spinnerWorkSector = view!!.findViewById(R.id.spinnerWorkSector)
        bttnRegisterOrganization = view!!.findViewById(R.id.buttonIndependientUser)
        txtAbout = view!!.findViewById(R.id.editTextPersonalDescription)
        txtContactEmail = view!!.findViewById(R.id.editTextContactEmail)
        txtContactName = view!!.findViewById(R.id.editTextOcupation)
        txtContactPhone = view!!.findViewById(R.id.editTextContactPhone)
        txtOrganizationName = view!!.findViewById(R.id.editTextIndpendientName)
        txtWebSite = view!!.findViewById(R.id.editTextWebSite)
        txtZipCode = view!!.findViewById(R.id.editTextSurname)
        bttnBack = view!!.findViewById(R.id.imageButtonSignup)

        val workSector = arrayOf<String?>(
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
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this.requireContext(), R.layout.item, workSector)
        arrayAdapter.setDropDownViewResource(R.layout.item)
        spinnerWorkSector.adapter = arrayAdapter

        bttnRegisterOrganization?.setOnClickListener() {
            val about = txtAbout?.text.toString()
            val contactEmail = txtContactEmail?.text.toString()
            val contactName = txtContactName?.text.toString()
            val contactPhone = txtContactPhone?.text.toString()
            val organizationName = txtOrganizationName?.text.toString()
            val webSite = txtWebSite?.text.toString()
            val zipCode = txtZipCode?.text.toString()
            val workSector = spinnerWorkSector?.selectedItem.toString()
            if (contactEmail.isAnEmail()) {
                if (about.isNotEmpty() && contactName.isNotEmpty() && contactPhone.isNotEmpty() && organizationName.isNotEmpty() && webSite.isNotEmpty() && zipCode.isNotEmpty()) {
                    var organizationUser = OrganizationUser()
                    organizationUser.about = about
                    organizationUser.contactEmail = contactEmail
                    organizationUser.contactName = contactName
                    organizationUser.contactPhone = contactPhone
                    organizationUser.name = organizationName
                    organizationUser.webSite = webSite
                    organizationUser.zipCode = zipCode.toInt()
                    organizationUser.workSector = workSector.toUpperCase()
                    organizationUser.user = user

                    ConnectionManager.registerOrganizationUser(organizationUser, success = {
                        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                        
                        val loginFragment = LoginFragment()
                        loginFragment.arguments = requireActivity().intent.extras

                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.add(R.id.mainFrame, loginFragment)
                        //transaction.addToBackStack(null)
                        transaction.commit()
                    }, fail = {
                        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                    })

                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Por favor llena todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireActivity(), "Introduce un correo válido", Toast.LENGTH_SHORT).show()
            }
        }

        bttnBack?.setOnClickListener() {
            getFragmentManager()?.popBackStack()
        }
    }
}