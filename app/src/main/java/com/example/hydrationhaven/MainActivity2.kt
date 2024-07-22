package com.example.hydrationhaven

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar




class MainActivity2 : AppCompatActivity() {

    private lateinit var submitButton: Button
    private lateinit var clearButton: Button
    private lateinit var pickTimeButton: Button
    private lateinit var detailPageButton: Button
    private lateinit var exitButton: Button

    private lateinit var averageView: TextView
    private lateinit var seletedDayView: TextView
    private lateinit var morningInput: EditText
    private lateinit var afternoonInput: EditText
    private lateinit var noteInput: EditText


    var selectedDate: String = ""
    var average: String = ""


    public var notes = mutableListOf<String>()
    public var dates = mutableListOf<String>()
    public var morning = mutableListOf<Double>()
    public var afternoon = mutableListOf<Double>()


    fun showDatePickerDialog(v : TextView) {

        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->


                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                //Show seled day
                Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_LONG).show()

                v.text = selectedDate


            }, year, month, day)

        datePickerDialog.show()
    }

  fun average(){

      var morningTotal: Double = 0.00
      var afternoonTotal: Double = 0.00
      var total: Double = 0.00
      var number = morning.size



      for (item in morning.withIndex()){

          var index = item.index

          morningTotal += morning[index]
          afternoonTotal += afternoon[index]



      }



      total =( morningTotal + afternoonTotal)/number

      average = total.toString();

      averageView.text = " Average ${average} liters"

  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        submitButton = findViewById(R.id.submit)
        pickTimeButton = findViewById(R.id.selectDay)
        clearButton = findViewById(R.id.clear)
        detailPageButton = findViewById(R.id.detail)
        exitButton = findViewById(R.id.exit2)


        averageView = findViewById(R.id.average)
        seletedDayView = findViewById(R.id.selectedDay)
        morningInput = findViewById(R.id.morningInput)
        afternoonInput = findViewById(R.id.afternoonInput)
        noteInput = findViewById(R.id.noteInput)


        averageView.text = "Weekly average is  0"

        submitButton.setOnClickListener {


            if (morningInput.text.isEmpty() || afternoonInput.text.isEmpty()|| selectedDate == "" || noteInput.text.isEmpty()) {

                averageView.text = "Please enter every thing "
            } else {


                notes.add(noteInput.text.toString())
                dates.add(selectedDate)
                morning.add(morningInput.text.toString().toDouble())
                afternoon.add(afternoonInput.text.toString().toDouble())


                noteInput.text.clear()
                morningInput.text.clear()
                afternoonInput.text.clear()
                selectedDate = ""
                seletedDayView.text = "none"

            }


            println(notes)
            println(dates)
            println(morning)
            println(afternoon)

            average()
        }

        detailPageButton.setOnClickListener {
            val intent = Intent(this@MainActivity2, MainActivity3::class.java)

            intent.putStringArrayListExtra("dates", ArrayList(dates))
            intent.putStringArrayListExtra("notes", ArrayList(notes))

            var morningS = mutableListOf<String>()
            var afternoonS = mutableListOf<String>()

            for( item in dates.withIndex()){
                var index = item.index

                morningS.add(morning[index].toString())
                afternoonS.add(afternoon[index].toString())

            }


            intent.putExtra("morning", ArrayList(morningS))
            intent.putExtra("afternoon", ArrayList(afternoonS))



            startActivity(intent)
        }

        exitButton.setOnClickListener {

            finishAffinity()

        }

        clearButton.setOnClickListener {

            noteInput.text.clear()
            morningInput.text.clear()
            afternoonInput.text.clear()
            selectedDate = ""
            seletedDayView.text = "none"
        }

        pickTimeButton.setOnClickListener {
            showDatePickerDialog(seletedDayView)
        }


    }
}