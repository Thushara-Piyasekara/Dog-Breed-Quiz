package com.example.dogbreedquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val correctCount = intent.getIntExtra("correctCount", 0)
        val inCorrectCount = intent.getIntExtra("inCorrectCount", 0)

        val finishText = findViewById<TextView>(R.id.results)
        finishText.text = "Correct Count: $correctCount \n Incrrect Count :$inCorrectCount"


    }
}