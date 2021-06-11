package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.Token

class HomeFragment : Fragment() {
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bttnLogout: TextView
    private lateinit var bttnProfile: TextView
    private lateinit var bttnAdd: FloatingActionButton
    private lateinit var bttnList: TextView
    private lateinit var bttnMyOffers: TextView
    private lateinit var fragment: FragmentContainerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        bottomAppBar = view.findViewById(R.id.bottomAppBar)
        // Inflate the layout for this fragment
        return view!!
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragment = view!!.findViewById(R.id.fragmentContainerView)

        bttnLogout = view!!.findViewById(R.id.logoutButton)
        bttnLogout.setOnClickListener() {
            val loginFragment = LoginFragment()
            loginFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrame, loginFragment)
            //transaction.addToBackStack(null)
            transaction.commit()
        }

        bttnProfile = view!!.findViewById(R.id.profile_button)
        bttnProfile.setOnClickListener() {
            if (Token.kindOf.equals("IND")) {
                val independientProfileFragment = IndependientProfileFragment()
                independientProfileFragment.arguments = requireActivity().intent.extras

                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, independientProfileFragment)
                fragment.removeAllViews()
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                val orgaizationProfileFragment = OrganizationProfileFragment()
                orgaizationProfileFragment.arguments = requireActivity().intent.extras

                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, orgaizationProfileFragment)
                fragment.removeAllViews()
                transaction.addToBackStack(null)
                transaction.commit()
            }


        }

        bttnList = view!!.findViewById(R.id.jobOffers_list)
        bttnList.setOnClickListener() {
            val jobOfferListFragment = JobOfferListFragment()
            jobOfferListFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, jobOfferListFragment)
            fragment.removeAllViews()
            transaction.addToBackStack(null)
            transaction.commit()
        }

        bttnMyOffers = view!!.findViewById(R.id.my_jobOffers)
        bttnMyOffers.setOnClickListener() {
            val myJobOfferListFragment = MyJobOfferListFragment()
            myJobOfferListFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, myJobOfferListFragment)
            fragment.removeAllViews()
            transaction.addToBackStack(null)
            transaction.commit()
        }

        bttnAdd = view!!.findViewById(R.id.buttonUploadJobOffer)
        bttnAdd?.setOnClickListener() {
            val addContactFragment = AddJobOfferFragment()
            addContactFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragmentContainerView, addContactFragment)
            fragment.removeAllViews()
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}