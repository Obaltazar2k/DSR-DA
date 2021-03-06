package com.stardust.proyectokotlin.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.adapters.JobOfferAdapter
import com.stardust.proyectokotlin.model.JobOffer
import com.stardust.proyectokotlin.services.JobOfferConnectionManager


class JobOfferListFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JobOfferAdapter
    private val jobOffer = ArrayList<JobOffer>()
    private lateinit var layoutManager: LinearLayoutManager
    var page = 1
    var isLoading = true

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_offer_list, container, false)
        recyclerView = view.findViewById(R.id.jobOffersRecyclerView)
        //bottomAppBar = view.findViewById(R.id.bottomAppBar)

        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = JobOfferAdapter(jobOffer, 1)

        progressBar = view!!.findViewById(R.id.bottomProgressBar)
        recyclerView.setHasFixedSize(true)
        val manager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

        getMoreJobOffers()

        layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = manager.childCount
                    val pastVisibleItem = manager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount

                    if(!isLoading){
                        if((visibleItemCount + pastVisibleItem) >= total) {
                            Toast.makeText(requireActivity(), "Cargando...", Toast.LENGTH_SHORT).show()
                            isLoading = true
                            page++
                            progressBar.visibility = View.VISIBLE
                            getMoreJobOffers()
                        }
                    }
                }
            }
        })
    }

    private fun getMoreJobOffers() {
        JobOfferConnectionManager.loadJobOffers(page, success = {
            jobOffer.addAll(it)
            adapter.notifyDataSetChanged()
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            isLoading = false
        }, fail = {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        })
    }
}