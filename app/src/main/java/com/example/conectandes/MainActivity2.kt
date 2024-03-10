package com.example.conectandes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val posponerAlarmaButton = findViewById<ImageButton>(R.id.section3)
        val detenerAlarmaButton = findViewById<ImageButton>(R.id.section4)

        posponerAlarmaButton.setOnClickListener {
            // Define el Intent para iniciar MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        detenerAlarmaButton.setOnClickListener {
            // Define el Intent para iniciar MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}