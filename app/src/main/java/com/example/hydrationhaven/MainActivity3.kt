package com.example.hydrationhaven

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity3 : AppCompatActivity() {
// display page
    private lateinit var backButton: Button
    private lateinit var results: TextView
    var  data : String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val notes = intent.getStringArrayListExtra("notes")
        val dates = intent.getStringArrayListExtra("dates")

        val morning = intent.getStringArrayListExtra("morning")
        val afternoon = intent.getStringArrayListExtra("afternoon")

        backButton = findViewById(R.id.back)
        results = findViewById(R.id.result)


        if (notes != null) {
            for (note in notes.withIndex()) {

                var index = note.index

                val stringMorning : String? = morning?.get(index)
                var stringAfternoon : String? = afternoon?.get(index)
                var stringNote: String = note.value
                var stringDate: String? = dates?.get(index)

//Parrellel array
                val row = "Day : ${stringDate}\nMorning intake :${stringMorning}\nAfternoon intake :${stringAfternoon}\nNotes :${stringNote}"

                data += "$row\n\n\n\n"

            }
        }

        println(data)


        results.text = data




        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            startActivity(intent)
        }

    }
}