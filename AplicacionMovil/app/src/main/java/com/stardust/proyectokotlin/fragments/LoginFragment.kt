package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.isAnEmail
import com.stardust.proyectokotlin.model.ConnectionManager

class LoginFragment : Fragment() {
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var bttnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtEmail = view!!.findViewById(R.id.editTextEmail)
        txtPassword = view!!.findViewById(R.id.editTextPassword)
        bttnAdd = view!!.findViewById(R.id.buttonLogin)

        bttnAdd?.setOnClickListener() {
            val email = txtEmail?.text.toString()
            val password = txtPassword?.text.toString()
            if (email.isAnEmail()) {
                if (password.isNotEmpty()) {
                    ConnectionManager.loadLogin(email, password, success = {
                        val contactListFragment = ContactListFragment()
                        contactListFragment.arguments = requireActivity().intent.extras

                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.add(R.id.mainFrame, contactListFragment)
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
    }
}