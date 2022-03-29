package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView?=null
    private var tvageinminutes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker:Button=findViewById(R.id.buttonDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvageinminutes=findViewById(R.id.tvageinminutes)
        buttonDatePicker.setOnClickListener {
           clickDatePicker()
        }
    }
   private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year =myCalendar.get(Calendar.YEAR)
        val month =myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)


       val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {

               view, year, month, dayofmonth ->
           Toast.makeText(
               this,
               "year was $year,month was ${month + 1},day of month $dayofmonth",
               Toast.LENGTH_SHORT
           ).show()

           val selectedDate = "$dayofmonth/${month + 1}/$year"
           tvSelectedDate?.setText(selectedDate)
           val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
           val theDate = sdf.parse(selectedDate)
           theDate?.let { val selectedDateInMinutes = theDate.time/60000
               val  currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
               currentDate?.let { val currentdateinminutes=currentDate.time/60000
                   val differenceinminutes=currentdateinminutes-selectedDateInMinutes

                   tvageinminutes?.text=differenceinminutes.toString() }  }




       },
           year,
           month,
           day,
       )
  dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
  dpd.show()


    }

}