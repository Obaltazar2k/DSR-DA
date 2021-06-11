package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.Token
import com.stardust.proyectokotlin.Token.password
import com.stardust.proyectokotlin.Token.token
import com.stardust.proyectokotlin.Token.username
import com.stardust.proyectokotlin.isAnEmail
import com.stardust.proyectokotlin.model.ConnectionManager
import com.stardust.proyectokotlin.services.GeneralUserConnectionManager


class LoginFragment : Fragment() {
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var bttnAdd: Button
    private lateinit var bttnRegister: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fm: FragmentManager = activity!!.supportFragmentManager
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }

        txtEmail = view!!.findViewById(R.id.editTextEmail)
        txtPassword = view!!.findViewById(R.id.editTextPassword)
        bttnAdd = view!!.findViewById(R.id.buttonLogin)
        bttnRegister = view!!.findViewById(R.id.buttonSignup)

        bttnAdd?.setOnClickListener() {
            val email = txtEmail?.text.toString()
            val password = txtPassword?.text.toString()
            if (email.isAnEmail()) {
                if (password.isNotEmpty()) {
                    GeneralUserConnectionManager.loadLogin(email, password, success = {
                        Token.token = it
                        Token.username = email
                        Token.password = password
                        val homeFragment = HomeFragment()
                        homeFragment.arguments = requireActivity().intent.extras

                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.mainFrame, homeFragment)
                        //transaction.addToBackStack(null)
                        transaction.commit()
                    }, fail = {
                        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                    })
                } else {
                    Toast.makeText(requireActivity(), "Introduce una contraseña", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireActivity(), "Introduce un correo válido", Toast.LENGTH_SHORT).show()
            }
        }

        bttnRegister?.setOnClickListener() {
            val userGeneralFragment = UserGeneralFragment()
            userGeneralFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, userGeneralFragment)
            transaction.addToBackStack(null)
            transaction.addToBackStack(null).hide(this)
            transaction.commit()
        }
    }
}