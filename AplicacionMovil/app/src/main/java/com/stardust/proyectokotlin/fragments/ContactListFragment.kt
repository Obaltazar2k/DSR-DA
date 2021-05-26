package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.adapters.ContactsAdapter
import com.stardust.proyectokotlin.model.ConnectionManager
import com.stardust.proyectokotlin.model.Contact


class ContactListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactsAdapter
    private lateinit var bttnAdd: FloatingActionButton
    private lateinit var bottomAppBar: BottomAppBar
    private val contacts = ArrayList<Contact>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        recyclerView = view.findViewById(R.id.contactsRecyclerView)

        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var btmAppBar = view!!.findViewById<BottomAppBar>(R.id.bottomAppBar)
        btmAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

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

        adapter = ContactsAdapter(contacts)
        bttnAdd = view!!.findViewById(R.id.buttonAddJobOffer)

        bttnAdd?.setOnClickListener() {
            val addContactFragment = AddContactFragment()
            addContactFragment.arguments = requireActivity().intent.extras

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, addContactFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        adapter.onContactSelected {


            val detailedContactFragment = DetailedContactFragment()


            val contacto = it
            val parameters = Bundle()
            parameters.putSerializable("contacto", it)
            detailedContactFragment.arguments = parameters


            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, detailedContactFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        recyclerView.setHasFixedSize(true)
        val manager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

        ConnectionManager.loadContacts(success = {
            contacts.addAll(it)
            adapter.notifyDataSetChanged()
        }, fail = {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }
}