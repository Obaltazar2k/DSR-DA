package com.stardust.proyectokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.stardust.proyectokotlin.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frame: FrameLayout? = findViewById(R.id.mainFrame)
        if(frame!=null) {
            val loginFragment = LoginFragment()
            loginFragment.arguments = intent.extras

            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainFrame, loginFragment)
            transaction.commit()
        }
    }

}