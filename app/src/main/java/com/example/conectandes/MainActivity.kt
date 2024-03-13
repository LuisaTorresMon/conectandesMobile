package com.example.conectandes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import com.example.conectandes.R.id.button41

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayout = findViewById<LinearLayout>(R.id.section2)

        linearLayout.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val btnCreateAlarm=findViewById<ImageButton>(button41);

        btnCreateAlarm.setOnClickListener {
            val intentCreateAlarm = Intent(this, CreateAlarm::class.java);
            startActivity(intentCreateAlarm);
        }
    }
}