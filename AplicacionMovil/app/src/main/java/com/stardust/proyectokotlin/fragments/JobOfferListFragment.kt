package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.adapters.JobOfferAdapter
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.services.JobOfferConnectionManager


class JobOfferListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JobOfferAdapter
    private lateinit var bttnAdd: FloatingActionButton
    //private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bttnLogout: TextView
    private val jobOffer = ArrayList<JobOffer>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_offer_list, container, false)
        recyclerView = view.findViewById(R.id.jobOffersRecyclerView)

        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var page = 1
/*
        var btmAppBar = view!!.findViewById<BottomAppBar>(R.id.bottomAppBar)
        btmAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }
*/
        bttnLogout = view!!.findViewById(R.id.logoutButton)
        bttnLogout.setOnClickListener() {
            val loginFragment = LoginFragment()
            loginFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrame, loginFragment)
            //transaction.addToBackStack(null)
            transaction.commit()
        }
/*
        btmAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                //R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    //true
                //}
                else -> false
            }
        }
*/

        adapter = JobOfferAdapter(jobOffer)
        bttnAdd = view!!.findViewById(R.id.buttonUploadJobOffer)

        bttnAdd?.setOnClickListener() {
            val addContactFragment = AddJobOfferFragment()
            addContactFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, addContactFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
/*
        adapter.onContactSelected {
            val detailedContactFragment = DetailedContactFragment()
            val jobOffer = it
            val parameters = Bundle()
            parameters.putSerializable("oferta de trabajo", it)
            detailedContactFragment.arguments = parameters

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, detailedContactFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
*/
        recyclerView.setHasFixedSize(true)
        val manager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

        JobOfferConnectionManager.loadJobOffers(page, success = {
            jobOffer.addAll(it)
            adapter.notifyDataSetChanged()
        }, fail = {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }
}