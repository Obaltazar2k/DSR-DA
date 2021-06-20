package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.model.IndependientUser
import com.stardust.proyectokotlin.model.User
import com.stardust.proyectokotlin.services.GeneralUserConnectionManager

class IndependientSignupFragment(private val user: User) : Fragment() {

    private lateinit var bttnRegisterIndependient: Button
    private lateinit var txtName: EditText
    private lateinit var txtSurname: EditText
    private lateinit var txtOcupation: EditText
    private lateinit var txtPersonalDescription: EditText
    private lateinit var bttnBack: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_independient_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bttnRegisterIndependient = view!!.findViewById(R.id.buttonIndependientUser)
        txtName = view!!.findViewById(R.id.editTextIndpendientName)
        txtSurname = view!!.findViewById(R.id.editTextSurname)
        txtOcupation = view!!.findViewById(R.id.editTextOcupation)
        txtPersonalDescription = view!!.findViewById(R.id.editTextPersonalDescription)
        bttnBack = view!!.findViewById(R.id.imageButtonSignup)

        bttnRegisterIndependient?.setOnClickListener() {
            val name = txtName?.text.toString()
            val surname = txtSurname?.text.toString()
            val ocupation = txtOcupation?.text.toString()
            val personalDescription = txtPersonalDescription?.text.toString()

            if (name.isNotEmpty() && surname.isNotEmpty() && ocupation.isNotEmpty() && personalDescription.isNotEmpty()) {
                var independientUser = IndependientUser()
                independientUser.name = name
                independientUser.surnames = surname
                independientUser.ocupation = ocupation
                independientUser.persoanlDescription = personalDescription
                independientUser.user = user

                GeneralUserConnectionManager.registerIndependientUser(independientUser, success = {
                    Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()

                    val validationFragment = ValidationFragment(user)
                    validationFragment.arguments = requireActivity().intent.extras

                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.add(R.id.mainFrame, validationFragment)
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
        }

        bttnBack?.setOnClickListener() {
            getFragmentManager()?.popBackStack()
        }

    }
}