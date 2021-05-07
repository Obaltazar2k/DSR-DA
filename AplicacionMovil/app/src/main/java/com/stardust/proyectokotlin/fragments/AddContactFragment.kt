package com.stardust.proyectokotlin.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.stardust.proyectokotlin.R
import com.stardust.proyectokotlin.isAnEmail
import com.stardust.proyectokotlin.model.ConnectionManager
import com.stardust.proyectokotlin.model.Contact

class AddContactFragment : Fragment() {
    private lateinit var txtName: EditText
    private lateinit var txtWork: EditText
    private lateinit var txtCorreo: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtFoto: EditText
    private lateinit var bttnAdd: Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtName = view!!.findViewById(R.id.editTextName)
        txtWork = view!!.findViewById(R.id.editTextWork)
        txtCorreo = view!!.findViewById(R.id.editTextCorreo)
        txtPhone = view!!.findViewById(R.id.editTextPhone)
        txtFoto = view!!.findViewById(R.id.editTextPhoto)
        bttnAdd = view!!.findViewById(R.id.buttonAddContact)

        bttnAdd?.setOnClickListener() {
            val name = txtName?.text.toString()
            val work = txtWork?.text.toString()
            val correo = txtCorreo?.text.toString()
            val phone = txtPhone?.text.toString()
            val photo = txtFoto?.text.toString()
            if (name.isNotEmpty() && work.isNotEmpty() && correo.isNotEmpty() && phone.isNotEmpty() && photo.isNotEmpty()) {
                if (correo.isAnEmail()) {
                    ///
                    val contact = Contact()
                    contact.name = name
                    contact.email = correo
                    contact.work = work
                    contact.phone = phone
                    contact.photo = photo
                    ConnectionManager.uploadContact(contact, success = {
                        val contactListFragment = ContactListFragment()
                        contactListFragment.arguments = requireActivity().intent.extras

                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.add(R.id.mainFrame, contactListFragment)
                        //transaction.addToBackStack(null)
                        transaction.commit()
                        Toast.makeText(requireActivity(), it,Toast.LENGTH_SHORT).show()
                    }, fail = {
                        Toast.makeText(requireActivity(), it,Toast.LENGTH_SHORT).show()
                    })
                } else {

                    Toast.makeText(requireActivity(), "Introduce un correo válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireActivity(), "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

}