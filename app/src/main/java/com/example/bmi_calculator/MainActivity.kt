package com.example.bmi_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcbutton = findViewById<Button>(R.id.btnCalculate)

        calcbutton.setOnClickListener {

            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight,height)) {
                val bmi =
                    weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100)) //formula to calculate the bmi
                //to limit the decimal to two places
                val bmi2digits = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2digits)
            }
        }
    }
    private fun validateInput(weight:String?,height:String?):Boolean{
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Emplty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is Emplty", Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        val indexText = findViewById<TextView>(R.id.tvIndex)
        val descriptionText = findViewById<TextView>(R.id.tvDescription)
        val info = findViewById<TextView>(R.id.tvInfo)

        indexText.text = bmi.toString()

        info.text = "( Normal Range is 18.5 - 24.9 )"

        var resultText = ""
        var color = 0

        when { //when block is same as if else nut this is used in kotlin
            bmi < 18.50 -> {
                resultText = " Underweight "
                color = R.color.under_weight
            }

            bmi in 18.50..24.99 -> {
                resultText = " Healthy "
                color = R.color.normal
            }

            bmi in 25.00..29.99 -> {
                resultText = " Overweight "
                color = R.color.overweight
            }

            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.Obese
            }
        }
        descriptionText.setTextColor(ContextCompat.getColor(this,color)) //setting the color with regards to the result...
        descriptionText.text=resultText
    }
}
