package com.example.hydrationhaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var start: Button
    private  lateinit var  exit: Button

// starrt of the app

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start = findViewById(R.id.start)
        exit = findViewById(R.id.exit)

        start.setOnClickListener {

            val intent = Intent(this@MainActivity, MainActivity2::class.java)

            startActivity(intent)
        }

        exit.setOnClickListener {

            finishAffinity()

        }

    }
}