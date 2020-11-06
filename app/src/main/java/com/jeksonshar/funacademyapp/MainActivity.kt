package com.jeksonshar.funacademyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton: Button = findViewById(R.id.next_activity_button)
        nextButton.setOnClickListener { moveToNextActivity() }
    }

    private fun moveToNextActivity() {
        val intent = Intent(this, SecondActivity::class.java)

        val transmittedString = "\"строка\""
        intent.putExtra(SecondActivity.TRANSMITTED_STRING, transmittedString)

        val transmittedInt = 12345
        intent.putExtra(SecondActivity.TRANSMITTED_INT, transmittedInt)

        val transmittedBoolean = true
        intent.putExtra(SecondActivity.TRANSMITTED_BOOLEAN, transmittedBoolean)

        startActivity(intent)
    }
}