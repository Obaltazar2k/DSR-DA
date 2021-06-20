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
import com.stardust.proyectokotlin.model.User
import com.stardust.proyectokotlin.services.GeneralUserConnectionManager

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
            val token = txtCode.text.toString()

            if (token.isNotEmpty()) {

                GeneralUserConnectionManager.validateUser(user.email, token, success = {
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
                    "Por favor llena el campo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        bttNewToken?.setOnClickListener() {
            GeneralUserConnectionManager.generateNewToken(user.email, success = {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }, fail = {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            })
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