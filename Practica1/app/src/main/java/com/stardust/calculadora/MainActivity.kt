package com.stardust.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var txtNumeros: EditText
    private lateinit var bttnC: Button
    private lateinit var bttn7: Button
    private lateinit var bttn8: Button
    private lateinit var bttn9: Button
    private lateinit var bttnSumar: Button
    private lateinit var bttn4: Button
    private lateinit var bttn5: Button
    private lateinit var bttn6: Button
    private lateinit var bttnRestar: Button
    private lateinit var bttn1: Button
    private lateinit var bttn2: Button
    private lateinit var bttn3: Button
    private lateinit var bttnMultiplicar: Button
    private lateinit var bttn0: Button
    private lateinit var bttnIgual: Button
    private lateinit var bttnDividir: Button

    private var operador = ""
    private var numDetras: Int? = null
    private var lastButton = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculadora)

        txtNumeros = findViewById(R.id.editTextNumeros)
        bttn0 = findViewById(R.id.button0)
        bttn1 = findViewById(R.id.button1)
        bttn2 = findViewById(R.id.button2)
        bttn3 = findViewById(R.id.button3)
        bttn4 = findViewById(R.id.button4)
        bttn5 = findViewById(R.id.button5)
        bttn6 = findViewById(R.id.button6)
        bttn7 = findViewById(R.id.button7)
        bttn8 = findViewById(R.id.button8)
        bttn9 = findViewById(R.id.button9)
        bttnC = findViewById(R.id.buttonC)
        bttnSumar = findViewById(R.id.buttonSumar)
        bttnRestar = findViewById(R.id.buttonRestar)
        bttnMultiplicar = findViewById(R.id.buttonMultiplicar)
        bttnDividir = findViewById(R.id.buttonDividir)
        bttnIgual = findViewById(R.id.buttonIgual)

        bttn0?.setOnClickListener {
            addNumber(0)
        }
        bttn1?.setOnClickListener {
            addNumber(1)
        }
        bttn2?.setOnClickListener {
            addNumber(2)
        }
        bttn3?.setOnClickListener {
            addNumber(3)
        }
        bttn4?.setOnClickListener {
            addNumber(4)
        }
        bttn5?.setOnClickListener {
            addNumber(5)
        }
        bttn6?.setOnClickListener {
            addNumber(6)
        }
        bttn7?.setOnClickListener {
            addNumber(7)
        }
        bttn8?.setOnClickListener {
            addNumber(8)
        }
        bttn9?.setOnClickListener {
            addNumber(9)
        }
        bttnC?.setOnClickListener {
            var numero = txtNumeros?.text.toString();
            if(numero!="0") {
                txtNumeros.setText("0")
            } else {
                operador = ""
                numDetras = null
            }
        }
        bttnSumar?.setOnClickListener {
            operacion("+")
        }
        bttnRestar?.setOnClickListener {
            operacion("-")
        }
        bttnMultiplicar?.setOnClickListener {
            operacion("X")
        }
        bttnDividir?.setOnClickListener {
            operacion("/")
        }
        bttnIgual?.setOnClickListener {
            operacion("=")
        }
    }

    private fun addNumber(number: Int): Unit {
        var numero = txtNumeros?.text.toString();
        if(numero.equals("0")) {
            numero = "$number";
        } else {
            if(lastButton.isAnOperador()){
                numero = "$number";
            } else {
                numero += number
            }
        }
        txtNumeros.setText(numero)
        lastButton = "$number"
    }

    private fun operacion(operadorEnviado: String): Unit {
        var numero = txtNumeros?.text.toString().toInt();
        if(operador.isEmpty()) {
            numDetras = numero
            operador = operadorEnviado
            lastButton = operadorEnviado
        } else {
            if (operadorEnviado.equals("=")) {
                if (numDetras != null) {
                    when (operador) {
                        "+" -> {
                            numDetras = numDetras!! +numero
                            txtNumeros.setText("$numDetras")
                        }
                        "-" -> {
                            numDetras = numDetras!! -numero
                            txtNumeros.setText("$numDetras")
                        }
                        "X" -> {
                            numDetras = numDetras!!*numero
                            txtNumeros.setText("$numDetras")
                        }
                        "/" -> {
                            numDetras = numDetras!!/numero
                            txtNumeros.setText("$numDetras")
                        }
                    }
                    operador = ""
                    lastButton = operadorEnviado
                }
            }
        }
    }
}