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
import com.stardust.proyectokotlin.Encrypt
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.isAnEmail
import com.stardust.proyectokotlin.model.User

class UserGeneralFragment : Fragment() {
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtCity: EditText
    private lateinit var txtCountry: EditText
    private lateinit var bttnRegister: Button
    private lateinit var bttnBack: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_general, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtEmail = view!!.findViewById(R.id.editTextIndpendientName)
        txtPassword = view!!.findViewById(R.id.editTextSurname)
        txtCity = view!!.findViewById(R.id.editTextOcupation)
        txtCountry = view!!.findViewById(R.id.editTextContactPhone)
        bttnRegister = view!!.findViewById(R.id.buttonIndependientUser)
        bttnBack = view!!.findViewById(R.id.imageButtonSignup)

        bttnRegister?.setOnClickListener() {
            val email = txtEmail?.text.toString()
            val password = Encrypt.hash(txtPassword?.text.toString())
            val city = txtCity?.text.toString()
            val country = txtCountry?.text.toString()
            if (email.isAnEmail()) {
                if (password.isNotEmpty()) {
                    if (city.isNotEmpty() && country.isNotEmpty()) {
                        val user = User()
                        user.email = email
                        user.password = password
                        user.city = city
                        user.country = country

                        val signupFragment = SignupFragment(user)
                        signupFragment.arguments = requireActivity().intent.extras

                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.add(R.id.mainFrame, signupFragment)
                        transaction.addToBackStack(null)
                        transaction.addToBackStack(null).hide(this)
                        transaction.commit()
                    } else {
                        Toast.makeText(requireActivity(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Introduce una contraseña", Toast.LENGTH_SHORT).show()
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