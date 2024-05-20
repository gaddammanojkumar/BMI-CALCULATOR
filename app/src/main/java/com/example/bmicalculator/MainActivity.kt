package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeightFeet: EditText
    private lateinit var editHeightInches: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editWeight = findViewById<EditText>(R.id.editweight)
        editHeightFeet = findViewById<EditText>(R.id.editheight)
        editHeightInches = findViewById<EditText>(R.id.editHeight1)
        buttonCalculate= findViewById<Button>(R.id.buttonCalculate)
        textResult = findViewById<TextView>(R.id.textResult)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textResult = findViewById(R.id.textResult)

        buttonCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val weightStr = editWeight.text.toString()
        val heightFeetStr = editHeightFeet.text.toString()
        val heightInchesStr = editHeightInches.text.toString()

        if (weightStr.isEmpty() || heightFeetStr.isEmpty() || heightInchesStr.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toFloatOrNull()
        val heightFeet = heightFeetStr.toIntOrNull()
        val heightInches = heightInchesStr.toIntOrNull()

        if (weight == null || heightFeet == null || heightInches == null) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert height to inches
        val totalHeightInches = heightFeet * 12 + heightInches

        // Convert height to meters
        val heightMeters = totalHeightInches * 0.0254

        // Calculate BMI
        val bmi = weight / (heightMeters * heightMeters)

        // Display the result
        textResult.text = String.format("Your BMI: %.2f", bmi)
        val bmiCategory = when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Healthy weight"
            else -> "Overweight"
        }
        textResult.text = String.format("Your BMI: %.2f\nCategory: %s", bmi, bmiCategory)
    }
}
