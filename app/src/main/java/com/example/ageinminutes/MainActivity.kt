package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var selectedDateTextView: TextView
    lateinit var ageInMinutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.select_date_btn)
        button.setOnClickListener {view ->

            ClickDatePicker(view)
        }


    }

    fun ClickDatePicker(view: View){

        val myCalender = Calendar.getInstance() //Initialize the calender
        val currentYear = myCalender.get(Calendar.YEAR) //get the current(present) year
        val currentMonth = myCalender.get(Calendar.MONTH) //get the current(present) month
        val currentDay = myCalender.get(Calendar.DAY_OF_MONTH) ////get the current(present) day

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth -> //selectedyear, Month and day
            // are what the user chooses whey they click ok


            selectedDateTextView = findViewById(R.id.tv_selected_date)
            ageInMinutes = findViewById(R.id.tv_age_in_minutes)

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            selectedDateTextView.text = selectedDate

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = simpleDateFormat.parse(selectedDate)

            val selectedDateInMinutes = theDate?.time?.div(60000) // get the time in minutes from 1970 till user was born
            val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis())) //get today's date
            Log.d("today", "$currentDate")

            val currentDateToMinutes = currentDate!!.time / 60000 //get the time in minutes since 1970 till today's date
            val difference = currentDateToMinutes - selectedDateInMinutes!! //the difference is user's age in minutes

            ageInMinutes.text = "$difference"


        }, currentYear, currentMonth, currentDay) //these are today's date, month and year, which is shown when the calender dialog comes up
        datePickerDialog.datePicker.setMaxDate(Date().time - 86400000)// sets limit so user can't choose a future date
        datePickerDialog.show()//shows the dialog

    }
}