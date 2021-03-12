package com.stardust.practica1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class miAdaptador(contexto: Context, array: Array<String>):BaseAdapter() {

    private val miContexto : Context
    private val arreglo : Array<String>

    init {
        miContexto = contexto
        arreglo = array
    }

    //cuantas filas se generan
    override fun getCount(): Int {
        return 7
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    //regresa el id de cada elemento de la lista
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //genera cada una de las filas dentro de la lista
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(miContexto)
        val listRowLayout = layoutInflater.inflate(R.layout.fila_lista, parent, false)

        val txtDescription = listRowLayout.findViewById<TextView>(R.id.textViewDescription)
        if (position < 7 )
            txtDescription.text = arreglo[position]
        else
            txtDescription.text = "No hay más de 7 días a la semana"

        val txtTitulo = listRowLayout.findViewById<TextView>(R.id.textViewTitle)
        txtTitulo.text = "Esta es el titulo $position"

        val imgView = listRowLayout.findViewById<ImageView>(R.id.imageView)

        return listRowLayout
        /*
        val texto = TextView(miContexto)
        texto.text = "esta es una fila mundo! $position"
        return texto
        */
    }

}