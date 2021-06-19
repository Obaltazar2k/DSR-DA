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
import com.stardust.proyectokotlin.model.ConnectionManager
import com.stardust.proyectokotlin.model.IndependientUser
import com.stardust.proyectokotlin.model.User

class ValidationFragment(private val user: User) : Fragment() {

    private lateinit var bttnValidate: Button
    private lateinit var bttNewToken: Button
    private lateinit var txtCode: EditText
    private lateinit var bttnBack: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_validation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bttnValidate = view!!.findViewById(R.id.buttonValidate)
        bttNewToken = view!!.findViewById(R.id.buttonNewCode)
        txtCode = view!!.findViewById(R.id.validateTextEmail)
        bttnBack = view!!.findViewById(R.id.imageButtonSignup)

        bttnValidate?.setOnClickListener() {
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

                ConnectionManager.registerIndependientUser(independientUser, success = {
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
        }

        bttNewToken?.setOnClickListener() {
            getFragmentManager()?.popBackStack()
        }

        bttnBack?.setOnClickListener() {
            val loginFragment = LoginFragment()
            loginFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, loginFragment)
            //transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}