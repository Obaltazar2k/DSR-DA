package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.stardust.proyectokotlin.R

class SignupFragment : Fragment() {
    private lateinit var bttnRegisterOrganization: Button
    private lateinit var bttnRegisterIndependient: Button
    private lateinit var bttnBack: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bttnRegisterOrganization = view!!.findViewById(R.id.buttonSignupOrganization)
        bttnRegisterIndependient = view!!.findViewById(R.id.buttonSignupIndependient)
        bttnBack = view!!.findViewById(R.id.imageButtonSignup)

        bttnRegisterOrganization?.setOnClickListener() {
            val organizationSignupFragment = OrganizationSignupFragment()
            organizationSignupFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, organizationSignupFragment)
            transaction.addToBackStack(null)
            transaction.addToBackStack(null).hide(this)
            transaction.commit()

        }

        bttnRegisterIndependient?.setOnClickListener() {
            val independientSignupFragment = IndependientSignupFragment()
            independientSignupFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, independientSignupFragment)
            transaction.addToBackStack(null)
            transaction.addToBackStack(null).hide(this)
            transaction.commit()

        }

        bttnBack?.setOnClickListener() {
            getFragmentManager()?.popBackStack()
        }
    }
}